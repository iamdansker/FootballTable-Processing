package info.jeppes.footbaltable.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.Utils;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;
import processing.core.PApplet;
import static processing.core.PConstants.LEFT;
import static processing.core.PConstants.RIGHT;
import processing.core.PVector;

/**
 *
 * @author Mathias
 */
public class DisplayGoalsPerPlayer extends PApplet {

    private GPlot plot;

    private GPointsArray pointArray = new GPointsArray(7);
    float[] panelDim = new float[]{400, 200};

    @Override
    public void setup() {
        size((int) (panelDim[0] + 100), (int) (panelDim[1] + 100)); //Sets the size of the canvas
        TreeMap<Calendar, Match> allMatches = Utils.getAllMatches(true);

        int[] goalsPerPlayer = new int[2];

        for (Map.Entry<Calendar, Match> entrySet : allMatches.entrySet()) {
            //Calendar key = entrySet.getKey();
            Match value = entrySet.getValue();

            for (Integer goal : value.getGoals()) {
                goalsPerPlayer[goal-1]++;
                System.out.println(goal);
            }

        }
        System.out.println(Arrays.toString(goalsPerPlayer));
        
        String[] playerNames = new String[]{"Player 1", "Player 2"};

        //Add Monday-Saturday
        for (int i = 0; i < goalsPerPlayer.length; i++) {
            pointArray.add(new PVector(i, goalsPerPlayer[i]), playerNames[i]);
        }


        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.setDim(panelDim);
        plot.getTitle().setText("Total goals per player");
        plot.getTitle().setTextAlignment(LEFT);
        plot.getYAxis().getAxisLabel().setText("Matches");
        plot.getYAxis().getAxisLabel().setTextAlignment(RIGHT);
        plot.startHistograms(GPlot.VERTICAL);
        plot.setPoints(pointArray);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[]{
            color(0, 0, 255, 50), color(0, 0, 255, 100),
            color(0, 0, 255, 150), color(0, 0, 255, 200)
        }
        );
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
