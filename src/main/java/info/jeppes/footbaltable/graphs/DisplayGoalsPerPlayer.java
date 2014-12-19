package info.jeppes.footbaltable.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.ProcessingApplet;
import info.jeppes.footbaltable.Utils;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import processing.core.PVector;

/**
 *
 * @author Mathias
 */
public class DisplayGoalsPerPlayer extends ProcessingApplet {

    private GPlot plot;

    private GPointsArray pointArray = new GPointsArray(7);
//    float[] panelDim = new float[]{400, 200};

    public DisplayGoalsPerPlayer() {
        super(225, 225);
    }

    @Override
    public void setup() {
        TreeMap<Calendar, Match> allMatches = Utils.getAllMatches(true);

        int[] goalsPerPlayer = new int[2];

        for (Map.Entry<Calendar, Match> entrySet : allMatches.entrySet()) {
            //Calendar key = entrySet.getKey();
            Match value = entrySet.getValue();

            for (Integer goal : value.getGoals()) {
                goalsPerPlayer[goal-1]++;
            }

        }
        
        String[] playerNames = new String[]{"Blue", "Red"};

        //Add Monday-Saturday
        for (int i = 0; i < goalsPerPlayer.length; i++) {
            pointArray.add(new PVector(i, goalsPerPlayer[i]), playerNames[i]);
        }


        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setYLim(0, Math.max(goalsPerPlayer[0], goalsPerPlayer[1]) * 1.1f);
        plot.setDim(getPreferredSize().width - 100, getPreferredSize().height - 100);
        plot.getTitle().setText("Total goals per player");
        plot.getYAxis().getAxisLabel().setText("Matches");
        plot.startHistograms(GPlot.VERTICAL);
        plot.setPoints(pointArray);
        
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[]{
                color(0, 0, 255, 255), 
                color(255, 0, 0, 255)
        });
        plot.activateCentering();
//        float maxY = Math.max(goalsPerPlayer[0], goalsPerPlayer[1]) * 1.1f ;
//        plot.setYLim(0, maxY);
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
