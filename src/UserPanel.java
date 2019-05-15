import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class UserPanel extends JPanel {
    public UserPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 150;
        setPreferredSize(dim);

        Border innerBorder = BorderFactory.createTitledBorder("User Panel");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));
    }
}
