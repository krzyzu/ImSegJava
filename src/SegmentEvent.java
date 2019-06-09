import java.awt.image.BufferedImage;
import java.util.EventObject;

public class SegmentEvent extends EventObject {
    private String segmentationType;
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SegmentEvent(Object source, String segmentationType) {
        super(source);
        this.segmentationType = segmentationType;
    }

    public String getSegmentationType() {
        return segmentationType;
    }

    public void setSegmentationType(String segmentationType) {
        this.segmentationType = segmentationType;
    }
  
}
