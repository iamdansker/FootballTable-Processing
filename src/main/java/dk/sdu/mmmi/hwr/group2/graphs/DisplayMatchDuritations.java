/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.hwr.group2.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import dk.sdu.mmmi.hwr.group2.Match;
import dk.sdu.mmmi.hwr.group2.ProcessingApplet;
import dk.sdu.mmmi.hwr.group2.Utils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TreeMap;
import static processing.core.PConstants.CENTER;

/**
 *
 * @author jeppe
 */
public class DisplayMatchDuritations extends ProcessingApplet{
    GPointsArray duritations = new GPointsArray(300);
    // Create a new plot and set its position on the screen
    GPlot plot = new GPlot(this, 0, 0);
    private int widestPoint;
    private int highestPoint;
    private GPointsArray GoalsPerHour;

    public DisplayMatchDuritations(){
        super(450, 200);
    }
    
    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches();
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        HashMap<Integer, Integer> durationDistribution = new HashMap();
        for (Match match : matches.values()) {
            int duritation = (int) ((match.getNewestGoalTime().getTimeInMillis() - match.getOldestGoalTime().getTimeInMillis()) / 1000) + 5;
            int rangeBlock = getRangeBlock(duritation, 60);
            if(rangeBlock > widestPoint){
                widestPoint = rangeBlock;
            }
            if(durationDistribution.containsKey(rangeBlock)){
                Integer value = durationDistribution.put(rangeBlock, durationDistribution.get(rangeBlock) + 1) + 1;
                if(highestPoint < value){
                    highestPoint = value;
                }
            } else {
                durationDistribution.put(rangeBlock, 1);
                if(highestPoint < 1){
                    highestPoint = 1;
                }
            }
        }
        GoalsPerHour = new GPointsArray(widestPoint);
        for(int i = 0; i < widestPoint; i++){
            GoalsPerHour.add(i, durationDistribution.containsKey(i + 1) ? durationDistribution.get(i + 1) : 0, Integer.toString(i + 1));
        }
        
      // GoalsPerHour.add(0,0);
        
        /*for (Match match : matches.values()) {
            int winner = match.getWinner();
            winsPerPlayer.get(winner).setY(winsPerPlayer.getY(winner) + 1);
        }*/
        
        // Setup for the third plot 
        int margin = 50;
        plot = new GPlot(this);
        plot.setPos(0, 0);
        
        plot.setDim(getPreferredSize().width - (margin * 2) , getPreferredSize().height - margin * 2 + 25);
        plot.setYLim(0, highestPoint * 1.1f);
        plot.setXLim(-1, widestPoint);
        plot.setMar(margin, margin, margin - 10 , margin);
        
        plot.getTitle().setText("Duration Distribution");
        plot.getTitle().setTextAlignment(CENTER);
        plot.getYAxis().getAxisLabel().setText("Occurrences");
        plot.getXAxis().getAxisLabel().setText("Duration (minutes)");
        plot.getXAxis().getAxisLabel().setOffset(17);
        plot.getXAxis().setTicks(new float[0]);
        plot.getXAxis().getAxisLabel().setDim(0, 0);
        plot.getYAxis().getAxisLabel().setTextAlignment(CENTER);
        plot.setPoints(GoalsPerHour);
        plot.activatePointLabels();
        plot.startHistograms(GPlot.VERTICAL);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[] {
                color(0, 0, 255, 50), color(0, 0, 255, 100), 
                color(0, 0, 255, 150), color(0, 0, 255, 200)
            }
        );
    }

    private int getRangeBlock(int value, int range){
        return (int) Math.ceil((float) value / (float) range);
    }
    
    @Override
    public void draw() {
        plot.beginDraw();
        plot.drawBackground();
        plot.drawBox();
        plot.drawYAxis();
        plot.drawXAxis();
        plot.drawLabels();
        plot.drawTitle();
        plot.drawHistograms();
        plot.endDraw();
    }

//    @Override
//    public void setup(){
//        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true);
//        int i = 0;
//        for(Match match : matches.values()){
//            long duritation = match.getNewestGoalTime().getTimeInMillis() - match.getOldestGoalTime().getTimeInMillis();
//            float minutes = duritation / (60f * 1000f);
//            if(minutes >= 1000 || minutes == 0){
//                continue;
//            }
//            duritations.add(i++, minutes);
//        }
//        
//    }
//
//    @Override
//    public void draw()
//    {
//        // Set the plot title and the axis labels
//        plot.setTitleText("Match Duration");
//        plot.getXAxis().setAxisLabelText("Match");
//        plot.getYAxis().setAxisLabelText("Duration in Minutes");
//
//        // Add the points
//        plot.setPoints(duritations);
//        plot.setLineColor(color(100, 100, 100));
//        plot.activatePointLabels();
//
//        // Draw it!
//        //plot.defaultDraw();
//        plot.beginDraw();
//        plot.drawBackground();
//        plot.drawBox();
//        plot.drawXAxis();
//        plot.drawYAxis();
//        plot.drawTitle();
//        plot.drawLines();
//        plot.drawLabels();
//        plot.drawLegend(new String[] {"Time"}, new float[] {0.60f}, 
//                        new float[] {0.92f});
//        plot.endDraw();
//    }
}