package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
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
		/*JButton tl = new JButton("tl");
		JButton tr = new JButton("tr");
		JButton bl = new JButton("bl");
		JButton br = new JButton("br");
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		
		SequentialGroup horiz = layout.createSequentialGroup();
		
		ParallelGroup right = layout.createParallelGroup();
		right.addComponent(tl);
		right.addComponent(bl);
		horiz.addGroup(right);
		
		ParallelGroup left = layout.createParallelGroup();
		left.addComponent(tr);
		left.addComponent(br);
		horiz.addGroup(left);
		
		layout.setHorizontalGroup(horiz);
		
		SequentialGroup vert = layout.createSequentialGroup();
		
		ParallelGroup top = layout.createParallelGroup();
		top.addComponent(tl);
		top.addComponent(tr);
		vert.addGroup(top);
		
		ParallelGroup bottom = layout.createParallelGroup();
		bottom.addComponent(bl);
		bottom.addComponent(br);
		vert.addGroup(bottom);
		
		layout.setVerticalGroup(vert);*/
		
		//FILTER
		JPanel filterPanel = new JPanel();
		filterPanel.add(new JTextArea());
		filterPanel.add(new JButton("Press this button!!!"));
		filterPanel.setSize(new Dimension(150,390));
		//PIC
		BufferedImage croppedImage = image.getSubimage(200, 200, 400, 300);		
		JLabel picLabel = new JLabel(new ImageIcon(croppedImage));
		JPanel imagePanel = new JPanel();
		imagePanel.setSize(new Dimension(300,400));
		imagePanel.add(picLabel);
		
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
				
		SequentialGroup horizontal = layout.createSequentialGroup();
		
		ParallelGroup backFilter = layout.createParallelGroup();
		backFilter.addComponent(backButton);
		backFilter.addComponent(filterPanel);
		
		horizontal.addGroup(backFilter);
		
		ParallelGroup imgBut = layout.createParallelGroup();
		imgBut.addComponent(imagePanel);
		imgBut.addComponent(saveButton);
		horizontal.addGroup(imgBut);
		
		layout.setHorizontalGroup(horizontal);
		
		SequentialGroup vertical = layout.createSequentialGroup();
		vertical.addComponent(backButton);
		
		ParallelGroup filterPic = layout.createParallelGroup();
		filterPic.addComponent(filterPanel);
		filterPic.addComponent(imagePanel);
		vertical.addGroup(filterPic);
		
		vertical.addComponent(saveButton);
		
		layout.setVerticalGroup(vertical);
		
		/*ParallelGroup saveButtons = layout.createParallelGroup();
		saveButtons.addComponent(saveButton);
		saveButtons.addComponent(saveToMyScelfiesButton);
		saveButtons.addComponent(uploadToCommunityButton);*/
		
		/*
		SequentialGroup photoSave = layout.createSequentialGroup();
		photoSave.addComponent(imagePanel);
		photoSave.addGroup(saveButtons);
		horizontal.addGroup(photoSave);
		
		layout.setHorizontalGroup(horizontal);
		
		SequentialGroup vertical = layout.createSequentialGroup();
		vertical.addComponent(backButton);
		
		ParallelGroup filterPhoto = layout.createParallelGroup();
		filterPhoto.addComponent(filterPanel);
		filterPhoto.addComponent(imagePanel);
		vertical.addGroup(filterPhoto);
		
		vertical.addGroup(saveButtons);
		
		layout.setVerticalGroup(vertical);*/
		
		/*JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		backPanel.add(backButton);
		
		add(backPanel);
		
		/*
		JPanel filterPanel = new JPanel();
		filterPanel.add(new JTextArea());
		filterPanel.add(new JButton("Press this button!!!"));
		filterScroll.setSize(new Dimension(150,390));
		filterScroll.add(filterPanel);
		
		add(filterScroll);*/
		
		/*
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
		add(savePanel);*/
		
		//(300,400)
	}
}
