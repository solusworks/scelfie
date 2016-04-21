package frame;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
	}

	private void createGUI() {
		
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
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
		
		//JPanel vertStuffPanel = new JPanel();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(usernamePanel);
		add(passwordPanel);
		add(logSignButs);
		JPanel temp = new JPanel();
		temp.add(guestButton);
		add(temp);
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());
		add(Box.createVerticalGlue());

		
		setOpaque(false);
		
		//add(vertStuffPanel);
		
		add(Box.createGlue());
		
		revalidate();
		repaint();
		
		/*setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
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
		vertStuffPanel.add(Box.createVerticalGlue());
		vertStuffPanel.add(Box.createVerticalGlue());

		
		vertStuffPanel.setOpaque(false);
		
		add(vertStuffPanel);
		
		add(Box.createGlue());
		
		revalidate();
		repaint();*/
		
		//BufferedImage logoImage;
		
	
		/*try {
			logoImage = ImageIO.read(new File("SCelfieLogo.png"));
			JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
			add(logoLabel);
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		
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
		else if(!isASCII(userText) || !isASCII(passText))
		{
			JOptionPane.showMessageDialog(this, "Username or password contains ASCII", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else if(userText.equals("communityalbum"))
		{
			JOptionPane.showMessageDialog(this, "Please choose a different username", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else if(userText.length() > 40)
		{
			JOptionPane.showMessageDialog(this, "Username must be shorter than 41 characters", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else if(userText.length() < 6)
		{
			JOptionPane.showMessageDialog(this, "Username must be longer than five characters", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else if(passText.length() > 50)
		{
			JOptionPane.showMessageDialog(this, "Password must be shorter than 51 characters", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else if(passText.length() < 6)
		{
			JOptionPane.showMessageDialog(this, "Password must be longer than five characters", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			String phpAnswer = PHPReader.signup(userText, passText);
			if(!phpAnswer.startsWith("t"))
			{
				JOptionPane.showMessageDialog(this, "Username or password invalid", "Error", JOptionPane.WARNING_MESSAGE);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "Sign up success!", "!", JOptionPane.PLAIN_MESSAGE);
				navigator.setRegistered(true);
				navigator.setUsername(userText);
				attemptLogIn();
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
		else if(!isASCII(userText) || !isASCII(passText))
		{
			JOptionPane.showMessageDialog(this, "Username or password contains ASCII, invalid", "Error", JOptionPane.WARNING_MESSAGE);
		}
		else
		{
			String phpAnswer = PHPReader.login(userText, passText);
			if(!phpAnswer.startsWith("t"))
			{
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
		navigator.toHome();
	}
	
	private Boolean isASCII(String str)
	{
		for (char c: str.toCharArray()){
			if (((int)c)>127){
				return false;
			} 
		}
		return true;
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		//g.setColor(Color.YELLOW);
		
		BufferedImage logoImage;
		try {
			logoImage = ImageIO.read(new File("SCelfieLogo.png"));
			g.drawImage(logoImage, 0, 0, getWidth(),getHeight(), null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
