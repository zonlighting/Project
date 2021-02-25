package com.humg.projectno1.service;

import java.util.List;
import com.humg.projectno1.dto.UserInfo;

public interface FriendService {

  int addFriend(long userId, long friendId);

  int confirmFriend(long userId, long friendId);

  List<UserInfo> showFriend(long userId);

}
