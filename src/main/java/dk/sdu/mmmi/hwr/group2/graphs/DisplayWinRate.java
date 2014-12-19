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
import java.util.TreeMap;

/**
 *
 * @author jeppe
 */
public class DisplayWinRate extends ProcessingApplet{


    private GPointsArray winsPerPlayer = new GPointsArray(5);
    private GPlot plot;

    public DisplayWinRate() {
        super(225, 200);
    }

    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches(true);
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        
        winsPerPlayer.add(1, 0, "Draw");
        winsPerPlayer.add(2, 0, "Blue");
        winsPerPlayer.add(3, 0, "Red");
        
        for (Match match : matches.values()) {
            int winner = match.getWinner();
            winsPerPlayer.get(winner).setY(winsPerPlayer.getY(winner) + 1);
        }
        
        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(200,200);
        plot.setXLim(new float[] {0.5f,winsPerPlayer.getNPoints()+0.5f});
        plot.setYLim(new float[] {0,80});
        plot.getTitle().setText("Matches Won per Player");
        plot.getTitle().setTextAlignment(CENTER);
        plot.getYAxis().getAxisLabel().setText("Matches");
        plot.getYAxis().getAxisLabel().setTextAlignment(RIGHT);
        plot.setDim(getPreferredSize().width - 100, getPreferredSize().height - 100);
        plot.getTitle().setText("Wins per Player");
        plot.getYAxis().getAxisLabel().setText("Wins");
        plot.setPoints(winsPerPlayer);
      //  plot.setPointSizes(new float[] {5,5});
        plot.startHistograms(GPlot.VERTICAL);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[] {
                color(255, 255, 0, 255), color(0, 0, 255, 255), 
                color(255, 0, 0, 255)
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
