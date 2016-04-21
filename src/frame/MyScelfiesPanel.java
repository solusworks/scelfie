package frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import networking.ImageURL;
import networking.PHPReader;

public class MyScelfiesPanel extends JPanel {
	private static final long serialVersionUID = -5936110089691226347L;
	
	private Navigator navigator;
	
	private JPanel overallPanel;
	private JButton back;
	private JScrollPane scroll;
	
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
		add(back, BorderLayout.NORTH);
		overallPanel.setLayout(new BoxLayout(overallPanel, BoxLayout.Y_AXIS));
		
		List<JPanel> picPanels = new Vector<JPanel>();		
		List<ImageURL> urls = PHPReader.getImageList(navigator.getUsername());
		
		JPanel fourPanel;
		
		int urlsLeft = urls.size();
		int urlPos = 0;
		while(urlsLeft > 0)
		{
			fourPanel = new JPanel();
			int limit = urlPos + 4;
			for(int i = urlPos; i < limit; i++)
			{
				if(i < urls.size())
				{
					JLabel picToAdd = processURL(urls.get(i));
					fourPanel.add(picToAdd);
					fourPanel.add(Box.createGlue());
					urlPos++;
					urlsLeft--;
				}
			}
			
			picPanels.add(fourPanel);
			fourPanel = null;
		}
		
		
		for(int i = 0; i < picPanels.size(); i++)
		{
			overallPanel.add(picPanels.get(i));
		}
		overallPanel.add(Box.createGlue());
		overallPanel.add(Box.createGlue());
		overallPanel.add(Box.createGlue());
		overallPanel.add(Box.createGlue());
		
		scroll = new JScrollPane(overallPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(680,500));
		add(scroll, BorderLayout.CENTER);
	}
	
	private JLabel processURL(ImageURL url)
	{
		JLabel picLabel = null;
		
		String username = url.getUsername();
		String fname = url.getImageName();
		URL img = PHPReader.downloadImage(username, fname);
		try {
			BufferedImage realImg = ImageIO.read(img);
			Graphics g = realImg.createGraphics();
			g.drawImage(realImg, 0, 0, 150, 150, null, null);	
			ImageIcon finish = new ImageIcon(realImg.getSubimage(0, 0, 150, 150));
			picLabel = new JLabel(finish);
			return picLabel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
