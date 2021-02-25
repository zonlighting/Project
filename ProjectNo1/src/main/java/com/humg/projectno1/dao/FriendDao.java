package com.humg.projectno1.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.humg.projectno1.dto.UserInfo;

@Repository
public class FriendDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public int addFriend(long userId, long friendId) {
    String sql = "INSERT INTO friendstbl (userid, friendid) VALUES (?, ?)";
    return jdbcTemplate.update(sql, userId, friendId);
  }

  public int confirmFriend(long userId, long friendId) {
    String sql = "UPDATE friendstbl SET status = ? WHERE userid = ? AND friendid = ? ";
    return jdbcTemplate.update(sql, 1, friendId, userId);
  }

  public List<UserInfo> showFriend(long userId) {
    String sql =
        "SELECT DISTINCT usertbl.* FROM usertbl inner join (select * From friendstbl where status =1 AND ( userid = ? OR friendid = ?) ) u on (usertbl.id=u.userid or usertbl.id=u.friendid ) ";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserInfo>(UserInfo.class), userId,
        userId);
  }

}
