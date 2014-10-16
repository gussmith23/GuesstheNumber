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
               
        guessInputFrame = new GuessInputFrame();
        
    }
    
}
