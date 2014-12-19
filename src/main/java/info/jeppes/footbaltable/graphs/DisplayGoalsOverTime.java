/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.ProcessingApplet;
import info.jeppes.footbaltable.Utils;
import java.util.Calendar;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsOverTime extends ProcessingApplet{
    GPointsArray goals = new GPointsArray(200);
    GPointsArray player1Goals = new GPointsArray(100);
    GPointsArray player2Goals = new GPointsArray(100);
    // Create a new plot and set its position on the screen
    GPlot plot = new GPlot(this, 0, 0);
    private int matchID;

    public DisplayGoalsOverTime(int matchID){
        super(450, 300);
        this.matchID = matchID;
    }

    @Override
    public void setup(){
        Match match = Utils.getMatch(matchID);

        long time = match.getOldestGoalTime().getTimeInMillis();

        int player1GoalsNumber = 0;
        int player2GoalsNumber = 0;
        
        boolean first = true;
        
        int i = 0;
        for(Calendar calendar : match.getGoalsTimeStamps()){
            long timeMS = (calendar.getTimeInMillis() - time) / 1000l + 5; //Fix for fist goal being used as time reference, so add 5 sec to the goal time to make it look better
            if(first){
                first = false;
                goals.add(timeMS - 5, i);
                player1Goals.add(timeMS - 5, player1GoalsNumber);
                player2Goals.add(timeMS - 5, player2GoalsNumber);
            }
            i++;
            goals.add(timeMS, i);
            if(match.getGoalsMap().get(calendar) == 1){
                player1GoalsNumber++;
            } else{
                player2GoalsNumber++;
            }
            player1Goals.add(timeMS, player1GoalsNumber);
            player2Goals.add(timeMS, player2GoalsNumber);
        }
        // Set the plot title and the axis labels
        plot.setTitleText("Match Goals over Time in Seconds");
        plot.getYAxis().setAxisLabelText("Goal");
        plot.getXAxis().setAxisLabelText("Goal Time (Seconds)");

        // Add the points
        plot.setPoints(goals);
        plot.setLineColor(color(100, 100, 100));
        plot.activatePointLabels();
        
        plot.addLayer("player1Goals", player1Goals);
        plot.getLayer("player1Goals").setLineColor(color(0, 0, 150));
        plot.addLayer("player2Goals", player2Goals);
        plot.getLayer("player2Goals").setLineColor(color(150, 0, 0));
    }

    @Override
    public void draw()
    {

        // Draw it!
        //plot.defaultDraw();
        plot.beginDraw();
        plot.drawBackground();
        plot.drawBox();
        plot.drawXAxis();
        plot.drawYAxis();
        plot.drawTitle();
        plot.drawLines();
        plot.drawLabels();
        plot.drawLegend(new String[]{"Total Goals","Blue Player Goals", "Red Player Goals"},
                    new float[]{0.07f, 0.07f, 0.07f},
                    new float[]{0.92f, 0.82f, 0.72f});

        plot.endDraw();
    }
}
