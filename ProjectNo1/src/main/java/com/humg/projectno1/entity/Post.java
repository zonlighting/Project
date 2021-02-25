package com.humg.projectno1.entity;


import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class Post {
  private int idPost;
  private int idUser;
  private String title;
  private String content;
  private String checkIn;
  private LocalDateTime time;
  private List<String> images;
  private List<String> videos;

}
