/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import info.jeppes.footbaltable.graphs.DisplayGeneralStatistics;
import info.jeppes.footbaltable.graphs.DisplayMatchTable;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author jeppe
 */
public class Main extends JFrame{
     public Main() {
         super("Embedded PApplet");

         setLayout(new BorderLayout());
         DisplayMatchTable displayMatchTable = new DisplayMatchTable();
         add(displayMatchTable, BorderLayout.CENTER);
         DisplayGeneralStatistics displayGeneralStatistics = new DisplayGeneralStatistics();
         add(displayGeneralStatistics, BorderLayout.EAST);
         
//         PApplet embed = new DisplayGoalsOverTime();
//         embed.init();
//         add(embed, BorderLayout.WEST);
//         
//         PApplet embed2 = new DisplayGoalsPerWeekDay();
//         embed2.init();
//         add(embed2, BorderLayout.EAST);
//         
//         PApplet embed3 = new DisplayWinRate();
//         embed3.init();
//         add(embed3, BorderLayout.SOUTH);
         
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setVisible(true);
     }

    public static void main(String[] args){
        new Main();
//        AppletViewer viewer = AppletLauncher.applet(FootballTableGUI.class).start();
        
    }
}
