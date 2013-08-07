/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package output;

/**
 *  Responsible for repainting the field.
 * @author Florian Kahllund and Philipp Meissner
 */
public class RepaintThread extends Thread {

    private OutputPanel out;

    /**
     *  Constructor
     * @param o
     */
    public RepaintThread(OutputPanel o) {
        this.out = o;
    }

    /**
     *  Does all the work in regards of repainting.
     */
    @Override
    public void run() {
        while (true) {
            out.repaint();
            try {
                Thread.sleep(40);
            } catch (InterruptedException ex) {
            }
        }
    }
}
