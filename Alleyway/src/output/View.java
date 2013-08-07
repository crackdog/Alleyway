/*
 * To change this template, choose Tools | Templates
 * and open the template in the addBall.
 */
package output;

import javax.swing.*;
import mvcinterfaces.*;
import java.awt.*;
import java.awt.event.*;

/** 
 *  The view.
 * @author Florian Kahllund and Philipp Meissner
 */
public class View extends JFrame implements ViewIF {
    // Some declarations for later usage.

    private LeftPanel leftPanel;
    private OutputPanel gamePanel;
    private ControllerIF ctrl;
    private RepaintThread rpThread;

    /**
     *  Constructor
     */
    public View() {
        // Sets our frame up.
        super("AlleyWay Reloaded v.0.1 Beta");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(660, 500));
        this.setLayout(new BorderLayout());
        this.addKeyListener(new MyKeyListener());

        // Creates all panels.
        leftPanel = new LeftPanel(new MyActionListener());
        gamePanel = new OutputPanel();

        // Adds all panels to our frame
        this.add(leftPanel, BorderLayout.WEST);
        this.add(gamePanel, BorderLayout.CENTER);
    }

    /**
     *  Starts the thread and initializes the frame.
     */
    public void init() {
        this.pack();
        this.setSize(850, 700);    // Sets the size of our frame
        this.setLocationRelativeTo(null);  // Sets the frames location centered (relative to our screen)
        this.setResizable(true);   // Makes the frame resizable
        this.setVisible(true);     // and visible (comes in quite handy at times)


        rpThread = new RepaintThread(gamePanel);
        rpThread.start();
        String images[] = {"bat.png",
            "ball.png",
            "red.png",
            "blue.png",
            "green.png",
            "orange.png"};
        gamePanel.init(images);



    }

    /**
     *  Sets a controller to our frame and gamePanel.
     * @param ctrl
     */
    @Override
    public void setController(ControllerIF ctrl) {
        this.ctrl = ctrl;
        this.gamePanel.setController(ctrl);
    }

    /**
     * Sets a model to the gamePanel.
     * @param game
     */
    @Override
    public void setModel(ModelViewIF game) {
        gamePanel.setGame(game);
    }

    /**
     *  Gets the current brickID.
     * @return brickID
     */
    @Override
    public int getBrickID() {
        return leftPanel.getBrickID();
    }

    /**
     * Contains the ActionListener.
     */
    public class MyActionListener implements ActionListener {

        /**
         * Catches/sets up actions when elements were clicked. 
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ctrl != null) {
                if (e.getActionCommand().equals("Start")) {
                    ctrl.start();
                } else if (e.getActionCommand().equals("Stop")) {
                    ctrl.stop();
                } else if (e.getActionCommand().equals("Load")) {
                    ctrl.load();
                } else if (e.getActionCommand().equals("Save")) {
                    ctrl.save();
                } else if (e.getActionCommand().equals("Pause")) {
                    ctrl.pause();
                } else if (e.getActionCommand().equals("Restart")) {
                    ctrl.restart();
                } else if (e.getActionCommand().equals("Stepper")) {
                    ctrl.step();
                } else if (e.getActionCommand().equals("Add Ball")) {
                    ctrl.addBall();
                }
            }
        }
    }

    /**
     * Contains the KeyListener 
     */
    public class MyKeyListener extends KeyAdapter {

        /**
         * Cares about the keys (that are being pressed)
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            if (ctrl != null) {

                switch (e.getKeyCode()) {
                    // Indicates the start
                    case (KeyEvent.VK_SPACE):
                    case (KeyEvent.VK_UP):
                        ctrl.start();
                        break;

                    // Indicates the usage of 'left-button'
                    case (KeyEvent.VK_LEFT):
                        ctrl.moveLeft();
                        break;

                    // Indicates the usage of 'right-button'
                    case (KeyEvent.VK_RIGHT):
                        ctrl.moveRight();
                        break;

                    //Press 'a' for addBall...
                    case (KeyEvent.VK_A):
                        ctrl.addBall();
                        break;

                    // In case a 'wrong' key was pressed we just break again
                    default:
                        break;
                }
            }
        }
    }
}