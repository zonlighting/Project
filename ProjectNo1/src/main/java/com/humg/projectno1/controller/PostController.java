package com.humg.projectno1.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.humg.projectno1.dto.PostForm;
import com.humg.projectno1.entity.Post;
import com.humg.projectno1.service.PostService;
import com.humg.projectno1.util.UploadFile;

@RestController
@RequestMapping("/post")
public class PostController {
  @Autowired
  private PostService postService;

  ModelMapper modelMapper = new ModelMapper();

  // Tạo bài viết mới
  @PostMapping("/addPost")
  public ResponseEntity<Integer> addPost(@ModelAttribute PostForm form) {
    try {
      ArrayList<String> images = new ArrayList<String>();
      ArrayList<String> videos = new ArrayList<String>();
      for (MultipartFile imageList : form.getImagesFile()) {
        if (!imageList.getOriginalFilename().equals("")) {
          String path = UploadFile.saveFile(imageList);
          images.add(path);
        }
      }
      for (MultipartFile videoList : form.getVideosFile()) {
        if (!videoList.getOriginalFilename().equals("")) {
          String path = UploadFile.saveFile(videoList);
          videos.add(path);
        }
      }
      Post post = modelMapper.map(form, Post.class);
      post.setTime(LocalDateTime.now());
      post.setImages(images);
      post.setVideos(videos);
      int add = postService.addPost(post);
      return new ResponseEntity<Integer>(add, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/showPostByFriend")
  public ResponseEntity<List<Post>> showPostByFriend(@RequestParam long userId) {
    List<Post> posts = postService.showPostByFriend(userId);
    return new ResponseEntity<List<Post>>(posts, HttpStatus.OK);
  }
}
