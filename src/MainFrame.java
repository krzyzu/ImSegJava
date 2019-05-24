

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
// 60:00
    private Toolbar toolbar;
    private UserPanel userPanel;
    private ShowImagePanel originImagePanel;
    private ShowImagePanel segmentedImagePanel;

    public MainFrame() {
        super("Image segmentation");
        toolbar = new Toolbar();
        userPanel = new UserPanel();
        originImagePanel = new ShowImagePanel();
        segmentedImagePanel = new ShowImagePanel();

        setLayout(new BorderLayout());
        add(toolbar,BorderLayout.NORTH);
        add(userPanel,BorderLayout.WEST);
        add(originImagePanel,BorderLayout.CENTER);
        add(segmentedImagePanel,BorderLayout.EAST);



        setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
