package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebSubSortDao;
import entity.WebSubsort;

public class WebSubsortDaoImpl extends Basedao implements IWebSubSortDao {

	public List<WebSubsort> findById(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from WebSubsort where webMajorsort.majorid=?";
		Integer[] objs = { id };
		return super.findAll(hql, objs);
	}

	public String findById2(Integer id) {
		// TODO Auto-generated method stub
		String hql = "select subname from WebSubsort where subid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		return (String) query.uniqueResult();
	}

}
