package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class HomePanel extends JPanel {
	private static final long serialVersionUID = -7364326372669653451L;
	private Navigator navigator;
	
	private JButton uploadPhotoButton;
	private JButton viewMyScelfiesButton;
	private JButton viewCommunityAlbumButton;
	
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
	}

	private void addActionListeners() {
		uploadPhotoButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		viewMyScelfiesButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
		viewCommunityAlbumButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
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
}
