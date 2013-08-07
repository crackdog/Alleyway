/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.io.Serializable;

/**
 *
 * @author Florian Kahllund and Philipp Meissner
 */
public class Ball extends MoveableObject {

    /**
     *  Constructor.
     * @param xSpeed
     * @param ySpeed
     * @param xPos
     * @param yPos
     * @param height
     * @param width
     * @param outputID
     */
    public Ball(double xSpeed, double ySpeed, double xPos, double yPos, double height, double width, int outputID) {
        super(xSpeed, ySpeed, xPos, yPos, height, width, outputID);
        this.normalizeSpeed();
    }
}
