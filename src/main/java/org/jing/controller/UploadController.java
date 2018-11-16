package org.jing.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jing.domain.AttachFileDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnailator;

@Controller
@Log4j
public class UploadController {
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		log.info("upload form");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile,Model model) {
		
		String uploadFolder = "C:\\upload";
		
		log.info("----------------------------");
		
		for(MultipartFile multipartFile : uploadFile) {
			log.info("----------------------------");
			log.info("upload file name : " +multipartFile.getOriginalFilename());
			log.info("upload file size : " +multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch(Exception e) {
				log.error(e.getMessage());
			}//end catch
		}//end for
	}
	
	
	//날짜 폴더 생성
	private String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		return str.replace("-", File.separator);
	}
	
	//이미지 파일 판단
	private boolean checkImageType(File file) {
		try {
			log.info("#########################################");
			String contentType = Files.probeContentType(file.toPath());
			log.info(contentType.startsWith("image"));
			
			return contentType.startsWith("image");
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
		log.info("upload ajax");
	}
	
	//@RequestMapping(value =  "/uploadAjaxAction", method= {RequestMethod.GET, RequestMethod.POST})
	//@PostMapping("/uploadAjaxAction")
	//public void uploadAjaxPost(@RequestBody MultipartFile[] uploadFile) {
	
	@PostMapping(value="/uploadAjaxAction", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<AttachFileDTO>> uploadAjaxPost(MultipartFile[] uploadFile){
		log.info("update ajax post...");
		
		List<AttachFileDTO> list = new ArrayList<>();
		String uploadFolder = "C:\\upload";
		
		String uploadFolderPath = getFolder();
		//make folder
		File uploadPath = new File(uploadFolder, uploadFolderPath);
		log.info("upload path: " + uploadPath);
		
		if(uploadPath.exists() ==false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile multipartFile : uploadFile) {
			
			AttachFileDTO attachDTO = new AttachFileDTO();
			
			
			
			String uploadFileName = multipartFile.getOriginalFilename();
			
			//IE
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
			log.info("only file name: " + uploadFileName);
			attachDTO.setFileName(uploadFileName);
			
			UUID uuid = UUID.randomUUID();
			uploadFileName = uuid.toString() + "_" + uploadFileName;
			log.info("uploadfilename: " + uploadFileName);
			
			
			try {
				log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~try?!");
				File saveFile = new File(uploadPath, uploadFileName);
				multipartFile.transferTo(saveFile);
				
				attachDTO.setUuid(uuid.toString());
				attachDTO.setUploadPath(uploadFolderPath);
				log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@" +saveFile );
				
				//check image type file
				if(checkImageType(saveFile)) {
					attachDTO.setImage(true);
					log.info("여기 오십니까!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					
					try {
					FileOutputStream thumbnail = new FileOutputStream(new File(uploadPath,"s_" + uploadFileName));
					Thumbnailator.createThumbnail(multipartFile.getInputStream(),thumbnail,100,100);
					log.info("@#@#@#@#@#@#@#@#@#@#@#@#@" + thumbnail);
					thumbnail.close();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
				
				//
				list.add(attachDTO);
				log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				log.info(list);
			}catch(Exception e) {
				e.printStackTrace();
			} //end catch
			
		}//for
		
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
}
