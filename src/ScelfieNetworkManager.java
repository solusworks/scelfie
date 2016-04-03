import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ScelfieNetworkManager {

	ScelfieNetworkManager() {

	}

	// public static String uploadImage(Image uploadImg, String fileName) {

	// String uploadResponse =
	// excutePost("http://www.non-solus.com/scelfie/upload.php", uploadImg);
	//
	// //Get bytes array from input image file
	// // open image
	// File imgPath = new File(ImageName);
	// BufferedImage bufferedImage = ImageIO.read(imgPath);
	//
	// // get DataBufferBytes from Raster
	// WritableRaster raster = bufferedImage .getRaster();
	// DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
	// data.getData();

//	public static String uploadImage(String ImageName) {
//
//		//// ----------------
//
//		HttpURLConnection connection = null;
//
//		// Get raw bytes from input image file
//
//		File imgPath = new File(ImageName);
//
//		BufferedImage bufferedImage = null;
//
//		System.out.println(imgPath);
//		
//		try {
//
//			bufferedImage = ImageIO.read(imgPath);
//			
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		// get DataBufferBytes from Raster
//		WritableRaster raster = bufferedImage.getRaster();
//		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();
//		byte[] imageBytes = data.getData();
//		String imageBytesStr = Base64.getEncoder().encodeToString(imageBytes);
//		
//		try {
//			// Create connection
//			URL url = new URL("http://www.non-solus.com/scelfie/upload.php");
//			connection = (HttpURLConnection) url.openConnection();
//			connection.setRequestMethod("POST");
////			connection.setRequestProperty("Content-Type", "image/png");
//			connection.setRequestProperty("Content-Type", "bitmap; charset=utf-8");
////			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
////			connection.setRequestProperty("Content-Type", "multipart/form-data");// boundary=---------------------------4664151417711");
//			connection.setRequestProperty("Content-Length", Integer.toString(imageBytes.length));
//			connection.setRequestProperty("Content-Language", "en-US");
//
//			connection.setUseCaches(false);
//			connection.setDoOutput(true);
//
//			// Send request
//			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
//			//wr.write(imageBytes);
//			wr.writeBytes(imageBytesStr);
//			wr.close();
//
//			// Get Response
//			InputStream is = connection.getInputStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//			StringBuilder response = new StringBuilder(); // or StringBuffer if
//															// not Java 5+
//			String line;
//			while ((line = rd.readLine()) != null) {
//				response.append(line);
//				response.append('\r');
//			}
//			rd.close();
//			return response.toString();
//
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			return null;
//
//		} finally {
//
//			if (connection != null) {
//				connection.disconnect();
//			}
//
//		}
//
//	}

	public static String excutePost(String targetURL, String urlParameters) {

		HttpURLConnection connection = null;

		try {
			// Create connection
			URL url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length", Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.close();

			// Get Response
			InputStream is = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			StringBuilder response = new StringBuilder(); // or StringBuffer if
															// not Java 5+
			String line;
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
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
	
	//public void uploadImageMultipart() {
	public void uploadImageMultipart(String ImagePath) {
		
		//CrLf: carriage return
		final String CrLf = "\r\n";
		
        URLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;

        try {
            //URL url = new URL("http://localhost/test/post.php");
        	URL url = new URL("http://www.non-solus.com/scelfie/upload.php");
            System.out.println("url:" + url);
            conn = url.openConnection();
            conn.setDoOutput(true);

            String postData = "";

            //InputStream imgIs = getClass().getResourceAsStream("testImage.png");
            InputStream imgIs = new FileInputStream(ImagePath);
            byte[] imgData = new byte[imgIs.available()];
            imgIs.read(imgData);

            String message1 = "";
            message1 += "-----------------------------4664151417711" + CrLf;
            //message1 += "Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"testImage.png\""
             //       + CrLf;
            
            message1 += "Content-Disposition: form-data; name=\"uploadedfile\"; filename=\"" + ImagePath
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

            System.out.println("open os");
            os = conn.getOutputStream();

            System.out.println(message1);
            os.write(message1.getBytes());

            // SEND THE IMAGE
            int index = 0;
            int size = 1024;
            do {
                System.out.println("write:" + index);
                if ((index + size) > imgData.length) {
                    size = imgData.length - index;
                }
                os.write(imgData, index, size);
                index += size;
            } while (index < imgData.length);
            System.out.println("written:" + index);

            System.out.println(message2);
            os.write(message2.getBytes());
            os.flush();

            System.out.println("open is");
            is = conn.getInputStream();

            char buff = 512;
            int len;
            byte[] data = new byte[buff];
            do {
                System.out.println("READ");
                len = is.read(data);

                if (len > 0) {
                    System.out.println(new String(data, 0, len));
                }
            } while (len > 0);

            System.out.println("DONE");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Close connection");
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
    }
}
