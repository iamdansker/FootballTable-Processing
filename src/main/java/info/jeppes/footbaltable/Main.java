/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package info.jeppes.footbaltable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class Main extends JFrame{
     public Main() {
         super("Embedded PApplet");

         setLayout(new BorderLayout());
         
         this.setSize(new Dimension(500, 500));
         this.setPreferredSize(new Dimension(500, 500));
         this.setVisible(true);
         
         PApplet embed = new FootballTableGUI();
         add(embed, BorderLayout.CENTER);
         
         embed.init();
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         
     }

    public static void main(String[] args){
        new Main();
//        AppletViewer viewer = AppletLauncher.applet(FootballTableGUI.class).start();
        
    }
}
