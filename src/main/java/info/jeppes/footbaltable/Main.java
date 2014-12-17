/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import info.jeppes.footbaltable.graphs.DisplayGoalsOverTime;
import info.jeppes.footbaltable.graphs.DisplayGoalsPerPlayer;
import info.jeppes.footbaltable.graphs.DisplayGoalsPerWeekDay;
import info.jeppes.footbaltable.graphs.DisplayMatchesPerWeekday;
import info.jeppes.footbaltable.graphs.DisplayWinRate;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class Main extends JFrame{
     public Main() {
         super("Embedded PApplet");

         setLayout(new FlowLayout());
         
         PApplet embed = new DisplayGoalsOverTime();
         embed.init();
         add(embed, BorderLayout.WEST);
         
         PApplet embed2 = new DisplayGoalsPerWeekDay();
         embed2.init();
         add(embed2, BorderLayout.EAST);
         
         PApplet embed3 = new DisplayWinRate();
         embed3.init();
         add(embed3, BorderLayout.SOUTH);
         
         PApplet embed4 = new DisplayMatchesPerWeekday();
         embed4.init();
         add(embed4);
         
         PApplet embed5 = new DisplayGoalsPerPlayer();
         embed5.init();
         add(embed5);
         
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         //this.pack();
         this.setSize(800, 600);
         this.setVisible(true);
     }

    public static void main(String[] args){
        new Main();
//        AppletViewer viewer = AppletLauncher.applet(FootballTableGUI.class).start();
        
    }
}
