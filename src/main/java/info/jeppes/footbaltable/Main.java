/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import info.jeppes.footbaltable.graphs.DisplayGeneralStatistics;
import info.jeppes.footbaltable.graphs.DisplayGoalsOverTime;
import info.jeppes.footbaltable.graphs.DisplayGoalsPerWeekDay;
import info.jeppes.footbaltable.graphs.DisplayMatchTable;
import info.jeppes.footbaltable.graphs.DisplayWinRate;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import processing.core.PApplet;

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
         
         JPanel northPanel = new JPanel();
         northPanel.setLayout(new GridLayout(0, 3));
         add(northPanel,BorderLayout.NORTH);
         
         PApplet goalsOverTime = new DisplayGoalsOverTime();
         goalsOverTime.init();
         System.out.println(goalsOverTime.getPreferredSize());
         northPanel.add(goalsOverTime);
         
         PApplet goalsPerWeekDay = new DisplayGoalsPerWeekDay();
         goalsPerWeekDay.init();
         System.out.println(goalsPerWeekDay.getPreferredSize());
         northPanel.add(goalsPerWeekDay);
         
         PApplet winRate = new DisplayWinRate();
         winRate.init();
         System.out.println(winRate.getPreferredSize());
         northPanel.add(winRate);
         
         
//         PApplet goalsPerWeekDay = new DisplayGoalsPerWeekDay();
//         goalsPerWeekDay.init();
//         add(goalsPerWeekDay,BorderLayout.WEST);
         
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
         
//         PApplet embed4 = new DisplayMatchesPerWeekday();
//         embed4.init();
//         add(embed4);
//         
//         PApplet embed5 = new DisplayGoalsPerPlayer();
//         embed5.init();
//         add(embed5);
         
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.pack();
         this.setVisible(true);
         System.out.println(displayMatchTable.getPreferredSize());
     }

    public static void main(String[] args){
        new Main();
//        AppletViewer viewer = AppletLauncher.applet(FootballTableGUI.class).start();
        
    }
}
