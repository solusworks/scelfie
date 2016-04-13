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
	
	{
		initializeVariables();
		addActionListeners();
		createGUI();
	}
	
	public HomePanel(ScelfieFrame inNav)
	{
		navigator = inNav;
	}

	private void initializeVariables() {
		uploadPhotoButton = new JButton();
		viewMyScelfiesButton = new JButton();
		viewCommunityAlbumButton = new JButton();
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
		// TODO Auto-generated method stub
		
	}
}
