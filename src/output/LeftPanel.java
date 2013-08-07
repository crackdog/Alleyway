/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Philipp
 */
public class LeftPanel extends JPanel {

    // Global variables used within this class.
    private JLabel redbrick, bluebrick, orangebrick, greenbrick;
    private ImageIcon red, blue, yellow, green;
    private ButtonGroup radioGroup;
    private JRadioButton blueButton, greenButton, redButton, orangeButton;
    private JPanel buttonPanel, editorPanel;
    private JButton startButton, pauseButton, loadButton,
            saveButton, stepButton, restartButton, stopButton,
            ballButton;

    /**
     *  Constructor.
     * @param action
     */
    public LeftPanel(ActionListener action) {
        // Generates a panel with a Borderlayout.
        super();
        this.setLayout(new BorderLayout());



        // Creates all buttons.
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        restartButton = new JButton("Restart");
        stepButton = new JButton("Stepper");
        stopButton = new JButton("Stop");
        ballButton = new JButton("Add Ball");



        // Adds all buttons to our panel responsible for buttons.
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2, 5, 5));

        // Generates all buttons and makes them viewable but not focusable.
        // Also adds them to our actionlistener.
        buttonPanel.add(startButton);
        startButton.setVisible(true);
        startButton.setFocusable(false);
        startButton.addActionListener(action);

        buttonPanel.add(pauseButton);
        pauseButton.setVisible(true);
        pauseButton.setFocusable(false);
        pauseButton.addActionListener(action);

        buttonPanel.add(loadButton);
        loadButton.setVisible(true);
        loadButton.setFocusable(false);
        loadButton.addActionListener(action);

        buttonPanel.add(saveButton);
        saveButton.setVisible(true);
        saveButton.setFocusable(false);
        saveButton.addActionListener(action);

        buttonPanel.add(restartButton);
        restartButton.setVisible(true);
        restartButton.setFocusable(false);
        restartButton.addActionListener(action);

        buttonPanel.add(stepButton);
        stepButton.setVisible(true);
        stepButton.setFocusable(false);
        stepButton.addActionListener(action);

        buttonPanel.add(stopButton);
        stopButton.setVisible(true);
        stopButton.setFocusable(false);
        stopButton.addActionListener(action);

        buttonPanel.add(ballButton);
        ballButton.setVisible(true);
        ballButton.setFocusable(false);
        ballButton.addActionListener(action);



        // Generates a second panel in which we display all images and radiobuttons.
        editorPanel = new JPanel();
        editorPanel.setLayout(new GridLayout(2, 4));
        
        //resource loading test ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        control.ResourceLoader loader = new control.ResourceLoader();

        // Makes all images not focusable and enters them to our panel.
        redbrick = new JLabel();
        try {
            red = new ImageIcon(loader.loadImageFile("red.png"));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        redbrick.setIcon(red);
        redbrick.setFocusable(false);
        editorPanel.add(redbrick);

        bluebrick = new JLabel();
        try {
            blue = new ImageIcon(loader.loadImageFile("blue.png"));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        bluebrick.setIcon(blue);
        bluebrick.setFocusable(false);
        editorPanel.add(bluebrick);

        greenbrick = new JLabel();
        try {
            green = new ImageIcon(loader.loadImageFile("green.png"));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        greenbrick.setIcon(green);
        greenbrick.setFocusable(false);
        editorPanel.add(greenbrick);

        orangebrick = new JLabel();
        try {
            yellow = new ImageIcon(loader.loadImageFile("orange.png"));
        } catch (Exception ex) {
            System.err.println(ex);
        }
        orangebrick.setIcon(yellow);
        orangebrick.setFocusable(false);
        editorPanel.add(orangebrick);


        radioGroup = new ButtonGroup();

        redButton = new JRadioButton("Red");
        redButton.setFocusable(false);
        radioGroup.add(redButton);
        editorPanel.add(redButton);

        blueButton = new JRadioButton("Blue");
        blueButton.setFocusable(false);
        radioGroup.add(blueButton);
        editorPanel.add(blueButton);

        greenButton = new JRadioButton("Green");
        greenButton.setFocusable(false);
        radioGroup.add(greenButton);
        editorPanel.add(greenButton);

        orangeButton = new JRadioButton("Orange");
        orangeButton.setFocusable(false);
        radioGroup.add(orangeButton);
        editorPanel.add(orangeButton);

        redButton.setSelected(true);

        // Last but not least we make all the images, buttons and radiobuttons appear nice.
        this.add(buttonPanel, BorderLayout.NORTH);
        this.add(editorPanel, BorderLayout.SOUTH);
    }

    /**
     *  Gets the brick ID.
     * @return brickID
     */
    public int getBrickID() {
        if (redButton.isSelected()) {
            return 2;
        } else if (blueButton.isSelected()) {
            return 3;
        } else if (greenButton.isSelected()) {
            return 4;
        } else {
            return 5;
        }
    }
}
