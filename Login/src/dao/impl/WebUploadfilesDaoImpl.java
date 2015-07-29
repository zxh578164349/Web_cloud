package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebUploadFileDao;
import entity.WebUploadfiles;

public class WebUploadfilesDaoImpl extends Basedao implements IWebUploadFileDao{

	public void add(WebUploadfiles file) {
		// TODO Auto-generated method stub
		super.merge(file);
	}

	public List<WebUploadfiles> findByName(String userName, String factNo) {
		// TODO Auto-generated method stub
		String hql="from WebUploadfiles where fileuser=? and filefactno=?";
		String[]objs={userName,factNo};
		return super.findAll(hql, objs);
	}

	public boolean delete(int id) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		WebUploadfiles file=this.findById(id);
		if(file!=null){
			flag=true;
			super.delete(file);
		}
		return flag;
	}

	public WebUploadfiles findById(int id) {
		// TODO Auto-generated method stub
		String hql="from WebUploadfiles where id=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, id);
		WebUploadfiles file=(WebUploadfiles)query.uniqueResult();
		return file;
	}

}
