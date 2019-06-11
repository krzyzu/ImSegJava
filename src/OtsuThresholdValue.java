import java.awt.image.BufferedImage;

public class OtsuThresholdValue {

    public static int calculate(BufferedImage image, int width, int height) {
        int histogram[] = Histogram.histogtam(image,width, height );

        int total = width * height;
        float sum = 0;
        for (int i = 0; i < 256; i++) {
            sum += i * histogram[i];
        }
        float sum_bg = 0;
        int wight_bg = 0, wight_fg = 0;
        float varMax = 0;
        int threshold = 0;
        for (int i = 0; i < 256; i++) {
            wight_bg += histogram[i];
            if (wight_bg == 0) {
                continue;
            }
            wight_fg = total - wight_bg;
            if (wight_fg == 0) {
                break;
            }
            sum_bg += (float) (i * histogram[i]);
            float mean_bg = sum_bg / wight_bg;
            float mean_fg = (sum - sum_bg) / wight_fg;
            float varBetween = (float) wight_bg * (float) wight_fg * (mean_bg - mean_fg) * (mean_bg - mean_fg);
            if (varBetween > varMax) {
                varMax = varBetween;
                threshold = i;
            }
        }
        return threshold;
    }
}