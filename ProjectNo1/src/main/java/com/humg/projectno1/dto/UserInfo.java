package com.humg.projectno1.dto;

import lombok.Data;

@Data
public class UserInfo {
  private long id;
  private String name;
  private int age;
  private int sex;
  private String avatar;
  private String address;
  private String email;
}
