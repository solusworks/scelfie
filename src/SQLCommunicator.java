import java.sql.SQLException;

import com.mysql.jdbc.Driver;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.DriverManager;


public class SQLCommunicator {
	private Connection con;
	/*
	private final static String selectName = "SELECT * FROM FACTORYORDERS WHERE NAME=?";
	private final static String addProduct = "INSERT INTO FACTORYORDERS(NAME,CREATED) VALUES(?,?)";
	private final static String updateProduct = "UPDATE FACTORYORDERS SET CREATED=? WHERE NAME=? ";
	*/
	
	private final static String addUser = "INSERT INTO SCELFIE(USERNAME, PASSWORD) VALUES(?,?)";
	private final static String selectUser = "SELECT * FROM SCELFIE WHERE USERNAME=?";
	
	
	public SQLCommunicator() {
		try {
			new Driver();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void connect() {
		try {
			System.out.println("Trying to connect...");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/scelfie?useSSL=false&user=root&password=root");
			//con = DriverManager.getConnection("jdbc:mysql://maker-marketplace.com:3306/scelfie?user=makerma1_csci201&password=iloveUSC2016!");
			System.out.println("Successfully connected to the server!");
		} catch (SQLException e) {
			System.out.println("Failed to connect to the server");
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void add(String username, String password) {
		try {
			PreparedStatement ps = con.prepareStatement(addUser);
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public boolean doesExist(String username) {
		try {
			PreparedStatement ps = con.prepareStatement(selectUser);
			ps.setString(1, username);
			ResultSet result = ps.executeQuery();
			while(result.next()) {
				System.out.println("User with this username already exists!");
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
