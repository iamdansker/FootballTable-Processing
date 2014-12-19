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
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsOverTheDay extends ProcessingApplet{

    private int hour;
    private int highestPoint = 0;
    private GPointsArray GoalsPerHour = new GPointsArray(24);
    private GPlot plot = new GPlot(this);

    public DisplayGoalsOverTheDay() {
        super((int) (450 * 1.5f), 200);
    }

    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches();
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        
        int[] goalsPerHour = new int[24];
        
        for (Match match : matches.values()) {
            for(Calendar goal : match.getGoalsTimeStamps()){
                hour = goal.get(Calendar.HOUR_OF_DAY);
                goalsPerHour[hour]++;
                if(goalsPerHour[hour] > highestPoint){
                    highestPoint = goalsPerHour[hour];
                }
            }
        }
        for (int i = 0; i < goalsPerHour.length;i++){
            GoalsPerHour.add(i,goalsPerHour[i],Integer.toString(i));
        }
        
        int margin = 25;
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(getPreferredSize().width - (margin * 2  + 25), getPreferredSize().height - margin * 2);
        plot.setYLim(0, goalsPerHour[hour] * 1.1f);
        plot.setXLim(new float[] {-1f,24f});
        plot.setMar(margin, margin + 25, margin, margin);
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
}
