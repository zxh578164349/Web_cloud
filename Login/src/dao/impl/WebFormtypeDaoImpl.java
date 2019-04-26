package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebFormtypeDao;
import entity.WebFormtype;

public class WebFormtypeDaoImpl extends Basedao implements IWebFormtypeDao{

	public List<Object[]> findWebformByFactnoTypeno(String factNo,
			String typeNo) {
		// TODO Auto-generated method stub
		String hql="select fid,fname from WebFormtype where webType.id.factNo=? and webType.id.typeNo=?";
		String[]objs={factNo,typeNo};
		List<Object[]>list=super.findAll(hql, objs);
		return list;
	}

	public List<WebFormtype> findWebformByFactno(String factNo) {
		// TODO Auto-generated method stub
		String hql="from WebFormtype where webType.id.factNo=?";
		String[]objs={factNo};
		List<WebFormtype>list=super.findAll(hql, objs);
		return list;
	}

}
