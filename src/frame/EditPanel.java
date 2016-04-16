package frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
	private JButton doneButton;
	
	private JScrollPane filterScroll;
	
	private JFileChooser fileChooser;
	
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
		/*c.setDialogTitle("Save as...");
			int returnVal = fc.showSaveDialog(null);
			
			if(returnVal == JFileChooser.APPROVE_OPTION)
			{
				try {
					file_selected = fc.getSelectedFile();								
					
					//String fname = "";
					
					if(file_selected.getName().endsWith(".txt"))
					{
						output = new BufferedWriter(new FileWriter(file_selected));
						tabs.setTitleAt(tabs.getSelectedIndex(), file_selected.getName());
						//fname = file_selected.getName();
					}
					else
					{			
						output = new BufferedWriter(new FileWriter(file_selected+".txt"));
						tabs.setTitleAt(tabs.getSelectedIndex(), file_selected.getName()+".txt");
						//fname = file_selected.getName() + ".txt"
					}

 				output.write(file_contents);
 				output.flush();
				} catch (IOException e) {
					e.printStackTrace();
				} */
}
