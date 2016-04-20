package frame;

import java.awt.image.BufferedImage;

public interface Navigator {
	public void toLogIn();
	public void toHome();
	public void toEdit();
	public void toMyScelfies();
	public void toCommunityAlbum();
	public void setRegistered(boolean b);
	public void setUsername(String username);
	public String getUsername();
	public Boolean getRegistered();
	public String getImageFile();
	public void setImageFile(String filepath);
	public BufferedImage getImage();
	public void setImage(BufferedImage image);
	public BufferedImage getEdited();
	public void setEdited(BufferedImage image);
}
