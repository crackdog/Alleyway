/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

/**
 *  Contains the moveable objects.
 * @author Florian Kahllund and Philipp Meissner
 */
public abstract class MoveableObject extends GameObject {

    private double xSpeed, ySpeed;
    private double nextX, nextY;
    private double ballSpeed;

    /**
     *  Constructor
     * @param xSpeed
     * @param ySpeed
     * @param xPos
     * @param yPos
     * @param h
     * @param w
     * @param outputID
     */
    public MoveableObject(double xSpeed, double ySpeed, double xPos, double yPos, double h, double w, int outputID) {
        super(xPos, yPos, h, w, outputID);
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.nextX = this.getxPos() + xSpeed;
        this.nextY = this.getyPos() + ySpeed;
        this.ballSpeed = 0.005;
    }

    /**
     *  Gets the x speed
     * @return xSpeed
     */
    public double getxSpeed() {
        return xSpeed;
    }

    /**
     *  Sets the x speed. 
     * @param xSpeed
     */
    public void setxSpeed(double xSpeed) {
        this.xSpeed = xSpeed;
    }

    /**
     *  Gets the y speed.
     * @return ySpeed
     */
    public double getySpeed() {
        return ySpeed;
    }

    /**
     *  Sets the y speed    
     * @param ySpeed
     */
    public void setySpeed(double ySpeed) {
        this.ySpeed = ySpeed;
    }

    /**
     *  Gets the next x coordinate.
     * @return nextX
     */
    public double getNextX() {
        return nextX;
    }

    /**
     *  Gets the next y coordinate. 
     * @return nextY
     */
    public double getNextY() {
        return nextY;
    }

    /**
     * Sets the next x coordinate.  
     * @param nextX
     */
    public void setNextX(double nextX) {
        this.nextX = nextX;
    }

    /**
     * Sets the next y coordinate.
     * @param nextY
     */
    public void setNextY(double nextY) {
        this.nextY = nextY;
    }

    /**
     * Moves the object to the next coordinate.
     */
    public void move() {
        this.setxPos(this.getxPos() + this.xSpeed);
        this.setyPos(this.getyPos() + this.ySpeed);
        this.nextX = this.getxPos() + this.xSpeed;
        this.nextY = this.getyPos() + this.ySpeed;
    }

    /**
     * Switches the x speed.
     */
    public void switchSpeedX() {
        this.xSpeed = -this.xSpeed;
    }

    /**
     * Switches the y speed.
     */
    public void switchSpeedY() {
        this.ySpeed = -this.ySpeed;
    }

    /**
     *  Normalizes the speed.
     */
    public void normalizeSpeed() {
        double len = Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
        xSpeed = (xSpeed / len) * ballSpeed;
        ySpeed = (ySpeed / len) * ballSpeed;
        nextX = this.getxPos() + xSpeed;
        nextY = this.getyPos() + ySpeed;
    }

    /**
     * Gets the balls speed.
     * @return ballSpeed
     */
    public double getBallSpeed() {
        return ballSpeed;
    }

    /**
     * Sets the balls speed.
     * @param ballSpeed
     */
    public void setBallSpeed(double ballSpeed) {
        this.ballSpeed = ballSpeed;
    }
}
