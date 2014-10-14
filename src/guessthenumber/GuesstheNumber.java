/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

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
                }
                
                // At this point, we've determined that they've inputted a valid number.
                
                // Increment the number of guesses.
                guesses++;
                
                // If they guessed right...
                if (guessedNumber == number) {
                    correct = true;
                    return;
                }
                
                
                
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
        
        return 0;
    }
}
