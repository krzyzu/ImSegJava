import java.awt.image.BufferedImage;

public class SegmentableImage extends BufferedImage {
    private String path;
    public SegmentableImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
