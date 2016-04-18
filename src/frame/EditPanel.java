package frame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
	private JButton doneButton;
	private JPanel filterPanel;
	private JLabel heartFilter;
	private JLabel flowerFilter;
	private JLabel kissFilter;
	private JLabel starFilter;
	private JLabel picLabel;
	
	private JScrollPane filterScroll;
	
	private JFileChooser fileChooser;
	
	//SM HERE
	//private ScelfieManager scelfieManager;
	
	//Boolean to keep track of whether the Scelfie has been saved in one of the three ways
	private Boolean saved;
	
	//Boolean to keep track of whether there is a filter on the Scelfie
	private Boolean filtered;
	
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
		uploadToCommunityButton = new JButton("Share with Community");
		filterScroll = new JScrollPane(new JTextArea());
		doneButton = new JButton("Done");
		
		fileChooser = new JFileChooser();
		filterPanel = new JPanel();
		ImageIcon heartIcon = createFilterIcon("testfiles/heart.png", 80);
		heartFilter = new JLabel(heartIcon);
		ImageIcon starIcon = createFilterIcon("testfiles/star.png", 80);
		starFilter = new JLabel(starIcon);
		ImageIcon flowerIcon = createFilterIcon("testfiles/flower.png", 80);
		flowerFilter = new JLabel(flowerIcon);
		ImageIcon kissIcon = createFilterIcon("testfiles/kiss.png", 80);
		kissFilter = new JLabel(kissIcon);
		
		saved = false;
		filtered = false;
		
		//SM HERE
		//scelfieManager = new ScelfieManager();
		//scelfieManager.loadTestImage(navigator.getImageFile());
	}

	private void addActionListeners()
	{
		backButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				goBack();
			}
		});
		saveButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				saveScelfie();
			}
		});
		saveToMyScelfiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(navigator.getRegistered())
				{
					saveToMy();
				}
				else {
					JOptionPane.showMessageDialog(null, "Register to create My SCelfies album", "Error", JOptionPane.WARNING_MESSAGE);
				}
		}});
		uploadToCommunityButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(navigator.getRegistered())
				{
					uploadToCommunity();
				}
				else {
					JOptionPane.showMessageDialog(null, "Register to share with Community", "Error", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				done();
			}
		});
		
		heartFilter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(!filtered)
				{
					filtered = true;
				}
				JOptionPane.showMessageDialog(null, "HEART", "Error", JOptionPane.WARNING_MESSAGE);
				//scelfieManager.addStickerToImg("testfiles/heart.png",FacialFeature.Eye);
				//picLabel.setIcon(scelfieManager.getEditedImg());
			}
		});
		starFilter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(!filtered)
				{
					filtered = true;
				}
				JOptionPane.showMessageDialog(null, "STAR", "Error", JOptionPane.WARNING_MESSAGE);
				//scelfieManager.addStickerToImg("testfiles/star.png",FacialFeature.Eye);
				//picLabel.setIcon(scelfieManager.getEditedImg());
			}
		});
		flowerFilter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(!filtered)
				{
					filtered = true;
				}
				JOptionPane.showMessageDialog(null, "FLOWER", "Error", JOptionPane.WARNING_MESSAGE);
				//scelfieManager.addStickerToImg("testfiles/flower.png",FacialFeature.Eye);
				//picLabel.setIcon(scelfieManager.getEditedImg());
			}
		});
		kissFilter.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e) {
				if(!filtered)
				{
					filtered = true;
				}
				JOptionPane.showMessageDialog(null, "KISS", "Error", JOptionPane.WARNING_MESSAGE);			
				//scelfieManager.addStickerToImg("testfiles/kiss.png",FacialFeature.Mouth);
				//picLabel.setIcon(scelfieManager.getEditedImg());
			}
		});
	}

	private void createGUI()
	{
		//DONE
		//JPanel doneButtonPan = new JPanel();
		//doneButtonPan.add(doneButton, BorderLayout.EAST);
		//doneButtonPan.add(Box.createGlue());
		//doneButtonPan.add(Box.createGlue());
		//doneButtonPan.add(Box.createGlue());
		//doneButtonPan.add(doneButton, FlowLayout.RIGHT);
		
		//FILTER
		
		filterPanel.setLayout(new BoxLayout(filterPanel, BoxLayout.Y_AXIS));
		//filterPanel.add(new JTextArea());
		filterPanel.setSize(new Dimension(150,390));
		
		//FilterPanel heart = new FilterPanel("testfiles/heart.png");
		
		
		//filterScroll.add(heart);
		
		filterPanel.add(heartFilter);
		filterPanel.add(flowerFilter);
		filterPanel.add(starFilter);
		filterPanel.add(kissFilter);
		//ImageIcon filter = createFilterIcon("testfiles/heart.png");
		//filterScroll.add(filter);
		
		//PIC
		BufferedImage croppedImage = image.getSubimage(200, 200, 400, 300);		
		picLabel = new JLabel(new ImageIcon(croppedImage));
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
		imgBut.addComponent(doneButton);
		imgBut.addComponent(imagePanel);
		
		JPanel saveButtons = new JPanel();
		//ParallelGroup saveButtons = layout.createParallelGroup();
		saveButtons.add(saveButton);
		saveButtons.add(saveToMyScelfiesButton);
		saveButtons.add(uploadToCommunityButton);
		
		imgBut.addComponent(saveButtons);
		horizontal.addGroup(imgBut);
		
		layout.setHorizontalGroup(horizontal);
		
		SequentialGroup vertical = layout.createSequentialGroup();
		//vertical.addComponent(backButton);
		
		
		ParallelGroup backDone = layout.createParallelGroup();
		backDone.addComponent(backButton);
		backDone.addComponent(doneButton, GroupLayout.Alignment.TRAILING);
		vertical.addGroup(backDone);
		
		ParallelGroup filterPic = layout.createParallelGroup();
		filterPic.addComponent(filterPanel);
		filterPic.addComponent(imagePanel);
		vertical.addGroup(filterPic);
		
		vertical.addComponent(saveButtons);
		
		layout.setVerticalGroup(vertical);
	}
	
	private void goBack()
	{
		navigator.setImage(null);
		navigator.setImageFile(null);
		navigator.toHome();
	}
	
	private void saveScelfie()
	{
		fileChooser.setDialogTitle("Save as...");
		int returnVal = fileChooser.showSaveDialog(null);
		
		//BufferedWriter output = null;
		
		if(returnVal == JFileChooser.APPROVE_OPTION)
		{
			//File file = fileChooser.getSelectedFile();
			//String path = file.getAbsolutePath();
			try {
				ImageIO.write(navigator.getImage(), "png", fileChooser.getSelectedFile());
				saved = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveToMy()
	{
		saved = true;
	}
	
	private void done()
	{
		if(!saved)
		{
			ImageIcon icon = createImageIcon();
			
			//ImageIcon icon = new ImageIcon("testfiles/fsif.png");
			int yesNo = JOptionPane.showOptionDialog(this, "Are you sure you are done?\nYou have not saved your Scelfie in any way!", "Unsaved Scelfie!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, null, null);
			if(yesNo == JOptionPane.YES_OPTION)
			{
				goBack();
			}
		}
	}
	
	private void uploadToCommunity()
	{
		
	}
	
	private ImageIcon createImageIcon()
	{
		ImageIcon orig = new ImageIcon("testfiles/fsif.png");
		Image img = orig.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, 100, 100, null, null);	
		ImageIcon finish = new ImageIcon(bi.getSubimage(0, 0, 100, 100));	
		return finish;
	}
	
	private ImageIcon createFilterIcon(String filepath, int size)
	{
		ImageIcon orig = new ImageIcon(filepath);
		Image img = orig.getImage();
		BufferedImage bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
		Graphics g = bi.createGraphics();
		g.drawImage(img, 0, 0, size, size, null, null);	
		ImageIcon finish = new ImageIcon(bi.getSubimage(0, 0, size, size));	
		return finish;
	}
}
