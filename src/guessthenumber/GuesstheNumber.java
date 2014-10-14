/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
    
    public static void main(String[] args) {
        
        guessInputFrame = new GuessInputFrame();
        
        playGame();
        
        
    }
    
    // Returns 1 if play again.
    public static int playGame(){
        
        number = (int) (1000*Math.random()) + 1;
        
        guessInputFrame.setLabelText("I have a number between 1 and 1000. Can you guess my number? (" + number + ").");
        
        //
        guessInputFrame.text.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar()!='\n') return;
                
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
                
                int distanceToTarget = Math.abs(number - guessedNumber);
                
                // If we're getting hotter...
                if (distanceToTarget < lastDistanceToTarget){
                    guessInputFrame.text.setBackground(Color.red);
                } else {
                    guessInputFrame.text.setBackground(Color.blue);
                }
                // Set last distance to target to the one we just caclulated...
                lastDistanceToTarget = distanceToTarget; 
                
                if (guessedNumber < number){                    
                    guessInputFrame.feedback.setText("Your guess was UNDER!");
                } else {
                    guessInputFrame.feedback.setText("Your guess was OVER!");
                }
                // Append number of guesses remaining to the feedback label.
                guessInputFrame.feedback.setText(
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
        
        
        while (guesses != allowedGuesses && !correct);
        
        // If they ended up guessing correctly...
        if(correct){
            guessInputFrame.feedback.setText("You guessed correctly!");
        }
        // Else, this is what happens if they're wrong.
        else {
            guessInputFrame.feedback.setText("Boo shitlord");
            guessInputFrame.text.setEditable(false);
        }
        
        return 0;
    }
}
