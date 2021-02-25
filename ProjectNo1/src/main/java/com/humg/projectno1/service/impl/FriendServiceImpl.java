package com.humg.projectno1.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.humg.projectno1.dao.FriendDao;
import com.humg.projectno1.dto.UserInfo;
import com.humg.projectno1.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {
  @Autowired
  private FriendDao friendDao;

  @Override
  public int addFriend(long userId, long friendId) {
    return friendDao.addFriend(userId, friendId);
  }

  @Override
  public int confirmFriend(long userId, long friendId) {
    return friendDao.confirmFriend(userId, friendId);
  }

  @Override
  public List<UserInfo> showFriend(long userId) {
    List<UserInfo> list = friendDao.showFriend(userId);
    List<UserInfo> result =
        list.stream().filter(line -> line.getId() != userId).collect(Collectors.toList());
    return result;
  }
}
