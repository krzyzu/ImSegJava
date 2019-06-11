import java.awt.image.BufferedImage;

public class Histogram {

    public static int[] histogtam(BufferedImage image,int width, int height) {
        int interval[] = new int[256];
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                int p = RGB.getRGBValue( image, width, height, i, j);
                int r = (p >> 16) & 255;
                interval[r]++;
            }
        }
        return interval;

    }

}