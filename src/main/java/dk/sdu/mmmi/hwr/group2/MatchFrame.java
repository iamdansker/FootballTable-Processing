/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.hwr.group2;

import dk.sdu.mmmi.hwr.group2.graphs.DisplayGeneralMatchStatistics;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayGoalsOverTime;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JPanel;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class MatchFrame extends JFrame{
    private final int matchID;

    public MatchFrame(int matchID) throws HeadlessException {
        this.matchID = matchID;
        init();
        
    }
    
    private void init(){
        setLayout(new BorderLayout());
        Match match = Utils.getMatch(matchID);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd HH:mm:ss");	
        String startTime = sdf.format(match.getOldestGoalTime().getTime());
        setTitle("Match "+matchID + " - "+startTime);
        
        PApplet goalsOverTime = new DisplayGoalsOverTime(matchID);
        goalsOverTime.init();
        add(goalsOverTime, BorderLayout.WEST);
        
        JPanel generalMatchStatistics = new DisplayGeneralMatchStatistics(matchID);
        add(generalMatchStatistics, BorderLayout.EAST);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
