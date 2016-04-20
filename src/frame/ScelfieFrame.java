package frame;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;


public class ScelfieFrame extends JFrame implements Navigator {
	private static final long serialVersionUID = 580583432395451286L;
	
	private Boolean registeredUserSession;
	private String username;
	
	private String imageFpath;
	private BufferedImage image;
	private BufferedImage edited;
	
	{
		setTitle("SCelfie");
		setSize(640,580);
		setMinimumSize(new Dimension(640,580));
		//setJMenuBar(new OfficeMenuBar());
		getContentPane().add(new LogInPanel(this));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		registeredUserSession = false;
		username = "";
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
	public void toHome() {
		getContentPane().removeAll();
		getContentPane().add(new HomePanel(this));
		revalidate();
		repaint();
	}

	@Override
	public void toEdit() {
		getContentPane().removeAll();
		getContentPane().add(new EditPanel(this));
		revalidate();
		repaint();
	}

	@Override
	public void toMyScelfies() {
		getContentPane().removeAll();
		getContentPane().add(new MyScelfiesPanel(this));
		revalidate();
		repaint();
	}
	
	@Override
	public void toCommunityAlbum() {
		getContentPane().removeAll();
		getContentPane().add(new CommunityAlbumPanel(this));
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

	@Override
	public String getImageFile() {
		return imageFpath;
	}

	@Override
	public void setImageFile(String filepath) {
		imageFpath = filepath;
	}

	@Override
	public BufferedImage getImage() {
		return image;
	}

	@Override
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public BufferedImage getEdited()
	{
		if(edited != null)
		{
			return edited;
		} else {
			return null;
		}
	}
	
	public void setEdited(BufferedImage image)
	{
		
	}

}
