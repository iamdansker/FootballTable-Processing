/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable;

import info.jeppes.footbaltable.graphs.DisplayGoalsOverTime;
import java.awt.HeadlessException;
import javax.swing.JFrame;
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
        PApplet goalsOverTime = new DisplayGoalsOverTime(matchID);
        goalsOverTime.init();
        add(goalsOverTime);
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
