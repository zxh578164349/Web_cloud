package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebTabpomfileDao;
import entity.KyzExpectmatmLog;
import entity.WebTabpomfile;

public class WebTabpomfileDaoImpl extends Basedao implements IWebTabpomfileDao{
	public WebTabpomfile findById(String pomNo, String fileName) {
		// TODO Auto-generated method stub
		String hql="from WebTabpomfile where id. webTabpom.pomNo=? and id.filename=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, pomNo);
		query.setString(1, fileName);
		return (WebTabpomfile)query.uniqueResult();
	}

	public void delete(String pomNo, String fileName,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		super.delete(this.findById(pomNo, fileName),log);
	}

	public List<Object[]> findByPomNo(String pomNo) {
		// TODO Auto-generated method stub
		String hql="select id.webTabpom.pomNo,id.filename,createdate from WebTabpomfile where id.webTabpom.pomNo=?";
		String[]objs={pomNo};
		List<Object[]>list=super.findAll(hql, objs);
		return list;
	}

	public void add(WebTabpomfile file) {
		// TODO Auto-generated method stub
		super.merge(file);
	}

	

}
