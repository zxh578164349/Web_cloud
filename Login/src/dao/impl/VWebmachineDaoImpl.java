package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebmachineDao;

import entity.VWebmachine;

public class VWebmachineDaoImpl extends Basedao implements IVWebmachineDao{

	public VWebmachine findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebmachine where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		VWebmachine machine=(VWebmachine)query.uniqueResult();
		return machine;
	}

}
