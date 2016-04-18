package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = -7364326372669653451L;
	private Navigator navigator;
	
	private JButton uploadPhotoButton;
	private JButton viewMyScelfiesButton;
	private JButton viewCommunityAlbumButton;
	
	private JFileChooser fileChooser;
	
	public HomePanel(ScelfieFrame inNav)
	{
		navigator = inNav;
		initializeVariables();
		addActionListeners();
		createGUI();
	}

	private void initializeVariables() {
		uploadPhotoButton = new JButton("Upload Photo");
		viewMyScelfiesButton = new JButton("My SCelfies");
		viewCommunityAlbumButton = new JButton("Community Album");
		fileChooser = new JFileChooser("Upload photo...");
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Photos", "png");
		fileChooser.setFileFilter(filter);
	}

	private void addActionListeners() {
		uploadPhotoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				openFileChooser();
			}
			
		});
		viewMyScelfiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				viewMy();
			}
			
		});
		viewCommunityAlbumButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				navigator.toCommunityAlbum();
			}
			
		});
	}

	private void createGUI() {
		add(uploadPhotoButton);
		if(navigator.getRegistered())
		{
			add(viewMyScelfiesButton);
		}
		add(viewCommunityAlbumButton);
	}
	
	private void openFileChooser()
	{
		fileChooser.setDialogTitle("Upload photo...");
		int returnVal = fileChooser.showOpenDialog(this);
		
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File selected = fileChooser.getSelectedFile();
			try {			
				navigator.setImageFile(selected.getAbsolutePath());
				BufferedImage image = ImageIO.read(selected);
				navigator.setImage(image);
				navigator.toEdit();
			} catch (IOException e) {
				e.printStackTrace();
				
			}	
		}
	}
	
	private void viewMy()
	{
		navigator.toMyScelfies();
	}
}
