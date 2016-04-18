//package editor;
//
//import java.awt.GridLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//import javax.swing.ImageIcon;
//import javax.swing.JButton;
//import javax.swing.JFileChooser;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JPanel;
//import javax.swing.filechooser.FileNameExtensionFilter;
//
////import SCeflieManager.FacialFeature;
//
//public class ScelfieClient extends JFrame {
//
//	private ScelfieNetworkManager networkManager;
//	private BufferedImage rawImg;
//	private SCeflieManager sceflieManager;
//
//	private JPanel mainPanel;
//	private JPanel btnPanel;
//	private JButton uploadBtn;
//	// private JButton processBtn;
//	private JButton stickerBtn;
//	private JButton saveLocallyBtn;
//	private JButton saveToServerBtn;
//
//	private JLabel imageLabel;
//
//	public ScelfieClient() {
//
//		super();
//		initializeVariables();
//		createGUI();
//		addActionAdapters();
//		setSize(800, 600);
//		// loadTestImage();
//		// testFacialRecognizer();
//		setVisible(true);
//	}
//
//	void initializeVariables() {
//
//		sceflieManager = new SCeflieManager();
//		// sceflieManager.loadTestImage("test_image_2.png");
//		// sceflieManager.processedRawImg();
//		// sceflieManager.addStickerToImg("stickers/heart.png",
//		// FacialFeature.Eye);
//
//		// sceflieManager.addStickerToImg("stickers/star.png",
//		// FacialFeature.Mouth);
//
//		// networkManager = new ScelfieNetworkManager();
//		// networkManager.uploadImageMultipart("testImage.png");
//
//		// No parameter for this POST method call
//		// System.out.println(ScelfieNetworkManager.excutePost("http://www.non-solus.com/scelfie/upload.php",
//		// ""));
//		// System.out.println(ScelfieNetworkManager.uploadImage("testImage.png"));
//
//		uploadBtn = new JButton("Upload image to edit");
//		// processBtn = new JButton("Process image");
//		stickerBtn = new JButton("Add sticker");
//		saveLocallyBtn = new JButton("Save locally");
//		saveToServerBtn = new JButton("Save to server");
//		mainPanel = new JPanel();
//		btnPanel = new JPanel(new GridLayout(5, 1));
//
//		imageLabel = new JLabel();
//	}
//
//	void createGUI() {
//
//		mainPanel.add(imageLabel);
//		btnPanel.add(uploadBtn);
//		// btnPanel.add(processBtn);
//		btnPanel.add(stickerBtn);
//		btnPanel.add(saveLocallyBtn);
//		btnPanel.add(saveToServerBtn);
//		mainPanel.add(btnPanel);
//		add(mainPanel);
//	}
//
//	void addActionAdapters() {
//
//		// Set up upload image button
//		uploadBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//
//				JFileChooser chooser = new JFileChooser();
//				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png");
//				chooser.setFileFilter(filter);
//				int returnVal = chooser.showOpenDialog(null);
//				if (returnVal == JFileChooser.APPROVE_OPTION) {
//					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getAbsolutePath());
//
//					sceflieManager.loadTestImage(chooser.getSelectedFile().getAbsolutePath());
//					ImageIcon icon = new ImageIcon(sceflieManager.getEditedImg());
//					imageLabel.setIcon(icon);
//				}
//
//			}
//		});
//		
//		// Set up sticker button
//		stickerBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				sceflieManager.addStickerToImg("stickers/heart.png", FacialFeature.Eye);
//				ImageIcon icon = new ImageIcon(sceflieManager.getEditedImg());
//				imageLabel.setIcon(icon);
//			}
//		});
//
//		// Set up save to local button
//		saveLocallyBtn.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				try {
//
//					// Save edited image to disk
//					JFileChooser jfc = new JFileChooser();
//					FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG", "png");
//					jfc.setFileFilter(filter);
//					int retVal = jfc.showSaveDialog(null);
//					if (retVal == JFileChooser.APPROVE_OPTION) {
//						File f = jfc.getSelectedFile();
//						
//						String fileName = f.getCanonicalPath();
//			            if (!fileName.endsWith(".png")) {
//			                f = new File(fileName + ".png");
//			            }
//						
//						//String test = f.getAbsolutePath();
//						ImageIO.write((BufferedImage) sceflieManager.getEditedImg(), "png", f);
//					}
//
//				} catch (IOException exception) {
//
//				}
//			}
//		});
//		
//		// Set up saveToServerBtn
//		saveToServerBtn.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				
//				// Save to tmp directory
//				File tmpFile = new File("tmp.png");
//				try {
//					
//					ImageIO.write((BufferedImage) sceflieManager.getEditedImg(), "png", tmpFile);
//					ScelfieNetworkManager.uploadImageMultipart(tmpFile.getAbsolutePath());
//					
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//				
//				
//			}
//		});
//
//	}
//
//	public static void main(String[] args) {
//
//		new ScelfieClient();
//
//	}
//
//}
