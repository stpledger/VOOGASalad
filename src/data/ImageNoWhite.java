package data;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageNoWhite {

    public java.awt.Image convertAWT(java.awt.Image image) {
        BufferedImage original = (BufferedImage) image;
        BufferedImage whiteFilter = new BufferedImage(original.getWidth(),
                original.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = whiteFilter.createGraphics();
        for (int i = 0; i < whiteFilter.getWidth(); i++) {
            for (int j = 0; j < whiteFilter.getHeight(); j++) {
                if (original.getRGB(i, j) != Color.WHITE.getRGB()) {
                    whiteFilter.setRGB(i, j, original.getRGB(i, j));
                }
            }
        }
        g.dispose();

        return whiteFilter;
    }

    public Image convert(Image image) {
        BufferedImage whiteFilter = SwingFXUtils.fromFXImage(image, null);
        return SwingFXUtils.toFXImage((BufferedImage) convertAWT(whiteFilter), null);
    }

}
