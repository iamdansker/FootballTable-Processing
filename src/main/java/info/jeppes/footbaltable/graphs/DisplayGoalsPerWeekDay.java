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
import java.util.Locale;
import java.util.TreeMap;
import processing.core.PVector;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsPerWeekDay extends ProcessingApplet {

    private GPointsArray goalsPerWeekDay = new GPointsArray(7);
    private GPlot plot;

    public DisplayGoalsPerWeekDay() {
        super(450, 200);
    }

    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches(true);
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);

        int[] goals = new int[7];
        String[] labels = new String[7];
        
        for (Calendar calendar : matches.keySet()) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            int index = dayOfWeek == 1 ? 6 : dayOfWeek - 2;
            goals[index] += matches.get(calendar).getGoalsMap().size();
            if(labels[index] == null){
                labels[index] = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.ENGLISH);
            }
        }
        
        for (int i = 0; i < goals.length; i++) {
            goalsPerWeekDay.add(new PVector(i, goals[i]), labels[i]);
        }
        
        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        int margin = 50;
        plot.setDim(getPreferredSize().width - (margin * 2) , getPreferredSize().height - margin * 2 + 25);
        plot.getTitle().setText("Goals per Week Day (total)");
        plot.getYAxis().getAxisLabel().setText("Goals");
        plot.setPoints(goalsPerWeekDay);
        plot.startHistograms(GPlot.VERTICAL);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[] {
                color(0, 0, 255, 50), color(0, 0, 255, 100), 
                color(0, 0, 255, 150), color(0, 0, 255, 200)
            }
        );
    }

    @Override
    public void draw() {
        plot.beginDraw();
        plot.drawBackground();
        plot.drawBox();
        plot.drawYAxis();
        plot.drawTitle();
        plot.drawHistograms();
        plot.endDraw();
    }

//    public void drawBars() {
//        int margin = 75;
//        int axisMargin = 25;
//        int x = margin;
//        int barWidth = (width - margin * 2) / goalsPerWeekDay.getNPoints();
//        for (int i = 0; i < goalsPerWeekDay.getNPoints(); i++) {
//            if (mouseX > x && mouseX <= x + barWidth) {
//                fill(255, 40, 40);
//            } else {
//                fill(50);
//            }
//
//            // we need to make the data range fit in out window so we can use map  
//            // we take the upper bound as a little larger our biggest value 
//            // and the lower bound as a little lower than our lowest value 
//            
//            float h = map(goalsPerWeekDay.get(i).getY(), lowestGoalsPerDay, highestGoalsPerDay, 0 + margin * 2, height - margin * 2);
//            
//            // as y increaces posativly from top to bottom we need to fiddle around 
//            // where the top of our box is to make it look like they grow from bottom to top 
//            rect(x , height - h - margin, barWidth, h);
//
//            // after we have drawn a bar we need to incriment our x position so 
//            // they don't draw on top of each other 
//            x += barWidth;
//        }
//        fill(20);
//        //Y Axis
//        line(margin - axisMargin, height - margin + axisMargin, margin - axisMargin, margin - axisMargin);
//        //X Axis
//        line(margin - axisMargin, height - margin + axisMargin, width - margin + axisMargin, height - margin + axisMargin);
//        
////        text("Test", 100, 100);
//        translate(margin - axisMargin - 10, height / 2);  // Translate to the center
//        rotate((float) (Math.PI/2 * 3));                // Rotate by theta
//        textAlign(CENTER);      
//        text("TEST", 0, 0);
//    }
}
