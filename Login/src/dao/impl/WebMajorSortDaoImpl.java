package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebMajorSortDao;
import entity.WebMajorsort;

public class WebMajorSortDaoImpl extends Basedao implements IWebMajorSortDao {

	public List<WebMajorsort> findAll() {
		// TODO Auto-generated method stub
		String hql = "from WebMajorsort";
		return super.findAll(hql, null);
	}

	public String findById(Integer id) {
		// TODO Auto-generated method stub
		String hql = "select majorname from WebMajorsort where majorid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		return (String) query.uniqueResult();
	}

	public Integer findByName(String name) {
		// TODO Auto-generated method stub
		String hql = "select majorid from WebMajorsort where majorname=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		return (Integer) query.uniqueResult();
	}

}
