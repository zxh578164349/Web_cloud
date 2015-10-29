package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IKyzExpectmatmsDao;
import entity.KyzExpectmats;
import entity.KyzExpectmatsId;

public class KyzExpcetmatmsDaoImpl extends Basedao implements IKyzExpectmatmsDao{

	public KyzExpectmats findById(String factNo,String billNo, String itemNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmats where id.kyzExpectmatm.id.factNo=? and id.kyzExpectmatm.id. billNo=? and id.itemNo=? ";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, billNo);
		query.setString(2, itemNo);
		return (KyzExpectmats)query.uniqueResult();
	}

	public void delete(String factNo,String billNo, String itemNo) {
		// TODO Auto-generated method stub
		KyzExpectmats kyzs=this.findById(factNo,billNo, itemNo);
		super.delete(kyzs);
		
	}

}
