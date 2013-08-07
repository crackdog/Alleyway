/* 
 * To change this template, choose Tools | Templates 
 * and open the template in the addBall. 
 */
package output;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;
import javax.swing.*;
import mvcinterfaces.*;

/**
 *  Panel for our gaming field.
 * @author Philipp
 */
public class OutputPanel extends JPanel {

    private ModelViewIF game;
    private BufferedImage imgarr[];
    private ControllerIF ctrl;

    /**
     *  Constructor.
     */
    public OutputPanel() {
        // Initializes the panel and variables.
        super();
        game = null;
        imgarr = null;
        // Further adds a MouseListener to this panel.
        this.addMouseListener(new MyMouseListener());
    }

    /**
     * Sets the game up.
     * @param g
     */
    public void setGame(ModelViewIF g) {
        game = g;
    }

    /**
     * Sets the Controller up.
     * @param ctrl 
     */
    public void setController(ControllerIF ctrl) {
        this.ctrl = ctrl;
    }

    /**
     * Iterates through the array of images to load them all up.
     * @param images
     */
    public void init(String images[]) {
        
        control.ResourceLoader loader = new control.ResourceLoader();

        imgarr = new BufferedImage[images.length];

        for (int i = 0; i < images.length; i++) {
            try {
                imgarr[i] = loader.loadImageFile(images[i]);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "There is no such element.", "Wrong ID", JOptionPane.ERROR_MESSAGE);
            }
        }
        // Finally repaints all images to make them appear on the panel.
        this.repaint();
    }

    /**
     * In here all components (balls, bricks, bat) will be drawn accurately.
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // When there is a game initialized
        if (game != null && imgarr != null) {

            // Declarations of variables we need later on
            GameObjectIF currentGO;
            Iterator iter;

            // Draws the gamefield dynamically sized to reduce the hassle of static sizes.
            int size;
            size = (int) (game.getHeight() * this.getSize().getHeight() - 40); // 20 above, 20 below

            // We use this in order to keep the aspect ratio.
            g2d.translate(20, 20);

            // Draws the rectangle in which the actual game will be displayed.
            g2d.setColor(Color.lightGray);
            g2d.fillRect(0, 0, (int) (game.getWidth() * size), size);


            // Draws the bat
            currentGO = game.getBat();
            g2d.drawImage(imgarr[currentGO.getOutputID()],
                    (int) (currentGO.getxPos() * size),
                    (int) (currentGO.getyPos() * size),
                    (int) (currentGO.getWidth() * size),
                    (int) (currentGO.getHeight() * size), this);


            iter = game.getBalls().iterator();
            while (iter.hasNext()) {
                currentGO = (GameObjectIF) iter.next();

                g2d.drawImage(imgarr[currentGO.getOutputID()],
                        (int) (currentGO.getxPos() * size),
                        (int) (currentGO.getyPos() * size),
                        (int) (currentGO.getWidth() * size),
                        (int) (currentGO.getHeight() * size), this);
            }



            iter = game.getBricks().iterator();

            while (iter.hasNext()) {
                currentGO = (GameObjectIF) iter.next();

                g2d.drawImage(imgarr[currentGO.getOutputID()],
                        (int) (currentGO.getxPos() * size),
                        (int) (currentGO.getyPos() * size),
                        (int) (currentGO.getWidth() * size),
                        (int) (currentGO.getHeight() * size), this);
            }

        } else {
            g2d.setBackground(Color.LIGHT_GRAY);
            g2d.clearRect(0, 0, 1000, 1000);
        }
    }

    /**
     * Our MouseListener to track the mouse actions.
     */
    public class MyMouseListener extends MouseAdapter {

        /**
         *  Catches all mouseclicks.
         * @param e
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // When a controller is attached.
            if (ctrl != null) {
                int size;

                size = (int) (game.getHeight() * getSize().getHeight() - 20 - 20); // 20 above, 20 below

                if ( // If click is valid (within the actual drawed rectangle)
                        (e.getX() >= 20) && (e.getY() >= 20) && // Makes sure the click is within the top-left corner
                        (e.getX() <= size + 20) && (e.getY() <= size + 20)) {    // and the bottom-right corner of our rectangle

                    // If it's a leftclick
                    if (e.getButton() == MouseEvent.BUTTON1) {
                        ctrl.addBrick(((double) (e.getX() - 20) / size), ((double) (e.getY() - 20) / size));
                        // we add another brick at the position we want it to be

                        // If it's a rightclick
                    } else if (e.getButton() == MouseEvent.BUTTON3) {
                        ctrl.removeBrick(((double) (e.getX() - 20) / size), ((double) (e.getY() - 20) / size));
                        // we remove the brick clicked.
                    }
                }
            }
        }
    }
}
