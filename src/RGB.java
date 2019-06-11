import java.awt.image.BufferedImage;

public class RGB {

 public static int getRGBValue(BufferedImage image, int width, int height, int i, int j) {

        i = Math.max(0, Math.min(width - 1, i));
        j = Math.max(0, Math.min(height - 1, j));
        return image.getRGB(i, j);
    }

}