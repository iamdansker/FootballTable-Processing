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
import java.util.TreeMap;

/**
 *
 * @author jeppe
 */
public class DisplayMatchDuritations extends ProcessingApplet{
    GPointsArray duritations = new GPointsArray(300);
    // Create a new plot and set its position on the screen
    GPlot plot = new GPlot(this, 0, 0);

    public DisplayMatchDuritations(){
        super(450, 300);
    }

    @Override
    public void setup(){
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true);
        int i = 0;
        for(Match match : matches.values()){
            long duritation = match.getNewestGoalTime().getTimeInMillis() - match.getOldestGoalTime().getTimeInMillis();
            float minutes = duritation / (60f * 1000f);
            if(minutes >= 1000 || minutes == 0){
                continue;
            }
            duritations.add(i++, minutes);
        }
        
    }

    @Override
    public void draw()
    {
        // Set the plot title and the axis labels
        plot.setTitleText("Match Duritations");
        plot.getXAxis().setAxisLabelText("Match");
        plot.getYAxis().setAxisLabelText("Duritation in Minutes");

        // Add the points
        plot.setPoints(duritations);
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