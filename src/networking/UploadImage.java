package networking;

import java.awt.image.BufferedImage;

public class UploadImage implements Runnable {
	private BufferedImage image;
	private String imageName;
	private String username;
	private volatile boolean result;
	
	public UploadImage(BufferedImage image, String imageName, String username) {
		this.image = image;
		this.imageName = imageName;
		this.username = username;
		new Thread(this).start();
	}

	@Override
	public void run() {
		result = PHPReader.uploadImage(image, imageName, username);
	}
	
	public boolean getValue() {
		return result;
	}

}
