package info.jeppes.footbaltable.graphs;

import grafica.GPlot;
import grafica.GPointsArray;
import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.ProcessingApplet;
import info.jeppes.footbaltable.Utils;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import processing.core.PVector;

/**
 *
 * @author Mathias
 */
public class DisplayMatchesPerWeekday extends ProcessingApplet {

    private GPlot plot;

    private GPointsArray pointArray = new GPointsArray(7);

    public DisplayMatchesPerWeekday(){
        super(450, 300);
    }
    
    @Override
    public void setup() {
        super.setup();
        TreeMap<Calendar, Match> allMatches = Utils.getAllMatches(true);

        int[] matchesPerWeekday = new int[8];

        for (Map.Entry<Calendar, Match> entrySet : allMatches.entrySet()) {
            Calendar key = entrySet.getKey();
            //Match value = entrySet.getValue();

            int dayOfWeek = key.get(Calendar.DAY_OF_WEEK);
            matchesPerWeekday[dayOfWeek]++;

        }
        Map<String, Integer> intValues = Calendar.getInstance().getDisplayNames(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.ENGLISH);
        //Reverse map for lookup
        Map<Integer, String> displayNames = new HashMap<>();
        for (String i : intValues.keySet()) {
            displayNames.put(intValues.get(i), i);
        }

        //Add Monday-Saturday
        for (int i = 2; i < matchesPerWeekday.length; i++) {
            pointArray.add(new PVector(i, matchesPerWeekday[i]), displayNames.get(i));
        }
        //Quickfix sunday
        pointArray.add(new PVector(matchesPerWeekday.length, matchesPerWeekday[1]), displayNames.get(1));

        // Setup for the third plot 
        plot = new GPlot(this);
        plot.setPos(0, 0);
        plot.getTitle().setText("Matches per weekday (total)");
        plot.getYAxis().getAxisLabel().setText("Matches");
        plot.startHistograms(GPlot.VERTICAL);
        plot.setPoints(pointArray);
        plot.getHistogram().setDrawLabels(true);
        plot.getHistogram().setRotateLabels(true);
        plot.getHistogram().setBgColors(new int[]{
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
