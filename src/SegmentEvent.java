import java.awt.image.BufferedImage;
import java.util.EventObject;

public class SegmentEvent extends EventObject {
    private int segmentationID;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SegmentEvent(Object source, int segmentationID) {
        super(source);
        this.segmentationID = segmentationID;
    }

    public int getSegmentationID() {
        return segmentationID;
    }

    public void setSegmentationID(int segmentationID) {
        this.segmentationID = segmentationID;
    }

}
