import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SegmentedImagePanel extends JPanel{
    public SegmentedImagePanel() {

            Border innerBorder = BorderFactory.createTitledBorder("Obraz Wysegmentowany");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
            setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }
}
