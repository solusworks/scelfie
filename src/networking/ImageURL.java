package networking;

import java.net.URL;

public class ImageURL {
	private final String baseURL = "http://non-solus.com/scelfie/";
	private String imageName;
	private String username;
	private URL imageURL;
	
	public ImageURL(String username, String imageName) {
		this.imageName = imageName;
		this.username = username;
		try {
			this.imageURL = new URL(baseURL + this.username + "/" + this.imageName);
		} catch (Exception e) {
			e.printStackTrace();
			this.imageURL = null;
		}
	}
	
	// Sets imageName and username to update the imageURL.
	// So to update the URL, username, or imageName, use this function.
	public void setImageName(String username, String imageName) {
		this.imageName = imageName;
		this.username = username;
		try {
			this.imageURL = new URL(baseURL + this.username + "/" + this.imageName);
		} catch (Exception e) {
			e.printStackTrace();
			this.imageURL = null;
		}
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public URL getImageURL() {
		return imageURL;
	}
}
