/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable.graphs;

import info.jeppes.footbaltable.Match;
import info.jeppes.footbaltable.Utils;
import info.jeppes.footbaltable.graphs.uielements.MatchTableElement;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.TreeMap;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author jeppe
 */
public class DisplayMatchTable extends JPanel{
    private JPanel panel;

    public DisplayMatchTable() {
        init();
        loadData();
    }

    private void init() {
        this.setLayout(new BorderLayout());
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    public void loadData(){
        TreeMap<Calendar, Match> allMatches = Utils.getAllMatches(true);
        panel.removeAll();
        
        for(Match match : allMatches.values()){
            MatchTableElement matchTableElement = new MatchTableElement(match);
            panel.add(matchTableElement);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension preferredSize = super.getPreferredSize();
        if(preferredSize.height > 400){
            return new Dimension(preferredSize.width,400);
        }
        return preferredSize;
    }
}
