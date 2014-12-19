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
public class DisplayWinRate extends ProcessingApplet{


    private GPointsArray winsPerPlayer = new GPointsArray(5);
    private GPlot plot;

    public DisplayWinRate() {
        super(225, 300);
    }

    @Override
    public void setup() {
        super.setup();
        Utils.getAllMatches(true);
        TreeMap<Calendar, Match> matches = Utils.getAllMatches(true, false);
        
        winsPerPlayer.add(1, 0, "Draw");
        winsPerPlayer.add(2, 0, "Player 1");
        winsPerPlayer.add(3, 0, "Player 2");
        
        for (Match match : matches.values()) {
            int winner = match.getWinner();
            winsPerPlayer.get(winner).setY(winsPerPlayer.getY(winner) + 1);
        }
        
        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(getPreferredSize().width - 100, getPreferredSize().height - 100);
        plot.getTitle().setText("Wins per Player");
        plot.getYAxis().getAxisLabel().setText("Wins");
        plot.getYAxis().getAxisLabel().setTextAlignment(RIGHT);
        plot.setPoints(winsPerPlayer);
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
