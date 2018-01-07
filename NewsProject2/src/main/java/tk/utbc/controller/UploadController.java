/**
 * 
 */
package tk.utbc.controller;



import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tk.utbc.util.MediaUtils;
import tk.utbc.util.UploadFileUtils;

/**
 * @author KEN
 * Park Jong-hyun
 */
@Controller
public class UploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class); 
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@RequestMapping(value="/uploadAjax", method=RequestMethod.GET)
	public void uploadAjax() {
		
	}
	@ResponseBody
	@RequestMapping(value="/uploadAjax", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	public ResponseEntity<List<String>> uploadAjax(@RequestParam("files") MultipartFile[] files) throws Exception {
		ResponseEntity<List<String>> entity = null;
		List<String> list = new ArrayList<String>();
		
		for(MultipartFile multipartFile : files) {
			list.add(UploadFileUtils.uploadFile(uploadPath, multipartFile.getOriginalFilename(), multipartFile.getBytes()));			
		}
//		logger.info("originalFileName : "+files.getOriginalFilename());
//		logger.info("size : "+ files.getSize());
//		logger.info("contentType : " + files.getContentType());
		//entity = new ResponseEntity<String>(file.getOriginalFilename(), HttpStatus.CREATED);
		entity = new ResponseEntity<List<String>>(list, HttpStatus.CREATED);
		
		return entity;
		
	}
	
	@RequestMapping(value="/uploadProfile", method=RequestMethod.GET)
	public void uploadProfilePicture() {
		
	}
	@ResponseBody
	@RequestMapping(value="/uploadProfile", method=RequestMethod.POST, produces="text/plain;charset=UTF-8" )
	public ResponseEntity<String> uploadProfilePicture(MultipartFile file) throws Exception{
		ResponseEntity<String> entity = null;
		logger.info("originalFileName : "+file.getOriginalFilename());
		logger.info("size : "+ file.getSize());
		logger.info("contentType : " + file.getContentType());
		entity = new ResponseEntity<String>(UploadFileUtils.uploadProfilePicture(uploadPath, file.getOriginalFilename() , file.getBytes()), HttpStatus.CREATED);
		return entity;
 	}

	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(@RequestParam(value="location" , required=false) String location, String fileName) throws Exception{
	    InputStream in = null; 
	    ResponseEntity<byte[]> entity = null;
	    logger.info("FILE NAME: " + fileName);
	    logger.info("location: " + location);
	    try{
	      String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	      MediaType mType = MediaUtils.getMediaType(formatName);
	      HttpHeaders headers = new HttpHeaders();
	      
	      if(location.equals("profile")) {
	    	  in = new FileInputStream(uploadPath+"/profile_picture/"+fileName);	    	  
	      }
	      else{
	    	  in = new FileInputStream(uploadPath+fileName);
	      }
	      if(mType != null){
	        headers.setContentType(mType);
	      }else{
	        fileName = fileName.substring(fileName.indexOf("_")+1);       
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition", "attachment; filename=\""+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	      }
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
	    }catch(Exception e){
	      e.printStackTrace();
	      entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally{
	      in.close();
	    }
	      return entity;    
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(String fileName){	    
	    logger.info("delete file: "+ fileName);
	    ResponseEntity<String> entity = null;
	    String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
	    MediaType mType = MediaUtils.getMediaType(formatName);
	
		if(mType != null){ 
		  String front = fileName.substring(0,12);
		  String end = fileName.substring(14);
		  new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete();
		}
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete();
		entity =  new ResponseEntity<String>("deleted", HttpStatus.OK);
		return entity;
	}  
	@ResponseBody
	@RequestMapping(value="/deleteAllFiles", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@RequestParam("files[]") String [] files){
		logger.info("delete All Files : " + files);
		
		if(files == null || files.length == 0) {
			return new ResponseEntity<String>("deleted", HttpStatus.OK);
		}
		
		for(String fileName : files) {
			String formatName = fileName.substring(fileName.lastIndexOf(".")+1);
			MediaType mType = MediaUtils.getMediaType(formatName);
			if(mType != null){
				String front = fileName.substring(0, 12);
				String end = fileName.substring(14);
				new File(uploadPath + (front + end).replace('/',File.separatorChar)).delete();
			}
			new File(uploadPath + fileName.replace('/',File.separatorChar)).delete();
		}
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
	
}
