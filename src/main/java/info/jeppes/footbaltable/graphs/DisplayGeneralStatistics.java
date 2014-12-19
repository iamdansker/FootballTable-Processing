/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable.graphs;

import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.Utils;
import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TreeMap;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jeppe
 */
public class DisplayGeneralStatistics extends JPanel{
    private JPanel panel;
    private JPanel panelLeft;
    private JPanel panelRight;

    public DisplayGeneralStatistics() {
        init();
        loadData();
    }

    private void init() {
        setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setBorder(BorderFactory.createLoweredSoftBevelBorder());
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelLeft.setLayout(new BoxLayout(panelLeft,BoxLayout.Y_AXIS));
        panelRight.setLayout(new BoxLayout(panelRight,BoxLayout.Y_AXIS));
////        panelLeft.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.GRAY));
        panel.add(panelLeft,BorderLayout.WEST);
        panel.add(panelRight,BorderLayout.EAST);
        add(panel,BorderLayout.NORTH);
    }
    
    public void loadData(){
        TreeMap<Calendar, Match> allMatches = Utils.getAllMatches(true);
        
        Calendar instance = GregorianCalendar.getInstance(Locale.getDefault());
        
        int totalGoals = 0;
        int matchesSize = allMatches.size();
        int matchesThisWeek = 0;
        long totalDuritation = 0;
        
        for(Calendar calendar : allMatches.keySet()){
            Match match = allMatches.get(calendar);
            totalGoals += match.getGoals().length;
            long duritation = match.getNewestGoalTime().getTimeInMillis() - match.getOldestGoalTime().getTimeInMillis();
            if(duritation < 60000000){
                totalDuritation += duritation;
            }
            if(instance.getTimeInMillis() - calendar.getTimeInMillis() <= 604800000){ //604800000 == 1 week in ms
               matchesThisWeek++;
            }
        }
        
        double averageGoals = (double) totalGoals / matchesSize;
        int averageMatchesThisWeek = matchesThisWeek / 7;
        long averageDuritation = totalDuritation / matchesSize;
        
        
        
//        JPanel totalMatches = new JPanel();
//        totalMatches.setBorder(BorderFactory.createEtchedBorder());
//        totalMatches.setLayout(new BorderLayout());
//        totalMatches.add(new JLabel("Total Matches: "),BorderLayout.WEST);
//        totalMatches.add(new JLabel(String.valueOf(matchesSize)), BorderLayout.EAST);
//        
//        JPanel AverageGoalsPerMatch = new JPanel();
//        AverageGoalsPerMatch.setBorder(BorderFactory.createEtchedBorder());
//        AverageGoalsPerMatch.setLayout(new BorderLayout());
//        AverageGoalsPerMatch.add(new JLabel("Average Goals Per Match: "),BorderLayout.WEST);
//        AverageGoalsPerMatch.add(new JLabel(String.valueOf(new BigDecimal(averageGoals).setScale(2, RoundingMode.DOWN).doubleValue())), BorderLayout.EAST);
//
        panelLeft.add(new JLabel("Total Matches: "));
        panelRight.add(new JLabel(String.valueOf(matchesSize)));
        
        panelLeft.add(new JLabel("Average Goals Per Match: "));
        panelRight.add(new JLabel(String.valueOf(new BigDecimal(averageGoals).setScale(2, RoundingMode.DOWN).doubleValue())));
        
        panelLeft.add(new JLabel("Average Duritation: "));
        panelRight.add(new JLabel(Utils.getTimeMMSSStringShort(averageDuritation)));
        
        panelLeft.add(new JLabel("Matches This Week: "));
        panelRight.add(new JLabel(String.valueOf(matchesThisWeek)));
        
        panelLeft.add(new JLabel("Average Matches Per Day This Week: "));
        panelRight.add(new JLabel(String.valueOf(averageMatchesThisWeek)));
        
    }
}
