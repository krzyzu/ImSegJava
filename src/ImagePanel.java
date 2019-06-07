import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ImagePanel extends JPanel {
	private BufferedImage image = null;

	public ImagePanel() {

	}

	public void setImage(BufferedImage image) {
		if (image != null) {
			this.image = image;
			// this.setPreferredSize(new Dimension(image.getWidth(),image.getHeight()));
			this.repaint();
		}
	}

	@Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        if (image == null) {
			return;
		}
		int panelx;
		int panely;
		double pomx;
		double pomy;
		double pomall;
		int nowyx;
		int nowyy;

		panelx = this.getWidth();
		panely = this.getHeight();

		if (image.getWidth() <= panelx && image.getHeight() <= panely) {
			g2d.drawImage(image, null, 0, 0);
		} 
		else {

			pomx = image.getWidth() / (double) panelx;
			pomy = image.getHeight() / (double) panely;
			if (pomx > pomy) {
				pomall = pomx;
			} else {
				pomall = pomy;
			}

			nowyx = (int) (image.getWidth() / pomall);
			nowyy = (int) (image.getHeight() / pomall);

			BufferedImage image2 = new BufferedImage(nowyx, nowyy, BufferedImage.TYPE_INT_RGB);// np +100 do nowyx to
																								// czarne
			Graphics2D grafika2 = image2.createGraphics();
			grafika2.drawImage(image, 0, 0, nowyx, nowyy, null);
			g2d.drawImage(image2, null, 0, 0);
		}
	}
}
