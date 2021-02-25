package com.humg.projectno1.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.humg.projectno1.dao.PostDao;
import com.humg.projectno1.entity.Post;
import com.humg.projectno1.service.PostService;

@Service
public class PostServiceImpl implements PostService {

  @Autowired
  private PostDao postDao;

  @Override
  public int addPost(Post post) {
    if (postDao.addPost(post) > 0) {
      return postDao.addImageVideoPost(post.getIdUser(), post.getImages(), post.getVideos());
    }
    return 0;
  }

  @Override
  public List<Post> showPostByFriend(long userId) {
    List<Post> list = postDao.showPostByFriend(userId);
    for (Post post : list) {
      post.setImages(postDao.showImagePost(post.getIdPost()));
      post.setVideos(postDao.showVideoPost(post.getIdPost()));
    }
    return list;
  }

}
