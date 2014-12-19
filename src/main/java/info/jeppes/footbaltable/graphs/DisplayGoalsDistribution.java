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
import java.util.HashMap;
import java.util.TreeMap;
import processing.core.PApplet;
import static processing.core.PConstants.CENTER;

/**
 *
 * @author jeppe
 */
public class DisplayGoalsDistribution extends ProcessingApplet{

    private int hour;
    private int highestPoint = 0;
    private int widestPoint = 0;
    private GPointsArray GoalsPerHour;
    private GPlot plot = new GPlot(this);

    public DisplayGoalsDistribution() {
        super((int) (450 * 1.5f), 200);
    }

    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches();
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        HashMap<Integer, Integer> goalsDistribution = new HashMap();
        
        for (Match match : matches.values()) {
            int goals = match.getGoalsMap().size();
            if(widestPoint < goals){
                widestPoint = goals;
            }
            if(goalsDistribution.containsKey(goals)){
                Integer value = goalsDistribution.put(goals, goalsDistribution.get(goals) + 1) + 1;
                if(highestPoint < value){
                    highestPoint = value;
                }
            } else {
                goalsDistribution.put(goals, 1);
                if(highestPoint < 1){
                    highestPoint = 1;
                }
            }
        }
        
        GoalsPerHour = new GPointsArray(widestPoint);
        for(int i = 0; i < widestPoint; i++){
            GoalsPerHour.add(i, goalsDistribution.containsKey(i + 1) ? goalsDistribution.get(i + 1) : 0, Integer.toString(i + 1));
        }
        
      // GoalsPerHour.add(0,0);
        
        /*for (Match match : matches.values()) {
            int winner = match.getWinner();
            winsPerPlayer.get(winner).setY(winsPerPlayer.getY(winner) + 1);
        }*/
        
        // Setup for the third plot 
        int margin = 25;
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(getPreferredSize().width - (margin * 2  + 25), getPreferredSize().height - margin * 2);
        plot.setYLim(0, highestPoint * 1.1f);
        plot.setXLim(-1, widestPoint);
        plot.setMar(margin, margin + 25, margin, margin);
        plot.getTitle().setText("Goals Distribution");
        plot.getTitle().setTextAlignment(CENTER);
        plot.getYAxis().getAxisLabel().setText("Occurrences");
        plot.getXAxis().getAxisLabel().setText("Goals");
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
