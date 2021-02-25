package com.humg.projectno1.service;

import java.util.List;
import com.humg.projectno1.entity.Post;

public interface PostService {

  int addPost(Post post);

  List<Post> showPostByFriend(long userId);

}
