/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcinterfaces;

/**
 *  Interface for the view.
 * @author florian
 */
public interface ViewIF {

    /**
     *  Gives the view a controller.
     * @param controller
     */
    void setController(ControllerIF controller);

    /**
     *  Gives the view a game.
     * @param game
     */
    void setModel(ModelViewIF game);

    /**
     *  Gets the BrickID.
     * @return brickID
     */
    int getBrickID();
}
