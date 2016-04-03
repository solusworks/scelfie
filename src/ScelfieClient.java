import javax.swing.JFrame;

public class ScelfieClient extends JFrame {

	private ScelfieNetworkManager networkManager;
	
	ScelfieClient() {

		super();
		initializeVariables();
		createGUI();
		addActionAdapters();
		setSize(800, 600);
		setVisible(true);
		
	}
	
	void initializeVariables(){
		
		networkManager = new ScelfieNetworkManager();
		networkManager.uploadImageMultipart("testImage.png");
		
		//No parameter for this POST method call
		//System.out.println(ScelfieNetworkManager.excutePost("http://www.non-solus.com/scelfie/upload.php", ""));
		//System.out.println(ScelfieNetworkManager.uploadImage("testImage.png"));
	}

	void createGUI(){
		
		
	}
	
	void addActionAdapters(){
		
		
		
	}
	
	public static void main(String[] args) {

		new ScelfieClient();
		
	}

}
