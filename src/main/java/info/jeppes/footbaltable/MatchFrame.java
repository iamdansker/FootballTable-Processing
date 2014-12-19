/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable;

import info.jeppes.footbaltable.graphs.DisplayGeneralMatchStatistics;
import info.jeppes.footbaltable.graphs.DisplayGoalsOverTime;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
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
        setTitle("Match "+matchID);
        
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
