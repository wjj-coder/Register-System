package com.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sys.dao.UserDao;
import com.sys.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	public void saves(User user) {
		userDao.saves(user);
	}

	public void updates(User user) {
		userDao.updates(user);
	}

	public void deletes(User user) {
		userDao.deletes(user);
	}

	// get a object
	public User getById(int userId) {
		return userDao.getById(userId);
	}

	public User getByEmailAddress(String emailAddress) {
		return userDao.getByEmailAddress(emailAddress);
	}

	public User getByEmailAndStatus(String emailAddress) {
		return userDao.getByEmailAndStatus(emailAddress);
	}

}
