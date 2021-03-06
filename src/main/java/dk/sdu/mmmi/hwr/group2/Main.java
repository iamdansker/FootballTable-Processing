/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.hwr.group2;

import dk.sdu.mmmi.hwr.group2.graphs.DisplayGeneralStatistics;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayGoalsDistribution;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayGoalsOverTheDay;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayGoalsPerPlayer;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayGoalsPerWeekDay;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayMatchDuritations;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayMatchTable;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayMatchesPerWeekday;
import dk.sdu.mmmi.hwr.group2.graphs.DisplayWinRate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class Main extends JFrame {

    public Main() {
        super("Embedded PApplet");

        setBackground(Color.WHITE);
        setLayout(new BorderLayout());
        DisplayMatchTable displayMatchTable = new DisplayMatchTable();
        add(displayMatchTable, BorderLayout.CENTER);
        DisplayGeneralStatistics displayGeneralStatistics = new DisplayGeneralStatistics();
        add(displayGeneralStatistics, BorderLayout.EAST);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(0, 3));
        northPanel.setBackground(Color.WHITE);

        JPanel topSouthPanel = new JPanel();
        topSouthPanel.setLayout(new GridLayout(0, 2));
        topSouthPanel.setBackground(Color.WHITE);

        add(topPanel, BorderLayout.NORTH);
        topPanel.add(northPanel, BorderLayout.NORTH);
        topPanel.add(topSouthPanel, BorderLayout.SOUTH);

        PApplet goalsPerWeekDay = new DisplayGoalsPerWeekDay();
        goalsPerWeekDay.init();

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());
        jPanel.setBackground(Color.WHITE);

        PApplet winRate = new DisplayWinRate();
        winRate.init();
        jPanel.add(winRate, BorderLayout.NORTH);

        PApplet goalsPerPlayer = new DisplayGoalsPerPlayer();
        goalsPerPlayer.init();
        jPanel.add(goalsPerPlayer, BorderLayout.SOUTH);

        PApplet matchesPerWeekday = new DisplayMatchesPerWeekday();
        matchesPerWeekday.init();

        PApplet matchDuritations = new DisplayMatchDuritations();
        matchDuritations.init();

        DisplayGoalsOverTheDay goalsOverTheDay = new DisplayGoalsOverTheDay();
        goalsOverTheDay.init();

        DisplayGoalsDistribution goalsDistribution = new DisplayGoalsDistribution();
        goalsDistribution.init();

        northPanel.add(goalsPerWeekDay);
        northPanel.add(matchesPerWeekday);
        northPanel.add(matchDuritations);
        topSouthPanel.add(goalsOverTheDay);
        topSouthPanel.add(goalsDistribution);
        add(jPanel, BorderLayout.WEST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Main();
//        AppletViewer viewer = AppletLauncher.applet(FootballTableGUI.class).start();

    }
}
