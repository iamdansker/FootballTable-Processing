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
    GPointsArray goals = new GPointsArray(300);
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

        int i = 0;
        for(Calendar calendar : match.getGoalsTimeStamps()){
            goals.add(i++, (calendar.getTimeInMillis() - time) / 1000l);
        }
    }

    @Override
    public void draw()
    {
        // Set the plot title and the axis labels
        plot.setTitleText("Match Goals over Time in Seconds");
        plot.getXAxis().setAxisLabelText("Goal");
        plot.getYAxis().setAxisLabelText("Time Since First Goal (Seconds)");

        // Add the points
        plot.setPoints(goals);
        plot.setLineColor(color(100, 100, 100));
        plot.activatePointLabels();

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
        plot.drawLegend(new String[] {"Time"}, new float[] {0.60f}, 
                        new float[] {0.92f});
        plot.endDraw();
    }
}
