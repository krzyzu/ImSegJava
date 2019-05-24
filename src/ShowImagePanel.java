import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class ShowImagePanel extends JPanel{
    public ShowImagePanel() {
            Dimension dim = getPreferredSize();
            dim.width = 150;
            setPreferredSize(dim);

            Border innerBorder = BorderFactory.createTitledBorder("Default Text");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
            setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }
}
