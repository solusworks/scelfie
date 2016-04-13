package frame;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import networking.PHPReader;

public class LogInPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	Navigator navigator;
	
	private JLabel usernameLabel;
	private JTextField usernameField;
	private JLabel passwordLabel;
	private JPasswordField passwordField;
	private JButton logInButton;
	private JButton signUpButton;
	private JButton guestButton;
	
	private PHPReader phpReader;
	
	{
		initializeVariables();
		addActionListeners();
		createGUI();
	}
	
	public LogInPanel(ScelfieFrame inNav)
	{
		navigator = inNav;
	}
	
	private void initializeVariables()
	{
		usernameLabel = new JLabel("Username");
		usernameField = new JTextField(15);
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(15);
		logInButton = new JButton("Log in");
		signUpButton = new JButton("Sign up");
		guestButton = new JButton("Continue as a Guest");
		
		phpReader = new PHPReader();
	}

	private void createGUI() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createGlue());
		
		JPanel logSignButs = new JPanel();
		logSignButs.add(logInButton, BorderLayout.WEST);
		logSignButs.add(signUpButton, BorderLayout.CENTER);
		
		JPanel usernamePanel = new JPanel();
		usernamePanel.add(usernameLabel, BorderLayout.WEST);
		usernamePanel.add(usernameField, BorderLayout.EAST);
		
		JPanel passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel, BorderLayout.WEST);
		passwordPanel.add(passwordField, BorderLayout.EAST);
		
		JPanel vertStuffPanel = new JPanel();
		vertStuffPanel.setLayout(new BoxLayout(vertStuffPanel, BoxLayout.Y_AXIS));
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(usernamePanel);
		vertStuffPanel.add(passwordPanel);
		vertStuffPanel.add(logSignButs);
		JPanel temp = new JPanel();
		temp.add(guestButton);
		vertStuffPanel.add(temp);
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(Box.createVerticalGlue());

		
		add(vertStuffPanel);
		
		add(Box.createGlue());
		
		BufferedImage logoImage;
		
		/*
		 * format=new ImageIcon(imagedata);
		  Image img = format.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		  jLabel15.setIcon(format);
		 */
		try {
			logoImage = ImageIO.read(new File("SCelfieLogo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
			add(logoLabel);
			
			/*
			logoImage = ImageIO.read(new File("SCelfieLogo.png"));
			ImageIcon format = new ImageIcon(logoImage);
			Image img = format.getImage().getScaledInstance(200, 50, Image.SCALE_SMOOTH);
			JLabel logoLabel = new JLabel();
			logoLabel.setIcon((Icon) logoLabel);
			add(logoLabel);*/
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void addActionListeners() {
		logInButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				attemptLogIn();
			}
		});
		
		signUpButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				attemptSignUp();
			}
		});
		
		guestButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				guestSession();
			}		
		});
	}
	
	private void attemptSignUp()
	{
		String userText = usernameField.getText();
		String passText = new String(passwordField.getPassword());
		
		if(userText.length() == 0 || passText.length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Username or password field blank", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			System.out.println("Attempting php sign up execute post");

			String phpAnswer = phpReader.executePost(PHPReader.SIGNUP, userText, passText);
			if(!phpAnswer.startsWith("T"))
			{
				System.out.println(phpAnswer);
				JOptionPane.showMessageDialog(this, "Username or password invalid", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Sign up success!", "!", JOptionPane.PLAIN_MESSAGE);
				navigator.setRegistered(true);
				navigator.setUsername(userText);
				navigator.toInstructions();
			}
		}
	}

	private void attemptLogIn()
	{
		String userText = usernameField.getText();
		String passText = new String(passwordField.getPassword());
		
		if(userText.length() == 0 || passText.length() == 0)
		{
			JOptionPane.showMessageDialog(this, "Username or password field blank", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			System.out.println("Attempting php log in execute post");
			String phpAnswer = phpReader.executePost(PHPReader.LOGIN, userText, passText);
			if(!phpAnswer.startsWith("T"))
			{
				System.out.println(phpAnswer);
				JOptionPane.showMessageDialog(this, "Username or password invalid", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Log in success!", "!", JOptionPane.PLAIN_MESSAGE);
				navigator.setRegistered(true);
				navigator.setUsername(userText);
				navigator.toHome();
			}
		}
		
	}

	private void guestSession()
	{
		navigator.setRegistered(false);
		navigator.toInstructions();
	}
}
