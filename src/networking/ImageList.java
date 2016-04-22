package networking;

import java.util.List;

public class ImageList implements Runnable {
	private String username;
	private List<ImageURL> list;
	
	public ImageList(String username) {
		this.username = username;
		Thread thread = new Thread(this);
		thread.start();
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		System.out.println("Started");
		list = PHPReader.getImageList(username);

	}
	
	public List<ImageURL> getList() {
		return list;
	}

}
