/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import control.ResourceLoader;
import game.objects.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.swing.JOptionPane;
import mvcinterfaces.*;

/**
 * Model of the ModelViewController of Alleyway
 * @author Florian Kahllund and Philipp Meissner
 */
public class Model implements ModelControllerIF, ModelViewIF {

    // Declares some variables we use later on.
    private TimeThread time;
    private List<GameObjectIF> balls;
    private List<GameObjectIF> bricks;
    private List<GameObjectIF> destroyedbricks;
    private GameObjectIF bat;
    private GameField field;
    private boolean stop;

    /**
     * The constructor.
     */
    public Model() {
        time = new TimeThread(this);

        balls = new CopyOnWriteArrayList<GameObjectIF>();
        bricks = new CopyOnWriteArrayList<GameObjectIF>();
        field = new GameField();
        bat = new Bat(field.getWidth() / 2 - 0.075, field.getBatY(), 0.02, 0.15, 0);
        destroyedbricks = new CopyOnWriteArrayList<GameObjectIF>();
        stop = true;
    }

    /**
     * Initializes the timeThread.
     */
    public void init() {
        time.start();
        this.addBall();
    }

    /**
     * Removes a game object.
     * @param x
     */
    public void remove(GameObjectIF x) {
        if (x instanceof Ball) {
            balls.remove(x);
        } else if (x instanceof Brick) {
            bricks.remove(x);
        }
    }

    /**
     *  Adds a brick at a certain position.
     * @param xPos
     * @param yPos
     * @param outputID
     */
    @Override
    public void addBrick(double xPos, double yPos, int outputID) {
        this.removeBrick(xPos, yPos);
        bricks.add(newBrick(xPos, yPos, outputID));
    }

    /**
     * Generates a new brick at a certain position.
     * @param xPos
     * @param yPos
     * @param OutputID
     * @return Brick
     */
    private Brick newBrick(double xPos, double yPos, int OutputID) {
        return new Brick(toGridX(xPos), toGridY(yPos), field.getGridH(), field.getGridW(), OutputID);
    }

    /**
     * Calculates the gridposition from a given position.
     * @param xPos
     * @return xPos
     */
    private double toGridX(double xPos) {
        return field.toGridX(xPos);
    }

    /**
     * Calculates the gridposition from a given position.
     * @param yPos
     * @return yPos
     */
    private double toGridY(double yPos) {
        return field.toGridY(yPos);
    }

    /**
     *  Adds a new ball to the game.
     */
    @Override
    public void addBall() {
        balls.add(newBall());
    }

    /**
     * Generates a new standard ball.
     * @return Ball
     */
    private Ball newBall() {
        return new Ball(0, 0.003, 0.35, 0.6, 0.03, 0.03, 1);
    }

    /**
     * Starts the game.
     */
    @Override
    public void startGame() {
        time.steps(true);
        if (stop) {
            this.reset();
            stop = false;
        }
    }

    /**
     * Pauses the game.
     */
    @Override
    public void pauseGame() {
        time.steps(false);
    }

    /**
     * Stops the game.
     */
    @Override
    public void stopGame() {
        time.steps(false);
        stop = true;
        balls.clear();
        balls.add(newBall());
    }

    /**
     * Makes a single step forward (time-viewed).
     */
    @Override
    public void step() {
        time.step();
    }

    /**
     * Moves the bat to the left.
     */
    @Override
    public void moveLeft() {
        time.moveLeft();
    }

    /**
     * Moves the bat to the right.
     */
    @Override
    public void moveRight() {
        time.moveRight();
    }

    /**
     * Gets the bat and its information.
     * @return  bat
     */
    @Override
    public GameObjectIF getBat() {
        return bat;
    }

    /**
     * Gets the balls and their information.
     * @return balls
     */
    @Override
    public List<GameObjectIF> getBalls() {
        return balls;
    }

    /**
     * Gets the bricks and their information.
     * @return bricks
     */
    @Override
    public List<GameObjectIF> getBricks() {
        return bricks;
    }

    /**
     * Gets the height of the field in which the game is being displayed.
     * @return field.getHeight
     */
    @Override
    public double getHeight() {
        return field.getHeight();
    }

    /**
     * Gets the width of the field in which the game is being displayed.
     * @return field.getWidth
     */
    @Override
    public double getWidth() {
        return field.getWidth();
    }

    /**
     * Gets the field and its information.
     * @return field
     */
    public GameField getField() {
        return field;
    }

    /**
     * Adds a destroyed brick to a list that only contains said objects.
     * @param brick
     */
    public void addDestroyedBrick(Brick brick) {
        if (this.bricks.remove(brick)) {
            this.destroyedbricks.add(brick);
        }
    }

    /**
     * Resets the game.
     */
    public void reset() {
        this.bricks.addAll(destroyedbricks);
        this.destroyedbricks.clear();
    }

    /**
     * Removes the brick at a certain position.
     * @param xPos
     * @param yPos
     */
    @Override
    public void removeBrick(double xPos, double yPos) {
        Iterator iter = bricks.iterator();
        Brick brick;

        // as long as there is another brick left
        while (iter.hasNext()) {
            brick = (Brick) iter.next();
            // If there already is a brick at the given position it'll be removed.
            if (brick.getxPos() == field.toGridX(xPos)
                    && brick.getyPos() == field.toGridY(yPos)) {
                bricks.remove(brick);
            }
        }
    }

    /**
     * Saves the bricks.
     * @param filename
     */
    @Override
    public void save(String filename) {
        try {
            OutputStream os = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            Iterator iter = bricks.iterator();
            while (iter.hasNext()) {
                Brick brick = (Brick) iter.next();
                oos.writeObject(brick);
            }
            oos.close();
            os.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Problem while saving the file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads the bricks.
     * @param filename
     */
    @Override
    public void load(String filename) {
        ResourceLoader loader = new ResourceLoader();
        try {
            ObjectInputStream ois = loader.loadObjectFile(filename);
            bricks.clear();
            Brick brick;
            try {
                while (true) {
                    brick = (Brick) ois.readObject();
                    bricks.add(brick);
                }
            } catch (Exception e) {
            }
            ois.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problem while loading the file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
