package hk.edu.polyu.comp.comp2021.jungle.view.gui;

import hk.edu.polyu.comp.comp2021.jungle.Application;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class provide methods to load an image from the resources folder
 */
public class ImageLoader {
    /**
     * Loads an image from the Assets folder in resources
     *
     * @param imageName The filename of the image
     * @return The BufferedImage, null if not found
     */
    public static BufferedImage loadImageFromAssets(String imageName) {
        String path = String.format("/assets/%s", imageName);
        try {
            InputStream stream = Application.class.getResourceAsStream(path);
            return ImageIO.read(stream);
        } catch (IllegalArgumentException | IOException e) {
            System.out.format("Error: Unable to locate resources %s", path);
            return null;
        }
    }
}
