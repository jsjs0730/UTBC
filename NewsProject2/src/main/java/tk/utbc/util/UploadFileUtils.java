/**
 * 
 */
package tk.utbc.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

/**
 * @author KEN
 * Park Jong-hyun
 */
public class UploadFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);	
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_"+ originalName;
		String savedPath = calcPath(uploadPath);
		//System.out.println("savedpath : " + savedPath);
		File target = new File(uploadPath + savedPath , savedName);
		FileCopyUtils.copy(fileData, target);
		
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		if(MediaUtils.getMediaType(formatName) != null) {
			uploadedFileName = makeThumbnail(uploadPath, savedPath, savedName);
		}else {
			uploadedFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadedFileName;
	}
	
	public static String uploadProfilePicture(String uploadPath, String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		String savedName = uid.toString() + "_" + originalName;
		String savedPath = File.separator + "profile_picture";
		String savedFile = uploadPath + File.separator + savedPath + File.separator + savedName;
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target);
		String formatName = originalName.substring(originalName.lastIndexOf(".")+1);
		String uploadedFileName = null;
		if(MediaUtils.getMediaType(formatName) != null) {
			BufferedImage sourceImg = ImageIO.read(target);
			BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.ULTRA_QUALITY, 150, 150 );
			ImageIO.write(destImg, formatName.toUpperCase(), target);
			uploadedFileName = savedName;
		}else {
			uploadedFileName = null;
		}
		return uploadedFileName;
	}

	
	private static String makeIcon(String uploadPath, String path, String fileName) {
		String iconName = uploadPath + path + File.separator + fileName;
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}

	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH)+1);
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		logger.info(datePath);
		
		return datePath;
	}
	
	private static void makeDir(String uploadPath, String... paths) {
		if(new File(uploadPath + paths[paths.length -1]).exists()) {
			return;
		}
		for(String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			if(!dirPath.exists()) {
				dirPath.mkdirs();
			}
		}
	}
	
	private static String makeThumbnail(String uploadPath, String path, String fileName) throws Exception{
		BufferedImage sourceImg = ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		System.out.println("makeTu / fileName" + fileName);
		String thumbnailName = uploadPath + path + File.separator + "s_" + fileName;
		
		File newFile = new File(thumbnailName);
		
		String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
//		System.out.println("thumbnail return : " +thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/'));
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
}
