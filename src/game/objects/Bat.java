/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

/**
 *
 * @author Florian Kahllund and Philipp Meissner
 */
public class Bat extends GameObject {

    /**
     *  Constructor.
     * @param xPos
     * @param yPos
     * @param height
     * @param width
     * @param outputID
     */
    public Bat(double xPos, double yPos, double height, double width, int outputID) {
        super(xPos, yPos, height, width, outputID);
    }
}
