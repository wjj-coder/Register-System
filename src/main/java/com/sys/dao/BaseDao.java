package com.sys.dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.CollectionUtils;

public abstract class BaseDao extends HibernateDaoSupport {
	
	@Resource
	private SessionFactory sf;
	
	@PostConstruct
	public void setMySessionFactory(){			
		super.setSessionFactory(sf);
	}
	public void save(Object object) {
		getHibernateTemplate().merge(object);
	}

	public void delete(Object object) {
		getHibernateTemplate().delete(object);
	}
	public void deleteList(List<?> list) {
		getHibernateTemplate().deleteAll(list);
	}
	public void update(Object object) {
		getHibernateTemplate().update(object);
	} 	
	public <E extends Serializable> void add(E ins) {
		getHibernateTemplate().save(ins);
	}

	public <E extends Serializable> void update(E ins) {
		getHibernateTemplate().update(ins);
	}

	// 通过主键ID返回一个数据对象
	public <E extends Serializable> E findById(Class<E> cla, Integer id) {		
		return getHibernateTemplate().get(cla, id);
	}

	// 获得Hibernate中的一个session
	public Session openSession() {
		return SessionFactoryUtils.getSession(getSessionFactory(), true);
	}
	
	public void saveOrUpdate(Object object) {
		getHibernateTemplate().saveOrUpdate(object);
	}
	
	public <E> List<E> listRaw(@SuppressWarnings("rawtypes") List list) {
		if (CollectionUtils.isEmpty(list)) {
			List<E> lst = new ArrayList<E>();
			return lst;
		} else {
			@SuppressWarnings("unchecked")
			List<E> res = (List<E>)list;
			return res;
		}
	}

	public <E> List<E> list(final String hql, final List<Object> params) {
		return list(hql, params.toArray());
	}

	public <E> List<E> list(final String hql, final Object... params) {
		
		List<E> list = getHibernateTemplate().execute(new HibernateCallback<List<E>>() {

			@Override
			public List<E> doInHibernate(Session session) throws HibernateException,
					SQLException {
				
				Query query = session.createQuery(hql);
				int index = 0;
				for (Object param : params) {
					query.setParameter(index++, param);
				}
				List<E> list = listRaw(query.list());
				return list;
			}
		});
		return list;
	}


}