/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.Utils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsOverTime extends PApplet{
    GPointsArray goals = new GPointsArray(300);
    // Create a new plot and set its position on the screen
    GPlot plot = new GPlot(this, 25, 25);
    

    @Override
    public void setup(){
        size( 500, 350 ); //Sets the size of the canvas
        Match match = Utils.getMatch(200);

        System.out.println(Arrays.toString(match.getGoals()));
        System.out.println(match.getGoalsTimeStamps());
        System.out.println(match.getNewestGoalTime().getTimeInMillis());
        System.out.println(match.getOldestGoalTime().getTimeInMillis());
        long time = match.getOldestGoalTime().getTimeInMillis();

        int i = 0;
        for(Calendar calendar : match.getGoalsTimeStamps()){
            System.out.println((calendar.getTimeInMillis() - time) / 1000l);
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
