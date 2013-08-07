/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

import java.io.Serializable;
import mvcinterfaces.GameObjectIF;

/**
 *  Contains the game objects
 * @author Florian Kahllund and Philipp Meissner
 */
public abstract class GameObject implements GameObjectIF, Serializable {

    private double xPos, yPos, height, width;
    private int outputID;

    /**
     * Default constructor for serialization.
     */
    public GameObject() {
    }

    /**
     * Default Constructor
     * @param xPos
     * @param yPos
     * @param height
     * @param width
     * @param outputID
     */
    public GameObject(double xPos, double yPos, double height, double width, int outputID) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.height = height;
        this.width = width;
        this.outputID = outputID;
    }

    /**
     *  Gets the height.
     * @return height
     */
    @Override
    public double getHeight() {
        return height;
    }

    /**
     *  Sets the height up.
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     *  Gets the output ID.
     * @return outputID
     */
    @Override
    public int getOutputID() {
        return outputID;
    }

    /**
     *  Sets an output ID.
     * @param outputID
     */
    public void setOutputID(int outputID) {
        this.outputID = outputID;
    }

    /**
     *  Gets the witdh.
     * @return width
     */
    @Override
    public double getWidth() {
        return width;
    }

    /**
     *  Sets the width.
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     *  Gets the x position.
     * @return xPos
     */
    @Override
    public double getxPos() {
        return xPos;
    }

    /**
     *  Sets the x position.
     * @param xPos
     */
    public void setxPos(double xPos) {
        this.xPos = xPos;
    }

    /**
     *  Gets the y position.    
     * @return yPos
     */
    @Override
    public double getyPos() {
        return yPos;
    }

    /**
     *  Sets the y position.
     * @param yPos
     */
    public void setyPos(double yPos) {
        this.yPos = yPos;
    }
}
