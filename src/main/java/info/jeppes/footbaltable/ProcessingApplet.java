/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.jeppes.footbaltable;

import java.awt.Color;
import java.awt.Dimension;
import processing.core.PApplet;

/**
 *
 * @author jeppe
 */
public class ProcessingApplet extends PApplet{

    private Dimension size;
    public ProcessingApplet(int width, int height){
        size = new Dimension(width, height);
        setBackground(Color.WHITE);
    }

    @Override
    public void setup() {
        
        size(size.width, size.height);
        super.setup();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return size;
    }
    
}
