/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable.graphs.uielements;

import info.jeppes.footbaltable.Match;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

/**
 *
 * @author jeppe
 */
public class MatchTableElement extends JPanel implements MouseListener{
    private static final long serialVersionUID = 1L;
    private final Match match;
    private Border exitBorder;
    private Border enterBorder;

    public MatchTableElement(Match match) {
        this.match = match;
        init();
    }

    private void init() {
        setLayout(new BorderLayout());
        
//        exitBorder = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        exitBorder = BorderFactory.createRaisedSoftBevelBorder();
        enterBorder = BorderFactory.createLoweredSoftBevelBorder();
        setBorder(exitBorder);
        
        addMouseListener(this);
        
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel, BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createEmptyBorder(2,10,2,10));
        JLabel matchLabel = new JLabel("Match ID: "+match.getId());
        
        int player1Score = match.getGoalsByPlayer(1).size();
        int player2Score = match.getGoalsByPlayer(2).size();
        JLabel player1ScoreLabel = new JLabel(String.valueOf(player1Score));
        player1ScoreLabel.setForeground(player1Score > player2Score ? Color.GREEN : player1Score == player2Score ? Color.GRAY : Color.RED);
        JLabel player2ScoreLabel = new JLabel(String.valueOf(player2Score));
        player2ScoreLabel.setForeground(player1Score < player2Score ? Color.GREEN : player1Score == player2Score ? Color.GRAY : Color.RED);
        
        JPanel scorePanel = new JPanel();
        panel.add(matchLabel,BorderLayout.CENTER);
        scorePanel.setLayout(new BorderLayout());
        scorePanel.add(player1ScoreLabel,BorderLayout.WEST);
        scorePanel.add(new JLabel(" - "),BorderLayout.CENTER);
        scorePanel.add(player2ScoreLabel,BorderLayout.EAST);
        panel.add(scorePanel,BorderLayout.EAST);
    }
    
    public Match getMatch(){
        return match;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(enterBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(exitBorder);
    }
}
