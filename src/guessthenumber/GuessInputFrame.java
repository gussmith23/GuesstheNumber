/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
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
    
    private final Dimension d = new Dimension(350,550);
    JLabel label;
    JTextField text;
    JList previousGuessesList;
    JScrollPane previousGuessesListScrollPane;
    DefaultListModel<String> previousGuessesListListModel;
    
    public GuessInputFrame(){
        super("Guess the Number");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(d);
        
        previousGuessesListListModel = new DefaultListModel<String>();
        previousGuessesList = new JList(previousGuessesListListModel);
        previousGuessesList.setVisibleRowCount(7);
        previousGuessesListScrollPane = new JScrollPane(previousGuessesList);
        
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        label = new JLabel();
        text = new JTextField();
        text.setSize(10, 10);
        add(label);
        add(text);
        add(previousGuessesListScrollPane);
        setVisible(true);
        
        
    }
    
    
    public void setLabelText(String str){
        label.setText(str);
    }
    
    public void addItemToList(String item) {
        previousGuessesListListModel.add(previousGuessesListListModel.getSize(), item);
    }
    
}
