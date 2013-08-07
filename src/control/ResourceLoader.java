/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.io.ObjectInputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author Florian Kahllund and Philipp Meissner
 */
public class ResourceLoader {
    
    private String rPath;
    
    public ResourceLoader() {
        rPath = File.separator + "resources" + File.separator;
    }
    
    public ResourceLoader(String resourcePath) {
        this.rPath = File.separator + resourcePath + File.separator;
    }
    
    private InputStream getInputStream(String resourcename) throws Exception {
        return this.getClass().getResourceAsStream(rPath + resourcename);
    }

    public BufferedImage loadImageFile(String resourcename) throws Exception {
        return ImageIO.read(this.getInputStream(resourcename));
    }
    
    public ObjectInputStream loadObjectFile(String resourcename) throws Exception {
        return new ObjectInputStream(this.getInputStream(resourcename));
    }
}
