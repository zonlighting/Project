package com.humg.projectno1.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.humg.projectno1.entity.Post;

@Repository
public class PostDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int addPost(Post post) {
    String sql = "INSERT INTO posttbl (iduser,title,content,checkIn,time) VALUES (?, ?, ?, ?, ?)";
    int addPost = jdbcTemplate.update(sql, post.getIdUser(), post.getTitle(), post.getContent(),
        post.getCheckIn(), post.getTime());


    return addPost;
  }

  public int addImageVideoPost(int idUser, List<String> images, List<String> videos) {
    for (String image : images) {
      String sql1 =
          "INSERT INTO imagetbl(idpost,pathimage) VALUES ((SELECT MAX(idpost) FROM posttbl where iduser = ? ), ?)";
      jdbcTemplate.update(sql1, idUser, image);
    }
    for (String video : videos) {
      String sql1 =
          "INSERT INTO videotbl(idpost,pathvideo) VALUES ((SELECT MAX(idpost) FROM posttbl where iduser = ? ), ?)";
      jdbcTemplate.update(sql1, idUser, video);
    }
    return 1;
  }

  public List<Post> showPostByFriend(long userId) {
    String sql =
        "SELECT * FROM `demo-spring-mvc-jdbc`.posttbl inner join (SELECT DISTINCT id FROM usertbl inner join (select * From friendstbl where status =1 AND ( userid = ? OR friendid = ?) ) u on (usertbl.id=u.userid or usertbl.id=u.friendid ) ) a on posttbl.iduser=a.id;";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Post>(Post.class), userId, userId);
  }

  public List<String> showImagePost(int idPost) {
    String sql = "SELECT pathimage FROM imagetbl WHERE idpost = ?;";
    return jdbcTemplate.queryForList(sql, String.class, idPost);
  }

  public List<String> showVideoPost(int idPost) {
    String sql = "SELECT pathvideo FROM videotbl WHERE idpost = ?;";
    return jdbcTemplate.queryForList(sql, String.class, idPost);
  }
}
