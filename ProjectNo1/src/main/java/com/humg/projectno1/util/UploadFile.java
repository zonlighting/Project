package com.humg.projectno1.util;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {
	public static String saveFile(MultipartFile multipartFile) throws IllegalStateException, IOException {
		var now = LocalDateTime.now();
		String fileNameImage = String.format("%s_%s", now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
				multipartFile.getOriginalFilename());
		String destination = System.getProperty("user.dir") + "/src/main/webapp/images/" + fileNameImage;
		File file = new File(destination);
		multipartFile.transferTo(file);
		return "/images/" + fileNameImage;
	}

	public static String saveVideo(MultipartFile multipartFile) throws IllegalStateException, IOException {
		String destination = System.getProperty("user.dir") + "/src/main/webapp/videos/"
				+ multipartFile.getOriginalFilename();
		File file = new File(destination);
		multipartFile.transferTo(file);
		return "/videos/" + multipartFile.getOriginalFilename();
	}
}
