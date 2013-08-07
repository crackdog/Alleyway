/*
 * To change this template, choose Tools | Templates
 * and open the template in the addBall.
 */
package control;

import mvcinterfaces.*;

/**
 *
 * @author Florian Kahllund and Philipp Meissner
 */
public class Controller implements ControllerIF {

    private ModelControllerIF model;
    private ViewIF view;

    /**
     * Constructor.
     * @param m
     * @param v
     */
    public Controller(ModelControllerIF m, ViewIF v) {
        this.model = m;
        this.view = v;
    }

    /**
     *  Calls the start method of the model.
     */
    @Override
    public void start() {
        model.startGame();
    }

    /**
     *  Calls the restart method of the model.
     */
    @Override
    public void restart() {
        model.stopGame();
        model.startGame();
    }

    /**
     *  Calls the step method of the model.
     */
    @Override
    public void step() {
        model.step();
    }

    /**
     *  Calls the movelLeft method of the model.
     */
    @Override
    public void moveLeft() {
        model.moveLeft();
    }

    /**
     *  Calls the moveRight method of the model.   
     */
    @Override
    public void moveRight() {
        model.moveRight();
    }

    /**
     *  Calls the stop method of the model.
     */
    @Override
    public void stop() {
        model.stopGame();
    }

    /**
     *  Calls the pause method of the model.
     */
    @Override
    public void pause() {
        model.pauseGame();
    }

    /**
     * Calls the addBall method of the model.
     */
    @Override
    public void addBall() {
        model.addBall();
    }

    /**
     * Calls the addBrick method of the model.
     * @param xPos
     * @param yPos
     */
    @Override
    public void addBrick(double xPos, double yPos) {
        model.addBrick(xPos, yPos, view.getBrickID());
    }

    /**
     *  Calls the removeBrick method of the model.
     * @param xPos
     * @param yPos
     */
    @Override
    public void removeBrick(double xPos, double yPos) {
        model.removeBrick(xPos, yPos);
    }

    /**
     * Calls the save method of the model.
     */
    @Override
    public void save() {
        model.save("level.dat");
    }

    /**
     *  Calls the load method of the model.
     */
    @Override
    public void load() {
        model.load("level.dat");
    }
}
