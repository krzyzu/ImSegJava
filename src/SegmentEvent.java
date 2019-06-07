import java.awt.image.BufferedImage;
import java.util.EventObject;

public class SegmentEvent extends EventObject {
    private BufferedImage bufferedImage;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SegmentEvent(Object source) {
        super(source);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
