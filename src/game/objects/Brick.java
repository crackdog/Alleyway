/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.io.Serializable;

/**
 *  Implies a brick.
 * @author Florian Kahllund and Philipp Meissner
 */
public class Brick extends GameObject implements Serializable {

    /**
     * Default constructor for serialization.
     */
    public Brick() {
    }

    /**
     *  Default constructor.
     * @param xPos
     * @param yPos
     * @param height
     * @param width
     * @param outputID
     */
    public Brick(double xPos, double yPos, double height, double width, int outputID) {
        super(xPos, yPos, height, width, outputID);
    }
}