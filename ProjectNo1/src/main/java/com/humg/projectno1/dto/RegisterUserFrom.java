package com.humg.projectno1.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RegisterUserFrom {
	private String userName;
	private String password;
	private String name;
	private int age;
	private int sex;
	private MultipartFile  avatar;
	private String address;
	private String email;
}
