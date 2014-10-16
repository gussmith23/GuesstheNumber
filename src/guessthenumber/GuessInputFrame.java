/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    
    private boolean correctBoolean = false;
    private int guesses = 0;
    private final int allowedGuesses = 7;
    private final String errorMessage = "Please input a valid integer.";
    private int lastDistanceToTarget = Integer.MAX_VALUE;
    private final Color GREEN = new Color(128,255,0);
    private int number;
    
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
        
        // Add button listener.
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        text.addKeyListener(new TextBoxListener());
        
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
    
    private void reset(){
        // Reset/regenerate key values.
        correctBoolean = false;
        guesses = 0;
        lastDistanceToTarget = Integer.MAX_VALUE;        
        number = (int) (1000*Math.random()) + 1;
        
        // Set up labels.
        setLabelText("I have a number between 1 and 1000. Can you guess my number?");
        setFeedbackText("Feedback on your input will be displayed here.");
        
        text.setEditable(true);
        text.setText("");
    }
    
    private void endGame() {
        // If they ended up guessing correctly...
        if(correctBoolean){
            setFeedbackText("You guessed correctly!");
        } else {
            setFeedbackText("You used up all of your tries.");
        }
        
        // In the end, we display the correct answer in the box (and make it uneditable). 
        text.setEditable(false);
        text.setBackground(GREEN);
        text.setText("" + number);
        System.out.println("Test");
    }
    
    private class TextBoxListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

            // If it's not the enter key...
            if (e.getKeyChar()!='\n') return;
            // ...or if there's no text entered...
            if (text.getText().length() == 0) return;
            // ...or if we've used all of our guesses, then return.
            if (guesses == allowedGuesses) return;

            // We try to parse a number out of the text box.
            int guessedNumber;
            try {
                guessedNumber = Integer.parseInt(text.getText());
            } catch (NumberFormatException exception){
                exception.printStackTrace();
                feedback.setText(errorMessage);
                return;
            } finally {
                // Clear the text block.
                text.setText("");
            }

            // At this point, we've determined that they've inputted a valid number.

            // Increment the number of guesses.
            guesses++;

            // If they guessed right...
            if (guessedNumber == number) {
                correctBoolean = true;
            }
            
            // If the game should be ending...
            if (guesses == allowedGuesses || correctBoolean){
                endGame();
                return;
            }

            // Calculating how close they were...
            int distanceToTarget = Math.abs(number - guessedNumber);

            // If we're getting hotter or colder...
            if (distanceToTarget < lastDistanceToTarget){
                text.setBackground(Color.red);
            } else {
                text.setBackground(Color.cyan);
            }
            // Set last distance to target to the one we just caclulated...
            lastDistanceToTarget = distanceToTarget; 

            // Now we calculate under/over.
            if (guessedNumber < number){                    
                feedback.setText("Your guess was UNDER!");
                System.out.println("under");
            } else {
                feedback.setText("Your guess was OVER!");
                System.out.println("over");
            }

            // Append number of guesses remaining to the feedback label.
            if(guesses < allowedGuesses) feedback.setText(
                    String.format(
                            "%s Guesses left: %d",
                            feedback.getText(),
                            allowedGuesses - guesses)
                    );

            return;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            return;
        }

        @Override
        public void keyReleased(KeyEvent e) {
            return;
        }
        
    }
    
}
