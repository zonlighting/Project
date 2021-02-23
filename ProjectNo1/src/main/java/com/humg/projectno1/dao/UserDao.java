package com.humg.projectno1.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.humg.projectno1.dto.RegisterUserFrom;
import com.humg.projectno1.entity.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User findUser(String userName, String password) {
		String sql = "SELECT * FROM usertbl WHERE username = ? AND password = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName, password);
	}

	public boolean checkRegister(RegisterUserFrom registerUserFrom) {
		String sql = "SELECT * FROM usertbl WHERE username = ? OR email = ?";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class),
				registerUserFrom.getUserName(), registerUserFrom.getEmail());
		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public int registerConfirm(String userName) {
		String sql = "UPDATE usertbl SET status = ? WHERE username = ? ";
		return jdbcTemplate.update(sql, 1, userName);
	}

	public void createUser(User user) {
		String sql = "INSERT INTO usertbl (username, password,name,age,sex,address,avatar,email) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getPassword(),
				user.getName(), user.getAge(), user.getSex(),
				user.getAddress(),user.getAvatar(), user.getEmail());

	}

	public boolean checUser(String userName) {
		String sql = "SELECT * FROM usertbl WHERE username = ?";
		List<User> users = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userName);
		if (users.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public User findUserByUserName(String userName) {
		String sql = "SELECT * FROM usertbl WHERE username = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);

	}

	public void changeInfo(User user) {
		String sql = "UPDATE usertbl SET name = ?, age = ?, address = ?, sex = ?, avatar = ? WHERE username = ? ";
		jdbcTemplate.update(sql,user.getName(),user.getAge(),user.getAddress(),user.getSex(),user.getAvatar(),user.getUserName());
	}

	public void changePassword(String username, String password) {
		String sql = "UPDATE usertbl SET password = ? WHERE username = ? ";
		jdbcTemplate.update(sql,password,username);
	}


}
