package com.humg.projectno1.service;

import com.humg.projectno1.dto.RegisterUserFrom;
import com.humg.projectno1.entity.User;

public interface UserService {

	public User findUser(String userName, String password);

	public boolean checkRegister(RegisterUserFrom registerUserFrom);

	public int registerConfirm(String userName);

	public void createUser(User user);

	public boolean checkUser(String userName);

	public User findUserByUserName(String userName);

	public void changeInfo(User user);

	public void changePassword(String username, String password);

}
