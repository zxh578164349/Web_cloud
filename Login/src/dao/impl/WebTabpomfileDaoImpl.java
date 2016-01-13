package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebTabpomfileDao;
import entity.WebTabpomfile;

public class WebTabpomfileDaoImpl extends Basedao implements IWebTabpomfileDao{
	public WebTabpomfile findById(String pomNo, String fileName) {
		// TODO Auto-generated method stub
		String hql="from WebTabpomfile where id.webTabpom.pomNo=? and id.filename=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, pomNo);
		query.setString(1, fileName);
		return (WebTabpomfile)query.uniqueResult();
	}

	public void delete(String pomNo, String fileName) {
		// TODO Auto-generated method stub
		super.delete(this.findById(pomNo, fileName));
	}

	public List<WebTabpomfile> findByPomNo(String pomNo) {
		// TODO Auto-generated method stub
		String hql="from WebTabpomfile where id.webTabpom.pomNo=?";
		String[]objs={pomNo};
		List<WebTabpomfile>list=super.findAll(hql, objs);
		for(int i=0;i<list.size();i++){//解決hibernate延遲問題
			list.get(i).getId().getWebTabpom();
		}
		return list;
	}

	

}
