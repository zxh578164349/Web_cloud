package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVKpifactDao;
import entity.VKpifact;

public class VKpifactDaoImpl extends Basedao implements IVKpifactDao{

	public List<VKpifact> findAll(String factNo,String yymm) {
		// TODO Auto-generated method stub
		String hql="from VKpifact where id.factNo=? and id.yymm=?";
	    String[]objs={factNo,yymm};
		return super.findAll(hql, objs);
		
		
		
	}

	public VKpifact findById(String factNo,String factCode,String yymm) {
		// TODO Auto-generated method stub
		String hql="from VKpifact where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VKpifact kpi=(VKpifact)query.uniqueResult();
		return kpi;
	}

}
