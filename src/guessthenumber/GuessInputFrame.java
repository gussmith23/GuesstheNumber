/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

/**
 *
 * @author hfs5022
 */
public class GuessInputFrame extends JFrame {
    
    private final Dimension d = new Dimension(400,120);
    JLabel label;
    JTextField text;
    JLabel feedback;
    JButton reset;
    
    public GuessInputFrame(){
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);        
        
        setLayout(new FlowLayout(FlowLayout.CENTER));
        label = new JLabel();
        text = new JTextField(4);
        feedback = new JLabel();
        reset = new JButton();
        
        reset.setText("New Game");
        
        add(reset);
        add(label);
        add(text);
        add(feedback);
        setVisible(true);
        
    }
    
    
    public void setLabelText(String str){
        label.setText(str);
    }
    
    public void setFeedbackText(String str){
        feedback.setText(str);
    }
    
}
