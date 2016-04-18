package frame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class FilterPanel extends JPanel {
	private static final long serialVersionUID = -6638619041234820049L;
	private BufferedImage image;

    public FilterPanel(String filename) {
       try {                
          image = ImageIO.read(new File(filename));
       } catch (IOException ex) {
            // handle exception...
    	   ex.printStackTrace();
       }
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //image = image.getSubimage(0, 0, 100, 100);
        //g.drawImage(image, 0, 0, 100, 100, null, null);      
        g.drawImage(image, 0, 0, null);
    }
}
