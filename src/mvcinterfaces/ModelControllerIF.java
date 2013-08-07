/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcinterfaces;

/**
 *  Interface for the MOdelController.
 * @author florian
 */
public interface ModelControllerIF {

    /**
     *  Adds a brick of a specific ID at a certain position.
     * @param xPos
     * @param yPos
     * @param outputID
     */
    void addBrick(double xPos, double yPos, int outputID);

    /**
     * Removes a brick at a certain position.
     * @param xPos
     * @param yPos
     */
    void removeBrick(double xPos, double yPos);

    /**
     *  Adds a ball.
     */
    void addBall();

    /**
     *  Starts the game.
     */
    void startGame();

    /**
     *  Pauses the game.
     */
    void pauseGame();

    /**
     *  Stops the game.
     */
    void stopGame();

    /**
     *  Steps through the game.
     */
    void step();

    /**
     *  Moves the bat to the left.
     */
    void moveLeft();

    /**
     *  Moves the bat to the right. 
     */
    void moveRight();

    /**
     *  Saves the game.
     * @param title
     */
    void save(String title);

    /**
     *  Loads a game.
     * @param title
     */
    void load(String title);
}
