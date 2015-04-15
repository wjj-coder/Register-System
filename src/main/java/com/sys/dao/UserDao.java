package com.sys.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.sys.entity.User;

@Repository
public class UserDao extends BaseDao {

	public void saves(User user) {
		this.getHibernateTemplate().save(user);
	}

	public void updates(User user) {
		this.getHibernateTemplate().update(user);
	}

	public void deletes(User user) {
		this.getHibernateTemplate().delete(user);
	}

	// get a object
	public User getById(int userId) {
		String hql = "from User where userId=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(userId);
		List<User> user = list(hql, parm);
		return user.size() == 0 ? null : user.get(0);
	}

	public User getByEmailAddress(String emailAddress) {
		String hql = "from User where userName=?";
		List<Object> parm = new ArrayList<Object>();
		parm.add(emailAddress);
		List<User> user = list(hql, parm);
		return user.size() == 0 ? null : user.get(0);
	}

	public User getByEmailAndStatus(String emailAddress) {
		String hql = "from User where userName=? and status=1";
		List<Object> parm = new ArrayList<Object>();
		parm.add(emailAddress);
		List<User> user = list(hql, parm);
		return user.size() == 0 ? null : user.get(0);
	}
}
