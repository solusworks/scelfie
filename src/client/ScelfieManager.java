package client;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

// enum for the whole package


//enum FacialFeature{Eye, Mouth};

public class ScelfieManager {

	
	
	private BufferedImage rawImg; // Original image
	private BufferedImage processedImg; // Original image with mark up to indicate facial features' positions
	private BufferedImage editedImg; // Original image with filters overlayed
	private Rect[] eyesRect;
	private Rect[] mouthRect;
	
	public void loadTestImage(String filePath) {
		
		try {

			rawImg = ImageIO.read(new File(filePath));

			// Copy rawImage into editedImg for later edits
			editedImg = cloneBufferedImageFrom(rawImg);

			// Process the input image once it's loaded
			processedRawImg();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addStickerToImg(String filePath, String feature) {
		
		Rect[] targetRect = null;
		
		if(feature.equals("Eye"))
		{
			targetRect = eyesRect;
			
		} else if(feature.equals("Mouth"))
		{
			targetRect = mouthRect;			
		}
		
		// Load sticker from filePath
		BufferedImage sticker = null;

		try {

			sticker = ImageIO.read(new File(filePath));
			//System.out.println("image type: " + sticker.getType());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Place input sticker on top of the detected eyes' position

		for (int i = 0; i < targetRect.length; i++) {

			// Rescale sticker to match the eye's size
			sticker = scaleBufferedImage(sticker, targetRect[i].width, targetRect[i].height);

//			System.out.println("eyes has been deteced at: " + eyesRect[i].x + " " + eyesRect[i].y + ", with size: "
//					+ eyesRect[i].height + ", " + eyesRect[i].width);

			for (int m = targetRect[i].y; m < targetRect[i].y + targetRect[i].height; m++) {

				for (int n = targetRect[i].x; n < targetRect[i].x + targetRect[i].width; n++) {

					// Get color from the input sticker
					Color col = new Color(sticker.getRGB(n - targetRect[i].x, m - targetRect[i].y), true);
					if(!isTransparent(col.getRGB())) 
					{
						editedImg.setRGB(n, m, col.getRGB());
					}									
				}
			}
		}
	}

	void processedRawImg() {

		// Load OpenCV library for use
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		byte[] rawData = ((DataBufferByte) rawImg.getRaster().getDataBuffer()).getData();
		Mat rawImageMat = new Mat(rawImg.getHeight(), rawImg.getWidth(), CvType.CV_8UC3);
		// Put pixel values of the input image into Mat
		rawImageMat.put(0, 0, rawData);

		Mat grayImageMat = new Mat();
		// Convert the original input image into gray scale
		Imgproc.cvtColor(rawImageMat, grayImageMat, Imgproc.COLOR_BGR2GRAY);

		// Convert gray scale image into processImg (BufferedImage) for later edits
		processedImg = mat2Img(grayImageMat);		
		eyesRect = recognizeEyes(grayImageMat);
		mouthRect = recognizeMouth(grayImageMat);

	}
	
	private Rect[] recognizeMouth(Mat grayRawImgMat)
	{
		// Set up classifer for the eye
				CascadeClassifier mouth_cascade;
				mouth_cascade = new CascadeClassifier("haarcascade_mouth.xml");

				if (mouth_cascade.empty())
					System.out.println("eye_cascade can't load xml file");
				
				
				
				// Detect face
				MatOfRect mouth = new MatOfRect();
				mouth_cascade.detectMultiScale(grayRawImgMat, mouth, 1.1, 2, 0, new Size(80, 80), new Size(140, 140)); // Min
																													// size:
																													// 80,
																													// Max:
																													// 100
																													// for
																													// eye
																													// detection
				
			
				// Draw mark-ups on input image
				
				System.out.println(mouth.toArray().length + "mouth has been deteced");
//
//				for (int i = 0; i < eyesRect.length; i++) {
//
//					System.out.println("eyes has been deteced at: " + eyesRect[i].x + " " + eyesRect[i].y + ", with size: "
//							+ eyesRect[i].height + ", " + eyesRect[i].width);
//
//					for (int m = eyesRect[i].y; m < eyesRect[i].y + eyesRect[i].height; m++) {
//
//						for (int n = eyesRect[i].x; n < eyesRect[i].x + eyesRect[i].width; n++) {
//
//							// grayImageMat.setPixel(n, m, 0);
//							processedImg.setRGB(n, m, 0);
//						}
//					}
//				}
											
				return mouth.toArray();
	}
	
	private Rect[] recognizeEyes(Mat grayRawImgMat)
	{		
		// Set up classifer for the eye
		CascadeClassifier eye_cascade;
		eye_cascade = new CascadeClassifier("haarcascade_eye.xml");

		if (eye_cascade.empty())
			System.out.println("eye_cascade can't load xml file");			
		
		// Detect face
		MatOfRect eyes = new MatOfRect();
		eye_cascade.detectMultiScale(grayRawImgMat, eyes, 1.1, 2, 0, new Size(80, 80), new Size(100, 100)); // Min
																											// size:
																											// 80,
																											// Max:
																											// 100
																											// for
																											// eye
																											// detection
		
	
		// Draw mark-ups on input image
		
		//System.out.println(eyesRect.length + "eyes has been deteced");

//		for (int i = 0; i < eyesRect.length; i++) {
//
//			System.out.println("eyes has been deteced at: " + eyesRect[i].x + " " + eyesRect[i].y + ", with size: "
//					+ eyesRect[i].height + ", " + eyesRect[i].width);
//
//			for (int m = eyesRect[i].y; m < eyesRect[i].y + eyesRect[i].height; m++) {
//
//				for (int n = eyesRect[i].x; n < eyesRect[i].x + eyesRect[i].width; n++) {
//
//					// grayImageMat.setPixel(n, m, 0);
//					processedImg.setRGB(n, m, 0);
//				}
//			}
//		}

		return eyes.toArray();
	}

	private static BufferedImage mat2Img(Mat in) 
	{
		BufferedImage out;
		byte[] data = new byte[in.height() * in.width() * (int) in.elemSize()];
		int type;
		in.get(0, 0, data);

		if (in.channels() == 1)
			type = BufferedImage.TYPE_BYTE_GRAY;
		else
			type = BufferedImage.TYPE_3BYTE_BGR;

		out = new BufferedImage(in.width(), in.height(), type);

		out.getRaster().setDataElements(0, 0, in.width(), in.height(), data);
		return out;
	}

	public BufferedImage getRawImg() {
		return rawImg;
	}

	public BufferedImage getProcessedImg() {
		return processedImg;
	}

	public BufferedImage getEditedImg() {
		return editedImg;
	}

	private static BufferedImage cloneBufferedImageFrom(BufferedImage bi) {
		ColorModel cm = bi.getColorModel();
		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		WritableRaster raster = bi.copyData(null);
		return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
	}

	private static BufferedImage scaleBufferedImage(BufferedImage before, int newWidth, int newHeight) {
		int oldWidth = before.getWidth();
		int oldHeight = before.getHeight();
		BufferedImage after = new BufferedImage(newWidth, newHeight, before.getType());
		AffineTransform at = new AffineTransform();
		at.scale((float) newWidth / (float) oldWidth, (float) newHeight / (float) oldHeight);
		AffineTransformOp scaleOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		after = scaleOp.filter(before, after);
		return after;
	}

	private boolean isTransparent(int pixel) {
		if ((pixel >> 24) == 0x00) {
			return true;
		}

		return false;
	}
}
