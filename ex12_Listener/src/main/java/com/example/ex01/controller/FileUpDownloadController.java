package com.example.ex01.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.ex01.domain.dto.FileDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/file")
public class FileUpDownloadController {
	
	public final static String UPLOAD_PATH = "c:\\upload";
	
	@GetMapping("/upload")
	public void upload() {
		log.info("GET /file/upload...");		
	}
	
	@PostMapping("/upload")
	public void upload_post(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
		log.info("POST /file/upload..."+file);		
		log.info(file.getOriginalFilename());
		log.info(file.getSize()+" byte");
		
		//폴더생성
		String dir = UPLOAD_PATH+File.separator+UUID.randomUUID();
		File dirPath = new File(dir);
		if(!dirPath.exists())
			dirPath.mkdirs();
		
		//파일명 추출
		String filename = file.getOriginalFilename();
		//파일객체 생성
		File fileObject = new File(dir,filename);
		//업로드 처리
		file.transferTo(fileObject);
	
	}
	
	@PostMapping("/multiUpload")
	public String upload_post_multi(@RequestParam("files") MultipartFile[] files) throws IllegalStateException, IOException {
		log.info("POST /file/upload..."+files);		
		
		//폴더생성
		String dir = UPLOAD_PATH+File.separator+UUID.randomUUID();
		File dirPath = new File(dir);
		if(!dirPath.exists())
			dirPath.mkdirs();
		
		for(MultipartFile file : files) {
			String filename = file.getOriginalFilename();
			File fileObject = new File(dir,filename);
			file.transferTo(fileObject);		
		}
	
		return "file/upload";
	}
	
	
	@PostMapping("/multiUpload_dto")
	public String upload_post_multi_dto(FileDto fileDto) throws IllegalStateException, IOException {
		log.info("POST /file/upload..." + fileDto);		
		return "file/upload";
	}
	
	
	@GetMapping("/list")
	public void list(Model model) {
		log.info("GET /file/list...");
		
		File base_dir = new File(UPLOAD_PATH);
		File[] sub_dir = base_dir.listFiles();
		for(File dir : sub_dir) {
			log.info("sub dir.." + dir.getPath());
			for(File f :dir.listFiles()) {
				System.out.println(f);
			}
		}
		
		model.addAttribute("root",base_dir);
		
	}
	
	
	@GetMapping(value="/download",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downliadFile(String filepath) throws UnsupportedEncodingException {
		log.info("GET /file/download" + filepath);
		//FileSystemResource : 파일 시스템의 특정 파일로부터 정보를 가져오는데 사용
		Resource resource = new FileSystemResource(filepath);
		//파일명 추출
		String filename = resource.getFilename();
		//헤더 정보 추가
		HttpHeaders headers = new HttpHeaders();
		//ISO-8859 라틴어 (특수믄자등 깨짐 방지)
		headers.add("Content-Disposition", "attachment; filename="+new String(filename.getBytes("UTF-8"),"ISO-8859-1"));
		//리소스 , 파일 정보가 포함된 헤더 , 상태정보를 전달
		return new ResponseEntity<Resource>(resource,headers,HttpStatus.OK);
	}
	
	
	
	
}