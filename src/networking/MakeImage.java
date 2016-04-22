package networking;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MakeImage implements Runnable {
	private URL img;
	private volatile JLabel picLabel;
	
	public MakeImage(URL img) {
		this.img = img;
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			BufferedImage realImg = ImageIO.read(img);
			Graphics g = realImg.createGraphics();
			g.drawImage(realImg, 0, 0, 150, 150, null, null);	
			ImageIcon finish = new ImageIcon(realImg.getSubimage(0, 0, 150, 150));
			picLabel = new JLabel(finish);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JLabel getImage() {
		return picLabel;
	}
	
}
