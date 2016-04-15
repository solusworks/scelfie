package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EditPanel extends JPanel {
	private static final long serialVersionUID = -5186980309061986267L;
	
	private Navigator navigator;
	private BufferedImage image;
	
	private JButton backButton;
	private JButton saveButton;
	private JButton saveToMyScelfiesButton;
	private JButton uploadToCommunityButton;
	
	private JScrollPane filterScroll;
	
	public EditPanel(ScelfieFrame inNav) {
		// TODO Auto-generated constructor stub
		navigator = inNav;
		image = navigator.getImage();
		
		initializeVariables();
		addActionListeners();
		createGUI();
	}

	private void initializeVariables()
	{
		backButton = new JButton("Back");
		saveButton = new JButton("Save");
		saveToMyScelfiesButton = new JButton("Save to My SCelfies");
		uploadToCommunityButton = new JButton("Upload to Community Album");
		filterScroll = new JScrollPane(new JTextArea());
	}

	private void addActionListeners()
	{
		
	}

	private void createGUI()
	{
		JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel.add(backButton);
		
		add(backPanel);
		
		/*
		JPanel filterPanel = new JPanel();
		filterPanel.add(new JTextArea());
		filterPanel.add(new JButton("Press this button!!!"));
		filterScroll.setSize(new Dimension(150,390));
		filterScroll.add(filterPanel);
		
		add(filterScroll);*/
		
		JPanel filter = new JPanel();
		filter.setBackground(new Color(100,20,255));
		filter.setSize(new Dimension(150,390));
		add(filter);
		
		JPanel savePanel = new JPanel();
		savePanel.add(saveButton);
		savePanel.add(saveToMyScelfiesButton);
		savePanel.add(uploadToCommunityButton);

		BufferedImage croppedImage = image.getSubimage(200, 200, 400, 300);
		
		JLabel picLabel = new JLabel(new ImageIcon(croppedImage));
		JPanel imagePanel = new JPanel();
		imagePanel.setSize(new Dimension(300,400));
		imagePanel.add(picLabel);
		
		add(imagePanel);
		add(savePanel);
		
		//(300,400)
	}
}
