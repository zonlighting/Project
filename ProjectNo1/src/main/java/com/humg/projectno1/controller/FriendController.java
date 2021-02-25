package com.humg.projectno1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.humg.projectno1.dto.UserInfo;
import com.humg.projectno1.service.FriendService;

@RestController
@RequestMapping("/friend")
public class FriendController {

  @Autowired
  private FriendService friendService;

  // Thêm bạn
  @PostMapping("/addFriend")
  public ResponseEntity<String> addFriend(@RequestParam long userId, @RequestParam long friendId) {
    try {
      int add = friendService.addFriend(userId, friendId);
      if (add > 0) {
        return new ResponseEntity<String>("ADD", HttpStatus.OK);
      } else {
        return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
    }
  }

  // xác nhận kết bạn
  @PostMapping("/confirmFriend")
  public ResponseEntity<String> confirmFriend(@RequestParam long userId,
      @RequestParam long friendId) {
    try {
      int confirm = friendService.confirmFriend(userId, friendId);
      if (confirm > 0) {
        return new ResponseEntity<String>("ADD", HttpStatus.OK);
      } else {
        return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
      }
    } catch (Exception e) {
      return new ResponseEntity<String>("FAILED", HttpStatus.BAD_REQUEST);
    }
  }

  // Hiển thị danh sách bạn bè
  @PostMapping("/showFriend")
  public ResponseEntity<List<UserInfo>> showFriend(@RequestParam long userId) {
    try {
      List<UserInfo> list = friendService.showFriend(userId);
      return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
    } catch (Exception e) {
      return null;
    }
  }


}
