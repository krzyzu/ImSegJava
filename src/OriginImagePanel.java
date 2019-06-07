import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.io.File; 
import java.util.Arrays; 
import javax.imageio.ImageIO;

public class OriginImagePanel extends JPanel{
    private ImagePanel imagePanel;

    public OriginImagePanel() {
        Border innerBorder = BorderFactory.createTitledBorder("Obraz Poczatkowy");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        imagePanel = new ImagePanel();
        // TODO set imagePanel
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
