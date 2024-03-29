
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame implements MenuListener, ActionListener {

	private JMenuBar bar;
	private JMenu application;
	private JMenuItem exit;
    private UserPanel userPanel;
    private OriginImagePanel originImagePanel;
    private SegmentedImagePanel segmentedImagePanel;
    private BufferedImage segmentableImage;
    private int panelWidth= 1000;
    private int panelHeight = 700;

    public MainFrame() {
        super("Image segmentation");
        bar = new JMenuBar();
        application = new JMenu("Menu");
		exit = new JMenuItem("Zamknij");
		exit.addActionListener(this);
		application.add(exit);
		bar.add(application);
		this.setJMenuBar(bar);
        userPanel = new UserPanel();
        userPanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        originImagePanel = new OriginImagePanel();
        originImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        segmentedImagePanel = new SegmentedImagePanel();
        segmentedImagePanel.setPreferredSize(new Dimension(panelWidth/3,panelHeight/3));
        segmentableImage = null;

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
                segmentableImage = originImagePanel.getImage();
                int id = e.getSegmentationID();

                if (id == 0) {
                    int k = Integer.parseInt(e.getSegParam());
                    ColorSegmentation color = new ColorSegmentation(segmentableImage);
                    BufferedImage resultcolor = color.segmentize(k);
                    segmentedImagePanel.setImage(resultcolor);
                }

                if (id == 1) {
                    int k = Integer.parseInt(e.getSegParam());
                    int mode = 1;
                    Means kmeans = new Means();
                    BufferedImage dstImage = kmeans.calculate(segmentableImage, k, mode);
                    segmentedImagePanel.setImage(dstImage);
                }

                if (id == 2) {

                	Threshold thresh = new Threshold();
                	BufferedImage resulttresh = thresh.apply(segmentableImage);
                	segmentedImagePanel.setImage(resulttresh);
                }
            }
        });

        layoutComponents();
    }
    private void layoutComponents() {

        setLayout(new BorderLayout());
        add(userPanel,BorderLayout.WEST);
        add(originImagePanel,BorderLayout.CENTER);
        add(segmentedImagePanel,BorderLayout.EAST);
        setLocation(500,100);
        setSize(panelWidth,panelHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(exit)) {
			System.exit(0);
		}
	}
}
