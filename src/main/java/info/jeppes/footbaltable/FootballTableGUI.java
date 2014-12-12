/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable;

import grafica.GPlot;
import grafica.GPointsArray;
import java.util.Calendar;
import java.util.TreeMap;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class FootballTableGUI extends PApplet {

    GPointsArray goalsPerWeekDay = new GPointsArray(7);
    // Create a new plot and set its position on the screen
    GPlot plot = new GPlot(this, 25, 25);
    private int lowestGoalsPerDay = 0;
    private int highestGoalsPerDay = 0;

    @Override
    public void setup() {
        Utils.getAllMatches();
        size(500, 350); //Sets the size of the canvas
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true);

        int[] goals = new int[7];
        
        for (Calendar calendar : matches.keySet()) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            goals[dayOfWeek - 1]++;
        }
        
        for (int i = 0; i < goals.length; i++) {
            goalsPerWeekDay.add(i, goals[i]);
            if(goals[i] > highestGoalsPerDay){
                highestGoalsPerDay = goals[i];
            }
            if(goals[i] < lowestGoalsPerDay){
                lowestGoalsPerDay = goals[i];
            }
        }
    }

    @Override
    public void draw() {
        drawBars();
    }

    public void drawBars() {
        int margin = 10;
        int x = margin;
        int barWidth = (width - margin * 2) / goalsPerWeekDay.getNPoints();
        for (int i = 0; i < goalsPerWeekDay.getNPoints(); i++) {
            if (mouseX > x && mouseX <= x + barWidth) {
                fill(255, 40, 40);
            } else {
                fill(50);
            }

            // we need to make the data range fit in out window so we can use map  
            // we take the upper bound as a little larger our biggest value 
            // and the lower bound as a little lower than our lowest value 
            
            float h = map(goalsPerWeekDay.get(i).getY(), lowestGoalsPerDay, highestGoalsPerDay, 0 + margin * 2, height - margin * 2);
            
            System.out.println(barWidth);
            
            // as y increaces posativly from top to bottom we need to fiddle around 
            // where the top of our box is to make it look like they grow from bottom to top 
            rect(x , height - h - margin, barWidth, h);

            // after we have drawn a bar we need to incriment our x position so 
            // they don't draw on top of each other 
            x += barWidth;
        }
    }
}
