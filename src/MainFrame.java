

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    private Toolbar toolbar;
    private UserPanel userPanel;
    private OriginImagePanel originImagePanel;
    private SegmentedImagePanel segmentedImagePanel;
    private int panelWidth= 1000;
    private int panelHeight = 700;

    public MainFrame() {
        super("Image segmentation");
        toolbar = new Toolbar();
        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        originImagePanel = new OriginImagePanel();
        originImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        segmentedImagePanel = new SegmentedImagePanel();
        segmentedImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));

        userPanel.setUserListener(new FormListener() {
            @Override
            public void formEventOccured(FormEvent e) {
                String imagePath = e.getPath();
                try {
                    BufferedImage originImage = null;
                    originImage = ImageIO.read(new File(imagePath));
                    originImagePanel.setImage(originImage);
                }
                catch (IOException ioe) {
                    System.out.println("Error while reading image");
                    System.exit(-1);
                }
            }
            @Override
            public void segmentEventOccured(SegmentEvent e) {
                // TODO receive segmented image and show it in SegmentedImagePanel
            }
        });

        layoutComponents();
    }
    private void layoutComponents() {

        setLayout(new BorderLayout());
        add(toolbar,BorderLayout.NORTH);
        add(userPanel,BorderLayout.WEST);
        add(originImagePanel,BorderLayout.CENTER);
        add(segmentedImagePanel,BorderLayout.EAST);



        setLocation(500,100);
        setSize(panelWidth,panelHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
