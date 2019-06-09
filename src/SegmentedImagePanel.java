import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

public class SegmentedImagePanel extends JPanel{
    private ImagePanel imagePanel;
    public SegmentedImagePanel() {
            Border innerBorder = BorderFactory.createTitledBorder("Obraz Wysegmentowany");
            Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
            setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        imagePanel = new ImagePanel();
        // TODO set image in imagePanel
        // imagePanel.setImage();
        layoutComponents();
    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
           add(imagePanel, BorderLayout.CENTER);
    }
    
    public void setImage(BufferedImage image) {
        imagePanel.setImage(image);
    }
}
