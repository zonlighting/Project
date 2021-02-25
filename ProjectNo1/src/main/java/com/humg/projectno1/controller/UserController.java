package com.humg.projectno1.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.humg.projectno1.dto.RegisterUserForm;
import com.humg.projectno1.entity.User;
import com.humg.projectno1.service.UserService;
import com.humg.projectno1.util.UploadFile;

@RestController
@RequestMapping("/user")
public class UserController {
  final static String LOCAL_HOST = "http://localhost:8080";
  ModelMapper modelMapper = new ModelMapper();

  @Autowired
  private UserService userService;

  @Autowired
  public JavaMailSender emailSender;

  // Đăng nhập
  @PostMapping(value = "/login")
  public ResponseEntity<String> login(@RequestParam String userName,
      @RequestParam String password) {
    try {
      User user = userService.findUser(userName, password);
      if (user.getStatus() == 0) {
        return new ResponseEntity<String>("Denied", HttpStatus.FORBIDDEN);
      } else {
        return new ResponseEntity<String>("Success", HttpStatus.OK);
      }
    } catch (Exception e) {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Đăng kí
  @PostMapping("/registerUser")
  public ResponseEntity<String> registerUser(@ModelAttribute RegisterUserForm registerUserFrom) {
    try {
      if (userService.checkRegister(registerUserFrom) == true) {
        return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
      } else {
        String path = "";
        path = UploadFile.saveFile(registerUserFrom.getAvatar());
        User user = modelMapper.map(registerUserFrom, User.class);
        user.setAvatar(path);
        userService.createUser(user);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(registerUserFrom.getEmail());
        message.setSubject("Confirm Account");
        message.setText(
            LOCAL_HOST + "/user/registerConfirm?userName=" + registerUserFrom.getUserName());
        this.emailSender.send(message);
        return new ResponseEntity<String>("success", HttpStatus.OK);
      }

    } catch (Exception e) {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Xác nhận email khi đăng kí tài khoản
  @GetMapping("/registerConfirm")
  public ResponseEntity<String> registerConfirm(@RequestParam String userName) {
    if (userService.registerConfirm(userName) > 0) {
      return new ResponseEntity<String>("success", HttpStatus.OK);
    } else {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Quên mật khẩu
  @PostMapping("/fogetUser")
  public ResponseEntity<String> fogetUser(@RequestParam String userName) {
    try {
      if (!userService.checkUser(userName)) {
        return new ResponseEntity<String>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
      } else {
        User user = userService.findUserByUserName(userName);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Password");
        message.setText(user.getPassword());
        this.emailSender.send(message);
        return new ResponseEntity<String>("success", HttpStatus.OK);
      }

    } catch (Exception e) {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Thay đổi thông tin tài khoản
  @PostMapping("/changeInfo")
  public ResponseEntity<String> changeInfo(@ModelAttribute RegisterUserForm userFrom) {
    try {
      String path = UploadFile.saveFile(userFrom.getAvatar());
      User user = modelMapper.map(userFrom, User.class);
      user.setAvatar(path);
      userService.changeInfo(user);
      return new ResponseEntity<String>("success", HttpStatus.OK);

    } catch (Exception e) {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Thay đổi mật khẩu
  @PostMapping("/changePassword")
  public ResponseEntity<String> changePassword(@RequestParam String username,
      @RequestParam String password) {
    try {
      userService.changePassword(username, password);
      return new ResponseEntity<String>("success", HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<String>("Failed", HttpStatus.BAD_REQUEST);
    }
  }

  // Thông tin tài khoản
  @PostMapping("/infoUser")
  public ResponseEntity<User> showInfoUser(@RequestParam String userName) {
    User user = userService.findUserByUserName(userName);
    return new ResponseEntity<User>(user, HttpStatus.OK);
  }
}
