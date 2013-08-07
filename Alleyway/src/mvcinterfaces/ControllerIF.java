/*
 * To change this template, choose Tools | Templates
 * and open the template in the addBall.
 */
package mvcinterfaces;

/**
 *  Interface for the controller.
 * @author florian
 */
public interface ControllerIF {

    /**
     *  Adds a ball.
     */
    void addBall();

    /**
     * Starts the game.
     */
    void start();

    /**
     * Stops the game.
     */
    void stop();

    /**
     * Pauses the game.
     */
    void pause();

    /**
     *  Restarts the game.
     */
    void restart();

    /**
     *  Saves the game
     */
    void save();

    /**
     *  Loads the game.
     */
    void load();

    /**
     *  Steps through the game (one step).
     */
    void step();

    /**
     *  Moves the bat to the left
     */
    void moveLeft();

    /**
     * Moves the bat to the right.
     */
    void moveRight();

    /**
     * Adds a brick at a certain position.
     * @param xPos
     * @param yPos
     */
    void addBrick(double xPos, double yPos);

    /**
     * Removes a brick at a certain position.
     * @param xPos
     * @param yPos
     */
    void removeBrick(double xPos, double yPos);
}
