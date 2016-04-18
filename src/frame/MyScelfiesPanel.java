package frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyScelfiesPanel extends JPanel {
	private static final long serialVersionUID = -5936110089691226347L;
	
	private Navigator navigator;
	
	private JPanel overallPanel;
	private JButton back;
	private JPanel scelfiesPanel;
	
	public MyScelfiesPanel(ScelfieFrame inNav)
	{
		navigator = inNav;
		
		initializeVariables();
		addActionListeners();
		createGUI();
	}
	
	private void initializeVariables()
	{
		overallPanel = new JPanel();
		back = new JButton("Back");
		scelfiesPanel = new JPanel();
	}
	
	private void addActionListeners()
	{
		back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				navigator.toHome();
			}
			
		});
	}
	
	private void createGUI()
	{
		setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));
		overallPanel.add(back);
		
		String [] files = {"testfiles/heart.png", "testfiles/star.png", "testfiles/btop.png"};
		scelfiesPanel.setLayout(new BoxLayout(scelfiesPanel, BoxLayout.X_AXIS));
		for(int i = 0; i < 3; i++)
		{
			JLabel add = createImageLabel(files[i]);
			scelfiesPanel.add(add);
		}
		
		overallPanel.add(scelfiesPanel);
		
		add(overallPanel);
	}
	
	JLabel createImageLabel(String fname)
	{
		JLabel label;
		ImageIcon orig = new ImageIcon(fname);
		Image img = orig.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 100, 100, null, null);	
		ImageIcon finish = new ImageIcon(bi.getSubimage(0, 0, 100, 100));
		label = new JLabel(finish);
		return label;
	}
}