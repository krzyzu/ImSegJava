import java.awt.image.BufferedImage;
import java.util.EventObject;

public class SegmentEvent extends EventObject {
    private String segmentationType;
    private int segmentationID;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SegmentEvent(Object source, String segmentationType, int segmentationID) {
        super(source);
        this.segmentationType = segmentationType;
        this.segmentationID = segmentationID;
    }

    public String getSegmentationType() {
        return segmentationType;
    }

    public void setSegmentationType(String segmentationType) {
        this.segmentationType = segmentationType;
    }

    public int getSegmentationID() {
        return segmentationID;
    }

    public void setSegmentationID(int segmentationID) {
        this.segmentationID = segmentationID;
    }

}
