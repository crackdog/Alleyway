/*
 * To change this template, choose Tools | Templates
 * and open the template in the addBall.
 */
package control;

import output.*;
import game.*;

/**
 *
 * @author Florian Kahllund and Philipp Meissner
 */
public class Main {

    /**
     *  Sets our frame, panels and basically whole GUI via calls up.
     * @param args
     */
    public static void main(String args[]) {
        
        Model game = new Model();        
        View frame = new View();
        Controller ctrl = new Controller(game, frame);
        
        // Gives the frame a view and controller
        frame.setController(ctrl);
        frame.setModel(game);
        
        // Initiation of our frame and game
        frame.init();
        game.init();
        
    }
}
