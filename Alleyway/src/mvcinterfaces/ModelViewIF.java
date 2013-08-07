/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcinterfaces;

import java.util.List;

/**
 *  Interface for the ModelView 
 * @author florian
 */
public interface ModelViewIF {

    /**
     *Gets the bat.
     * @return bat
     */
    GameObjectIF getBat();

    /**
     *  Gets the balls.
     * @return balls
     */
    List<GameObjectIF> getBalls();

    /**
     *  Gets the bricks.    
     * @return bricks
     */
    List<GameObjectIF> getBricks();

    /**
     *  Gets the height.    
     * @return height
     */
    double getHeight();

    /**
     *  Gets the width.
     * @return width
     */
    double getWidth();
}
