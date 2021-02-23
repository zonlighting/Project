package com.humg.projectno1.entity;

import lombok.Data;

@Data
public class User {
	private long id;
	private String userName;
	private String password;
	private String name;
	private int age;
	private String address;
	private int sex;
	private String avatar;
	private String email;
	private int status;
}
