import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;

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
       // GridBagConstraints gbc = new GridBagConstraints();
       // gbc.weightx = 1;
       // gbc.weighty = 0.1;
       // gbc.gridx = 0;
       // gbc.gridy = 0;
       // gbc.insets = new Insets(0,0,0,5);
       // gbc.fill = GridBagConstraints.NONE;
       // gbc.anchor = GridBagConstraints.LINE_END;
        add(imagePanel, BorderLayout.CENTER);
    }

    public void setImage(BufferedImage image) {
        imagePanel.setImage(image);
    }

}
