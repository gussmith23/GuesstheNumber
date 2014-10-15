/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 *
 * @author hfs5022
 */
public class GuesstheNumber {

    private static int number;
    private static GuessInputFrame guessInputFrame;
    private static boolean correct = false;
    private static int guesses = 0;
    private static final int allowedGuesses = 7;
    private static final String errorMessage = "Please input a valid integer.";
    private static int lastDistanceToTarget = Integer.MAX_VALUE;
    public static boolean reset = false;
    private static final Color GREEN = new Color(128,255,0);
    
    public static void main(String[] args) {
               
        // This loop theoretically won't be escaped. The program can only end
        //      when the user hits the 'X' button.
        boolean playAgain = true;
        while(playAgain){
            playAgain = playGame();
        }
        
        
    }
    
    // Returns 1 if play again.
    public static boolean playGame(){
        
        // Make a new window.
        guessInputFrame = new GuessInputFrame();
        
        // Reset/regenerate key values.
        correct = false;
        guesses = 0;
        lastDistanceToTarget = Integer.MAX_VALUE;        
        number = (int) (1000*Math.random()) + 1;
        
        // Set up labels.
        guessInputFrame.setLabelText("I have a number between 1 and 1000. Can you guess my number?");
        guessInputFrame.setFeedbackText("Feedback on your input will be displayed here.");
        
        // Add button listener.
        guessInputFrame.reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset = true;
            }
        });
        
        // Add listener for Enter key.
        guessInputFrame.text.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                
                // If it's not the enter key...
                if (e.getKeyChar()!='\n') return;
                // ...or if there's no text entered...
                if (guessInputFrame.text.getText().length() == 0) return;
                // ...or if we've used all of our guesses, then return.
                if (guesses == allowedGuesses) return;
                
                // We try to parse a number out of the text box.
                int guessedNumber;
                try {
                    guessedNumber = Integer.parseInt(guessInputFrame.text.getText());
                } catch (NumberFormatException exception){
                    exception.printStackTrace();
                    guessInputFrame.feedback.setText(errorMessage);
                    return;
                } finally {
                    // Clear the text block.
                    guessInputFrame.text.setText("");
                }
                
                // At this point, we've determined that they've inputted a valid number.
                
                // Increment the number of guesses.
                guesses++;
                
                // If they guessed right...
                if (guessedNumber == number) {
                    correct = true;
                    return;
                }
                
                // Calculating how close they were...
                int distanceToTarget = Math.abs(number - guessedNumber);
                
                // If we're getting hotter or colder...
                if (distanceToTarget < lastDistanceToTarget){
                    guessInputFrame.text.setBackground(Color.red);
                } else {
                    guessInputFrame.text.setBackground(Color.cyan);
                }
                // Set last distance to target to the one we just caclulated...
                lastDistanceToTarget = distanceToTarget; 
                
                // Now we calculate under/over.
                if (guessedNumber < number){                    
                    guessInputFrame.feedback.setText("Your guess was UNDER!");
                    System.out.println("under");
                } else {
                    guessInputFrame.feedback.setText("Your guess was OVER!");
                    System.out.println("over");
                }
                
                // Append number of guesses remaining to the feedback label.
                if(guesses < allowedGuesses) guessInputFrame.feedback.setText(
                        String.format(
                                "%s Guesses left: %d",
                                guessInputFrame.feedback.getText(),
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
             
        });
        
        // We wait for some state change - i.e. they use their guesses,
        //      they guess right, or they hit New Game.
        while (guesses != allowedGuesses && !correct && !reset);
        
        // If they hit New Game...
        if(reset) {
            // Get rid of the window.
            guessInputFrame.dispose();
            // Reset this immediately.
            reset = false;
            // Return true so the main func will start a new game.
            return true;
        }
        
        // If they ended up guessing correctly...
        if(correct){
            guessInputFrame.setFeedbackText("You guessed correctly!");
        } else {
            guessInputFrame.setFeedbackText("You used up all of your tries.");
            System.out.println("bab");
        }
        
        // In the end, we display the correct answer in the box (and make it uneditable). 
        guessInputFrame.text.setEditable(false);
        guessInputFrame.text.setBackground(GREEN);
        guessInputFrame.text.setText("" + number);
        
        // Wait for them to press the New Game button...
        while(!reset);
        
        // Once we do, we trash this current window...
        guessInputFrame.dispose();
        
        // ...and we reset this var ASAP.
        reset = false;
        
        // Returning true will cause the main function to open another game.
        return true;
    }
}
