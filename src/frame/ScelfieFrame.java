package frame;

import java.awt.Dimension;

import javax.swing.JFrame;


public class ScelfieFrame extends JFrame implements Navigator {
	private static final long serialVersionUID = 580583432395451286L;
	
	private Boolean registeredUserSession;
	private String username;
	
	{
		setTitle("SCelfie");
		setSize(640,480);
		setMinimumSize(new Dimension(640,480));
		//setJMenuBar(new OfficeMenuBar());
		getContentPane().add(new LogInPanel(this));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		ScelfieFrame sf = new ScelfieFrame();
		sf.setVisible(true);
		//sf.setIconImage(new Image("SCelfieLogo.png"));
	}
	
	@Override
	public void toLogIn() {
		getContentPane().removeAll();
		getContentPane().add(new LogInPanel(this));
		revalidate();
		repaint();
	}

	@Override
	public void toInstructions() {
		// TODO Auto-generated method stub
		getContentPane().removeAll();
		getContentPane().add(new InstructionsPanel(this));
		revalidate();
		repaint();
	}

	@Override
	public void toSignUp() {
		getContentPane().removeAll();
		getContentPane().add(new SignUpPanel());
		revalidate();
		repaint();
	}

	@Override
	public void toHome() {
		getContentPane().removeAll();
		getContentPane().add(new HomePanel());
		revalidate();
		repaint();
	}

	@Override
	public void toEdit() {
		getContentPane().removeAll();
		getContentPane().add(new EditPanel());
		revalidate();
		repaint();
	}

	@Override
	public void toMyScelfies() {
		getContentPane().removeAll();
		
		revalidate();
		repaint();
	}

	public String getUsername() {
		return username;
	}

	
	public void setUsername(String username) {
		this.username = username;
	}

	
	@Override
	public void setRegistered(boolean b) {
		registeredUserSession = b;	
	}

	@Override
	public Boolean getRegistered() {
		return registeredUserSession;
	}
}
