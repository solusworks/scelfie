package frame;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InstructionsPanel extends JPanel {
	private static final long serialVersionUID = -201183432227279869L;
	Navigator navigator;

	JButton next;
	JLabel instructions;
	
	public InstructionsPanel(ScelfieFrame inNav) {
		navigator = inNav;
		
		initializeVariables();
		addActionListeners();
		createGUI();
	}

	private void initializeVariables() {
		instructions = new JLabel();
		try {
			BufferedImage img = ImageIO.read(new File("testfiles/instructions.png"));
			ImageIcon icon = new ImageIcon(img);
			instructions.setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		next = new JButton("Next");
	}
	
	private void addActionListeners() {
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				navigator.toHome();
			}
		});
	}

	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		next.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(next);
		instructions.setAlignmentX(Component.CENTER_ALIGNMENT);
		add(instructions);
	}
	
}
