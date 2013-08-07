/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.objects.*;
import java.util.Iterator;

/**
 *  Calculates collisions within the field and times the movements.
 * @author Florian Kahllund and Philipp Meissner
 */
public class TimeThread extends Thread {

    // Declarations used for later usage.
    private Model game;
    private boolean moveLeft, moveRight, steps, running, onestep;

    /**
     *  Constructor.
     * @param gamemodel
     */
    public TimeThread(Model gamemodel) {
        game = gamemodel;
        moveLeft = false;
        moveRight = false;
        steps = false;
        running = true;
        onestep = false;
    }

    /**
     *  Moves the bat to the left.
     */
    public void moveLeft() {
        this.moveLeft = true;
    }

    /**
     *  Moves the bat to the right.
     */
    public void moveRight() {
        this.moveRight = true;
    }

    /**
     * Start or stops the game.
     * @param step
     */
    public void steps(boolean step) {
        this.steps = step;
    }

    /**
     * Makes a single step forward of the game.
     */
    public void step() {
        this.onestep = true;
        this.steps = true;
    }

    /**
     *  Contains the thread calculations
     */
    @Override
    public void run() {
        running = true;
        while (running) {
            if (steps) {
                if (onestep) {
                    steps = false;
                    onestep = false;
                }

                Iterator brickiter;
                Iterator balliter = game.getBalls().iterator();
                Ball ball;
                Brick brick;
                GameField field;
                boolean collision;

                Bat bat = (Bat) game.getBat();
                field = game.getField();
                // Iterates through all balls.
                while (balliter.hasNext()) {
                    collision = false;
                    ball = (Ball) balliter.next();

                    // as long as there are collisions in the step.
                    do {
                        collision = false;

                        double border, xo, xn, yo, yn, newY, newX;

                        //Calculates the bat collision
                        if (ball.getNextY() + ball.getHeight() >= bat.getyPos()) {

                            border = bat.getyPos();
                            xo = ball.getxPos();
                            xn = ball.getNextX();
                            yo = ball.getyPos() + ball.getHeight();
                            yn = ball.getNextY() + ball.getHeight();

                            newX = yn - yo;
                            newX /= ball.getySpeed();
                            newX *= ball.getxSpeed();
                            newX += ball.getxPos();

                            if (newX + ball.getWidth() >= bat.getxPos()
                                    && newX <= bat.getxPos() + bat.getWidth()) {

                                ball.setySpeed(bat.getWidth() / -4);
                                ball.setxSpeed(newX - (bat.getxPos() + bat.getWidth() / 2));

                                ball.setxPos(newX);
                                ball.setyPos(border - ball.getHeight());

                                ball.normalizeSpeed();

                                collision = true;
                            }
                        }

                        //Calculates collisions with the field.
                        if (!collision) {

                            if (ball.getNextX() < 0) {

                                border = 0;
                                xo = ball.getxPos();
                                xn = ball.getNextX();
                                yo = ball.getyPos();
                                yn = ball.getNextY();

                                newY = xn - xo;
                                newY /= ball.getxSpeed();
                                newY *= ball.getySpeed();
                                newY += ball.getyPos();

                                ball.switchSpeedX();
                                ball.setNextX(border - (xn - border));

                                ball.setyPos(newY);
                                ball.setxPos(border);

                                collision = true;


                            } else if (ball.getNextX() + ball.getWidth() > field.getWidth()) {


                                border = field.getWidth();
                                xo = ball.getxPos() + ball.getWidth();
                                xn = ball.getNextX() + ball.getWidth();
                                yo = ball.getyPos();
                                yn = ball.getNextY();

                                newY = xn - xo;
                                newY /= ball.getxSpeed();
                                newY *= ball.getySpeed();
                                newY += ball.getyPos();

                                ball.switchSpeedX();
                                ball.setNextX(border - ((xn + ball.getWidth()) - border));

                                ball.setyPos(newY);
                                ball.setxPos(border - ball.getWidth());

                                collision = true;
                            }

                            if (ball.getNextY() < 0) {

                                border = 0;
                                xo = ball.getxPos();
                                xn = ball.getNextX();
                                yo = ball.getyPos();
                                yn = ball.getNextY();

                                newX = yn - yo;
                                newX /= ball.getySpeed();
                                newX *= ball.getxSpeed();
                                newX += ball.getxPos();

                                ball.switchSpeedY();
                                ball.setNextY(border - (yn - border));

                                ball.setxPos(newX);
                                ball.setyPos(border);

                                collision = true;
                            }
                        }

                        //Calculates the brick collisions
                        brickiter = game.getBricks().iterator();
                        while (!collision && brickiter.hasNext()) {
                            brick = (Brick) brickiter.next();

                            //from top
                            border = brick.getyPos();
                            xo = ball.getxPos();
                            xn = ball.getNextX();
                            yo = ball.getyPos() + ball.getHeight();
                            yn = ball.getNextY() + ball.getHeight();

                            if (yo < border && yn >= border) {

                                newX = yn - yo;
                                newX /= ball.getySpeed();
                                newX *= ball.getxSpeed();
                                newX += ball.getxPos();

                                if (newX + ball.getWidth() >= brick.getxPos()
                                        && newX <= brick.getxPos() + brick.getWidth()) {

                                    ball.switchSpeedY();
                                    ball.setNextY(border - ((yn + ball.getWidth()) - border));

                                    ball.setxPos(newX);
                                    ball.setyPos(border - ball.getHeight());

                                    collision = true;
                                }
                            }


                            //from below
                            border = brick.getyPos() + brick.getHeight();
                            xo = ball.getxPos();
                            xn = ball.getNextX();
                            yo = ball.getyPos();
                            yn = ball.getNextY();

                            if (yo > border && yn <= border) {

                                newX = yn - yo;
                                newX /= ball.getySpeed();
                                newX *= ball.getxSpeed();
                                newX += ball.getxPos();

                                if (newX + ball.getWidth() >= brick.getxPos()
                                        && newX <= brick.getxPos() + brick.getWidth()) {

                                    ball.switchSpeedY();
                                    ball.setNextY(border - (yn - border));

                                    ball.setxPos(newX);
                                    ball.setyPos(border);

                                    collision = true;
                                }
                            }


                            //from left
                            border = brick.getxPos();
                            xo = ball.getxPos() + ball.getWidth();
                            xn = ball.getNextX() + ball.getWidth();
                            yo = ball.getyPos();
                            yn = ball.getNextY();

                            if (xo < border && xn >= border) {

                                newY = xn - xo;
                                newY /= ball.getxSpeed();
                                newY *= ball.getySpeed();
                                newY += ball.getyPos();

                                if (newY + ball.getHeight() >= brick.getyPos()
                                        && newY <= brick.getyPos()) {

                                    ball.switchSpeedX();
                                    ball.setNextX(border - ((xn + ball.getWidth()) - border));

                                    ball.setyPos(newY);
                                    ball.setxPos(border - ball.getWidth());

                                    collision = true;
                                }
                            }

                            //from right
                            border = brick.getxPos() + brick.getWidth();
                            xo = ball.getxPos();
                            xn = ball.getNextX();
                            yo = ball.getyPos();
                            yn = ball.getNextY();

                            if (xo > border && xn <= border) {

                                newY = xn - xo;
                                newY /= ball.getxSpeed();
                                newY *= ball.getySpeed();
                                newY += ball.getyPos();

                                if (newY + ball.getHeight() >= brick.getyPos()
                                        && newY <= brick.getyPos()) {

                                    ball.switchSpeedX();
                                    ball.setNextX(border - (xn - border));

                                    ball.setyPos(newY);
                                    ball.setxPos(border);

                                    collision = true;
                                }
                            }


                            // When there was/is a collision we destroy the brick that got hit.
                            if (collision) {
                                game.addDestroyedBrick(brick);
                            }
                        }
                    } while (collision);
                    // Moves the ball.
                    ball.move();
                    // If the ball is too low
                    if (ball.getyPos() > field.getHeight() - ball.getHeight()) {
                        game.remove(ball);
                    }
                }
                // Moves the bat to the left.
                if (moveLeft) {
                    double newX = bat.getxPos() - 0.04;
                    if (newX < 0) {
                        newX = 0;
                    }
                    // Sets the new position of the bat up.
                    bat.setxPos(newX);
                    this.moveLeft = false;
                }   // Moves the bat to the right.  
                if (moveRight) {
                    double newX = bat.getxPos() + 0.04;
                    if (newX + bat.getWidth() > field.getWidth()) {
                        newX = field.getWidth() - bat.getWidth();
                    }
                    // Sets the new position of the bat up.
                    bat.setxPos(newX);
                    this.moveRight = false;
                }
            }

            try {   // Wait for 35 milliseconds.
                Thread.sleep(30);
            } catch (InterruptedException ex) {
            }
        }
    }
}