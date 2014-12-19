/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable.graphs;

import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.Utils;
import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author jeppe
 */
public class DisplayGeneralMatchStatistics extends JPanel{
    private JPanel panel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private final int matchID;

    public DisplayGeneralMatchStatistics(int matchID) {
        this.matchID = matchID;
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
        Match match = Utils.getMatch(matchID);
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
        String startTime = sdf.format(match.getOldestGoalTime().getTime());
        String endTime = sdf.format(match.getNewestGoalTime().getTime());
        
        String matchDuritation = Utils.getTimeMMSSStringShort(match.getNewestGoalTime().getTimeInMillis() - match.getOldestGoalTime().getTimeInMillis());
        
        
        int totalGoals = match.getGoalsMap().size();
        int player1Goals = match.getGoalsByPlayer(1).size();
        int player2Goals = totalGoals - player1Goals;
        
        
//        panelLeft.add(new JLabel("Average Goals Per Match: "));
//        panelRight.add(new JLabel(String.valueOf(new BigDecimal(averageGoals).setScale(2, RoundingMode.DOWN).doubleValue())));
        
        panelLeft.add(new JLabel("Duritation"));
        panelRight.add(new JLabel(matchDuritation));
        
        panelLeft.add(new JLabel("Start Time"));
        panelRight.add(new JLabel(startTime));
        
        panelLeft.add(new JLabel("End Time"));
        panelRight.add(new JLabel(endTime));
        
        panelLeft.add(new JLabel("Total Goals"));
        panelRight.add(new JLabel(String.valueOf(totalGoals)));
        
        panelLeft.add(new JLabel("Blue Goals"));
        panelRight.add(new JLabel(String.valueOf(player1Goals)));
        
        panelLeft.add(new JLabel("Red Goals"));
        panelRight.add(new JLabel(String.valueOf(player2Goals)));
        
        
    }
}
