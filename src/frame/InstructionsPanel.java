package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class InstructionsPanel extends JPanel {
	private static final long serialVersionUID = -4380241702662632665L;
	private ScelfieFrame navigator;
	
	private JButton nextButton;
	
	{
		initializeVariables();
		addActionListeners();
		createGUI();
	}
	
	public InstructionsPanel(ScelfieFrame inNav)
	{
		navigator = inNav;
	}

	private void initializeVariables() {
		nextButton = new JButton("Next");
	}
	
	private void addActionListeners() {
		nextButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				next();
			}	
		});
	}
	
	private void createGUI() {
		add(nextButton);
	}

	private void next() {
		navigator.toHome();
	}
	
}
