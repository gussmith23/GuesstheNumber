/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package guessthenumber;

import javax.swing.JFrame;

/**
 *
 * @author hfs5022
 */
public class GuesstheNumber {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        GuessInputFrame guessInputFrame = new GuessInputFrame();
        
        guessInputFrame.setLabelText("First guess:");
        guessInputFrame.addItemToList("Test 1");
        guessInputFrame.addItemToList("Test 2");
    }
    
}
