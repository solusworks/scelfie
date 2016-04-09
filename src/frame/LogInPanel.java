package frame;

import java.awt.BorderLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogInPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton logInButton;
	private JButton signUpButton;
	private JButton guestButton;
	
	{
		initializeVariables();
		addActionListeners();
		createGUI();
	}
	
	private void initializeVariables()
	{
		usernameLabel = new JLabel("Username");
		usernameField = new JTextField(40);
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(50);
		logInButton = new JButton("Log in");
		signUpButton = new JButton("Sign up");
		guestButton = new JButton("Continue as guest");
	}

	private void createGUI() {
		System.out.println("Creating GUI!!");
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createGlue());
		
		JPanel logSignButs = new JPanel();
		logSignButs.add(logInButton, BorderLayout.WEST);
		logSignButs.add(signUpButton, BorderLayout.CENTER);
		
		JPanel vertStuffPanel = new JPanel();
		vertStuffPanel.setLayout(new BoxLayout(vertStuffPanel, BoxLayout.Y_AXIS));
		vertStuffPanel.add(usernameField);
		vertStuffPanel.add(passwordField);
		vertStuffPanel.add(logSignButs);
		vertStuffPanel.add(guestButton);
		
		add(vertStuffPanel);
		
		add(Box.createGlue());
	}

	private void addActionListeners() {
		
	}
}
