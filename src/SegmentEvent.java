import java.awt.image.BufferedImage;
import java.util.EventObject;

public class SegmentEvent extends EventObject {
    private int segmentationID;
    private String segParam;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SegmentEvent(Object source, int segmentationID, String segParam) {
        super(source);
        this.segmentationID = segmentationID;
        this.segParam = segParam;
    }

    public int getSegmentationID() {
        return segmentationID;
    }

    public void setSegmentationID(int segmentationID) {
        this.segmentationID = segmentationID;
    }

    public String getSegParam() {
        return segParam;
    }

    public void setSegParam(String segParam) {
        this.segParam = segParam;
    }
}
