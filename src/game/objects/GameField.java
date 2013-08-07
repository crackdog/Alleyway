/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game.objects;

/**
 *  Contains the field.
 * @author Florian Kahllund and Philipp Meissner
 */
public class GameField {

    private double width, height;
    private double gridW, gridH;
    private double batY, brickYmin;

    /**
     *  Constructor of the field.
     */
    public GameField() {
        this.width = 0.675;
        this.height = 1.0;
        this.gridW = 0.075;//width / 14;
        this.gridH = 0.025;
        this.batY = height - 4 * gridH;
        this.brickYmin = height - 6 * gridH;
    }

    /**
     *  Gets the height.
     * @return height
     */
    public double getHeight() {
        return height;
    }

    /**
     *  Gets the width.
     * @return width
     */
    public double getWidth() {
        return width;
    }

    /**
     * Gets the gridheight.
     * @return gridH
     */
    public double getGridH() {
        return gridH;
    }

    /**
     * Gets the gridwidth.
     * @return gridW
     */
    public double getGridW() {
        return gridW;
    }

    /**
     * Gets the bat y position.
     * @return batY
     */
    public double getBatY() {
        return batY;
    }

    /**
     * Gets the brick minumum y position.
     * @return brickYmin    
     */
    public double getBrickYmin() {
        return brickYmin;
    }

    /**
     * Calculates the given position to the grid.
     * @param xPos
     * @return xPos
     */
    public double toGridX(double xPos) {

        xPos = gridW * Math.floor(xPos / gridW);
        if (xPos > width - gridW) {
            xPos = width - gridW;

        } else if (xPos < 0) {
            xPos = 0;
        }
        return xPos;
    }

    /**
     * Calculates the given position to the grid.
     * @param yPos
     * @return yPos
     */
    public double toGridY(double yPos) {
        //yPos -= yPos % gridH;
        yPos = gridH * Math.floor(yPos / gridH);
        if (yPos > brickYmin - gridH) {
            yPos = brickYmin - gridW;
        } else if (yPos < 0) {
            yPos = 0;
        }
        return yPos;
    }
}