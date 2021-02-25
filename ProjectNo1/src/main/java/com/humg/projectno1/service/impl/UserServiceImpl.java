package com.humg.projectno1.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humg.projectno1.dao.UserDao;
import com.humg.projectno1.dto.RegisterUserForm;
import com.humg.projectno1.entity.User;
import com.humg.projectno1.service.UserService;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public User findUser(String userName, String password) {
    return userDao.findUser(userName, password);
  }

  @Override
  public boolean checkRegister(RegisterUserForm registerUserFrom) {
    return userDao.checkRegister(registerUserFrom);
  }

  @Override
  public int registerConfirm(String userName) {
    return userDao.registerConfirm(userName);
  }

  @Override
  public void createUser(User user) {
    userDao.createUser(user);
  }

  @Override
  public boolean checkUser(String userName) {
    return userDao.checUser(userName);
  }

  @Override
  public User findUserByUserName(String userName) {
    return userDao.findUserByUserName(userName);
  }



  @Override
  public void changePassword(String username, String password) {
    userDao.changePassword(username, password);
  }

  @Override
  public void changeInfo(User user) {
    userDao.changeInfo(user);
  }



}
