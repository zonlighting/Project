package com.humg.projectno1.dto;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class PostForm {
  private int idUser;
  private String title;
  private String content;
  private String checkIn;
  private MultipartFile[] imagesFile;
  private MultipartFile[] videosFile;
}
