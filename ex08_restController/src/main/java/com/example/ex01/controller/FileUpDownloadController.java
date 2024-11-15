package com.example.ex01.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequestMapping("/file")
public class FileUpDownloadController {

	@GetMapping("/upload")
	public void upload() {
		log.info("GET /file/upload");
	}
	
}
