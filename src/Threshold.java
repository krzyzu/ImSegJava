import java.awt.image.BufferedImage;

public class Threshold {

    private int width;
    private int height;

    public BufferedImage apply(BufferedImage bufferedImage) {
        int p, r;
        this.width = bufferedImage.getWidth();
        this.height = bufferedImage.getHeight();

        double thresholdValue = OtsuThresholdValue.calculate(bufferedImage, width, height);

        BufferedImage imageOutput = new BufferedImage(this.width,this.height, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < bufferedImage.getWidth(); i++) {
            for (int j = 0; j < bufferedImage.getHeight(); j++) {

                r = RGB.getRGBValue(bufferedImage,width,height, i, j);
                r = ((r >> 16) & 0xff);

                if (r > thresholdValue) {
                    p = 255;
                } else {
                    p = 0;
                }
                p = (p << 16) | (p << 8) | (p);
                imageOutput.setRGB(i, j, p);

            }
        }

        return imageOutput;
    }


}