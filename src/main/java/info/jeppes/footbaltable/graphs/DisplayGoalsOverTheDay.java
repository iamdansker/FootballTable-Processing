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
import java.util.Calendar;
import java.util.TreeMap;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.RIGHT;
import processing.core.PVector;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsOverTheDay extends PApplet{

    int hour;
    private GPointsArray GoalsPerHour = new GPointsArray(24);
    private GPlot plot = new GPlot(this);

    @Override
    public void setup() {
        Utils.getAllMatches();
        size(700, 300); //Sets the size of the canvas
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        
        
        
        for (Match match : matches.values()) {
            
            
          System.out.println(match.getGoalsTimeStamps());
         
          
        }
        
        int[] goalsPerHour = new int[24];
        
        for (Calendar calendar : matches.keySet()) {
            hour = calendar.get(Calendar.HOUR_OF_DAY);
            System.out.println(hour);
            
            goalsPerHour[hour]++;
            
        }
        for (int i = 0; i < goalsPerHour.length;i++){
        GoalsPerHour.add(i,goalsPerHour[i],Integer.toString(i));
        }
        
      // GoalsPerHour.add(0,0);
        
        /*for (Match match : matches.values()) {
            int winner = match.getWinner();
            winsPerPlayer.get(winner).setY(winsPerPlayer.getY(winner) + 1);
        }*/
        
        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(600,200);
        plot.setYLim(new float[] {0,30});
        plot.setXLim(new float[] {0f,23f});
        plot.getTitle().setText("Number of goals scored per hour");
        plot.getTitle().setTextAlignment(CENTER);
        plot.getYAxis().getAxisLabel().setText("Goals");
        plot.getYAxis().getAxisLabel().setTextAlignment(CENTER);
        plot.setPoints(GoalsPerHour);
        plot.activatePointLabels();
      //  plot.setPointSizes(new float[] {5,5});
        plot.startHistograms(GPlot.VERTICAL);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[] {
                color(255, 255, 0, 255), color(0, 0, 255, 255), 
                color(255, 0, 0, 255), color(0, 0, 255, 200)
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
}
