package networking;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class PHPReader {
	public static final String SIGNUP = "http://www.non-solus.com/scelfie/signup.php";
	public static final String LOGIN = "http://www.non-solus.com/scelfie/login.php";
	private static final String SIGNUP_HASH = "http://www.non-solus.com/scelfie/signup_hash.php";
	private static final String LOGIN_HASH = "http://www.non-solus.com/scelfie/login_hash.php";
	
	
	// Given a username and password, the password is first encrypted with BCrypt.
	// Then, the username and encrypted password are sent to the PHP script. The script checks if the user exists.
	// If the user exists, the PHP script will send back a message to this function saying what went wrong.
	// If the user was successfully signed up, all the PHP script will return is "true".
	public static String signup(String username, String password) {
		HttpURLConnection connection = null;

		try {
			String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			System.out.println("Password: " + encryptedPassword);
			// Create string to send POST request for username and password
			String data = URLEncoder.encode("server_username", "UTF-8") + "=" + URLEncoder.encode("makerma1_csci201", "UTF-8") +
					"&" + URLEncoder.encode("server_password", "UTF-8") + "=" + URLEncoder.encode("iloveUSC2016!", "UTF-8") +
					"&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + 
					"&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(encryptedPassword, "UTF-8");
			
			// Create connection
			URL url = new URL(SIGNUP_HASH);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(data);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line = rd.readLine();
			while (line != null) {
				response.append(line);
				line = rd.readLine();
				if (line != null) response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}

		}
	}

	
	// Given a username, sends the username to the PHP script which checks if it exists.
	// If the user DOESN'T exist in the MySQL database, the PHP script will return "false".
	// If the user DOES exist, get its (encrypted) password from MySQL and send it back to the Java code.
	// After receiving the password from the script, check if it matches this function's password parameter.
	public static String login(String username, String password) {
		HttpURLConnection connection = null;

		try {
			// Create string to send POST request for username and password
			String data = URLEncoder.encode("server_username", "UTF-8") + "=" + URLEncoder.encode("makerma1_csci201", "UTF-8") +
					"&" + URLEncoder.encode("server_password", "UTF-8") + "=" + URLEncoder.encode("iloveUSC2016!", "UTF-8") +
					"&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
			
			// Create connection
			URL url = new URL(LOGIN_HASH);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(data);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line = rd.readLine();
			while (line != null) {
				response.append(line);
				line = rd.readLine();
				if (line != null) response.append('\r');
			}
			rd.close();
			
			String result = response.toString();
			System.out.println(result);
			System.out.println(password);
			try {
				if (BCrypt.checkpw(password, result)) {
					return "True";
				} else {
					return "Incorrect password";
				}
			} catch (IllegalArgumentException iae) {
				return "Incorrect hash/password";
			}


		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}

		}
	}
	

	// IMPORTANT NOTE: this function is outdated now!!! Use signup or checkLogin functions instead
	//
	//
	// This function can be used to attempt to login/signup.
	// The function will return the String "True" if successful or "False" if not.
	// Any other Strings that are returned will have to do with the connection to the server not working.
	public static String executePost(String targetURL, String username, String password) {

		HttpURLConnection connection = null;

		try {
			// Create string to send POST request for username and password
			String data = URLEncoder.encode("server_username", "UTF-8") + "=" + URLEncoder.encode("makerma1_csci201", "UTF-8") +
					"&" + URLEncoder.encode("server_password", "UTF-8") + "=" + URLEncoder.encode("iloveUSC2016!", "UTF-8") +
					"&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + 
					"&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
			
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(data.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(data);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line = rd.readLine();
			while (line != null) {
				response.append(line);
				line = rd.readLine();
				if (line != null) response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}

		}
	}
}
