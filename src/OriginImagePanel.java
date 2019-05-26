import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OriginImagePanel extends JPanel{
    public OriginImagePanel() {

        Border innerBorder = BorderFactory.createTitledBorder("Obraz Poczatkowy");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }
}
