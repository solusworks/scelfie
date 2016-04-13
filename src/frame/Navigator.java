package frame;

public interface Navigator {
	public void toLogIn();
	public void toInstructions();
	public void toSignUp();
	public void toHome();
	public void toEdit();
	public void toMyScelfies();
	public void setRegistered(boolean b);
	public void setUsername(String username);
	public String getUsername();
	public Boolean getRegistered();
}
