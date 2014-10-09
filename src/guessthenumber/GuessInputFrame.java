/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author hfs5022
 */
public class GuessInputFrame extends JFrame {
    
    private final Dimension d = new Dimension(350,550);
    JLabel label;
    JTextField text;
    
    public GuessInputFrame(){
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        label = new JLabel();
        text = new JTextField();
        add(label);
        add(text);
        setVisible(true);
    }
    
    
    public void setLabelText(String str){
        label.setText(str);
    }
}
