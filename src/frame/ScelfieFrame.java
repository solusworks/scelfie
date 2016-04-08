package frame;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ScelfieFrame extends JFrame implements Navigator {
	private static final long serialVersionUID = 580583432395451286L;

	{
		setTitle("Trojan Office");
		setSize(640,480);
		setMinimumSize(new Dimension(640,480));
		//setJMenuBar(new OfficeMenuBar());
		//getContentPane().add(new MainMenu(this));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	@Override
	public void toLogIn() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toInstructions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toSignUp() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toHome() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toEdit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toMyScelfies() {
		// TODO Auto-generated method stub
		
	}

}
