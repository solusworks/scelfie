package networking;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

public class PHPReader {
	// These two String variables are outdated/deprecated
	public static final String SIGNUP = "http://www.non-solus.com/scelfie/signup.php";
	public static final String LOGIN = "http://www.non-solus.com/scelfie/login.php";
	public static final String ERICKTEST = "http://www.non-solus.com/scelfie/ericktest.php";
	
	private static final String SIGNUP_HASH = "http://www.non-solus.com/scelfie/signup_hash.php";
	private static final String LOGIN_HASH = "http://www.non-solus.com/scelfie/login_hash.php";
	private static final String UPLOAD = "http://www.non-solus.com/scelfie/upload.php";
	private static final String IMAGE_LIST = "";
	
	
	// Given a username and password, the password is first encrypted with BCrypt.
	// Then, the username and encrypted password are sent to the PHP script. The script checks if the user exists.
	// If the user exists, the PHP script will send back a message to this function saying what went wrong.
	// If the user was successfully signed up, all the PHP script will return is "true".
	public static String signup(String username, String password) {
		HttpURLConnection connection = null;

		try {
			String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			//System.out.println("Password: " + encryptedPassword);
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
			//System.out.println(result);
			//System.out.println(password);
			try {
				if (BCrypt.checkpw(password, result)) {
					return "true";
				} else {
					return "Incorrect password";
				}
			} catch (IllegalArgumentException iae) {
				return "Incorrect hash format in database";
			} catch (Exception e) {
				e.printStackTrace();
				return "BCrypt error";
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
	
	private static String getBasename(String imagePath) {
		String result = "";
		int slashIndex = imagePath.lastIndexOf('/');
		for (int i = 0; i < imagePath.length(); i++) {
			if (i >= slashIndex) {
				result += imagePath.charAt(i);
			}
		}
		return result;
	}
	
	private static byte[] imageToBytes(BufferedImage image) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", baos);
		} catch (IOException ioe) {
			System.out.println("Error converting to bytes");
		}
		return baos.toByteArray();
	}
	
	// Given a BufferedImage, upload it to the server. The name of the file on the server
	// is required and is therefore a parameter for this function. Make sure to add file extensions
	// (e.g. ".png" or ".jpg") to imageName as well. Finally, the username is required to determine
	// which folder the image will be uploaded to. Function returns true if it worked and false if otherwise.
	public static boolean uploadImage(BufferedImage image, String imageName, String username) {
		boolean result = false;
		String fullPath = username + "/" + imageName;
		
		//CrLf: carriage return
		final String CrLf = "\r\n";
		
        URLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            //URL url = new URL("http://localhost/test/post.php");
        	URL url = new URL(UPLOAD + "?username=" + username);
            //System.out.println("url:" + url);
            conn = url.openConnection();
            conn.setDoOutput(true);

            //String postData = "";
            byte[] imgData = imageToBytes(image);

            String message1 = "";
            message1 += "-----------------------------4664151417711" + CrLf;
            
            message1 += "Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"" + imageName
                           + CrLf;
            
            message1 += "Content-Type: image/png" + CrLf;
            message1 += CrLf;

            // the image is sent between the messages in the multipart message.

            String message2 = "";
            message2 += CrLf + "-----------------------------4664151417711--"
                    + CrLf;

            conn.setRequestProperty("Content-Type",
                    "multipart/form-data; boundary=---------------------------4664151417711");
            // might not need to specify the content-length when sending chunked
            // data.
            conn.setRequestProperty("Content-Length", String.valueOf((message1
                    .length() + message2.length() + imgData.length)));

            //System.out.println("open os");
            os = conn.getOutputStream();

            //System.out.println(message1);
            os.write(message1.getBytes());

            // SEND THE IMAGE
            int index = 0;
            int size = 1024;
            do {
                //System.out.println("write:" + index);
                if ((index + size) > imgData.length) {
                    size = imgData.length - index;
                }
                os.write(imgData, index, size);
                index += size;
            } while (index < imgData.length);
            //System.out.println("written:" + index);

            //System.out.println(message2);
            os.write(message2.getBytes());
            os.flush();

            //System.out.println("open is");
            is = conn.getInputStream();

            char buff = 512;
            int len;
            byte[] data = new byte[buff];
            do {
                //System.out.println("READ");
                len = is.read(data);

                if (len > 0) {
                    //System.out.println(new String(data, 0, len));
                    result = true;
                }
            } while (len > 0);

            //System.out.println("DONE");
        } catch (Exception e) {
        	return result;
        	//System.out.println("File not found");
            //e.printStackTrace();
        } finally {
            //System.out.println("Close connection");
            try {
                os.close();
            } catch (Exception e) {
            }
            try {
                is.close();
            } catch (Exception e) {
            }
            try {

            } catch (Exception e) {
            }
        }
        return result;
    }
	
	// Returns true if file upload successful, false if not (boolean types)
	// This method should only be used after a user is "logged in".
	// In order to use this function, the imagePath must be provided.
	// This is the path to the image that a user wants to upload on their local machine.
	// The username variable should be self explanatory.
	public static boolean uploadImage(String imagePath, String username) {
			boolean result = false;
			String basename = getBasename(imagePath);
			String fullPath = username + "/" + basename;
			
			//CrLf: carriage return
			final String CrLf = "\r\n";
			
	        URLConnection conn = null;
	        OutputStream os = null;
	        InputStream is = null;
	
	        try {
	            //URL url = new URL("http://localhost/test/post.php");
	        	URL url = new URL(UPLOAD + "?username=" + username);
	            //System.out.println("url:" + url);
	            conn = url.openConnection();
	            conn.setDoOutput(true);
	
	            //String postData = "";
	
	            InputStream imgIs = new FileInputStream(imagePath);
	            byte[] imgData = new byte[imgIs.available()];
	            imgIs.read(imgData);
	
	            String message1 = "";
	            message1 += "-----------------------------4664151417711" + CrLf;
	            
	            message1 += "Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"" + imagePath
	                           + CrLf;
	            
	            message1 += "Content-Type: image/png" + CrLf;
	            message1 += CrLf;
	
	            // the image is sent between the messages in the multipart message.
	
	            String message2 = "";
	            message2 += CrLf + "-----------------------------4664151417711--"
	                    + CrLf;
	
	            conn.setRequestProperty("Content-Type",
	                    "multipart/form-data; boundary=---------------------------4664151417711");
	            // might not need to specify the content-length when sending chunked
	            // data.
	            conn.setRequestProperty("Content-Length", String.valueOf((message1
	                    .length() + message2.length() + imgData.length)));
	
	            //System.out.println("open os");
	            os = conn.getOutputStream();
	
	            //System.out.println(message1);
	            os.write(message1.getBytes());
	
	            // SEND THE IMAGE
	            int index = 0;
	            int size = 1024;
	            do {
	                //System.out.println("write:" + index);
	                if ((index + size) > imgData.length) {
	                    size = imgData.length - index;
	                }
	                os.write(imgData, index, size);
	                index += size;
	            } while (index < imgData.length);
	            //System.out.println("written:" + index);
	
	            //System.out.println(message2);
	            os.write(message2.getBytes());
	            os.flush();
	
	            //System.out.println("open is");
	            is = conn.getInputStream();
	
	            char buff = 512;
	            int len;
	            byte[] data = new byte[buff];
	            do {
	                //System.out.println("READ");
	                len = is.read(data);
	
	                if (len > 0) {
	                    //System.out.println(new String(data, 0, len));
	                    result = true;
	                }
	            } while (len > 0);
	
	            //System.out.println("DONE");
	        } catch (Exception e) {
	        	return result;
	        	//System.out.println("File not found");
	            //e.printStackTrace();
	        } finally {
	            //System.out.println("Close connection");
	            try {
	                os.close();
	            } catch (Exception e) {
	            }
	            try {
	                is.close();
	            } catch (Exception e) {
	            }
	            try {
	
	            } catch (Exception e) {
	            }
	        }
	        return result;
	    }
	
	public static String getImageList() {
		
		String list = "";
		return list;
	}
	
	public static String downloadImage(String imageName) {
		
		return "";
	}
	
	

	// IMPORTANT NOTE: this function is out-dated now!!! Use signup or login functions instead
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
