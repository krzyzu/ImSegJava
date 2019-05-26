

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
// 60:00
    private Toolbar toolbar;
    private UserPanel userPanel;
    private OriginImagePanel originImagePanel;
    private SegmentedImagePanel segmentedImagePanel;


    public MainFrame() {
        super("Image segmentation");
        int panelWidth= 1000;
        int panelHeight = 700;
        toolbar = new Toolbar();
        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        originImagePanel = new OriginImagePanel();
        originImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        segmentedImagePanel = new SegmentedImagePanel();
        segmentedImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));

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
