import java.util.EventListener;

public interface FormListener extends EventListener {
    public void formEventOccured(FormEvent e);
    public void segmentEventOccured(SegmentEvent e);
}
