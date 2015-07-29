package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVWebydataawebcashoutDao;
import entity.VWebydataawebcashout;

public class VWebydataawebcashoutDaoImpl extends Basedao implements IVWebydataawebcashoutDao{

	public List<VWebydataawebcashout> findById(String factNo, String factCode,
			String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebydataawebcashout where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymm')=?";
		String[]objs={factNo,factCode,yymm};
		return super.findAll(hql, objs);
	}

	public VWebydataawebcashout findByIdOne(String factNo, String factCode,
			String yymmdd) {
		// TODO Auto-generated method stub
		String hql="from VWebydataawebcashout where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymmdd')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymmdd);
		VWebydataawebcashout cashout=(VWebydataawebcashout)query.uniqueResult();
		return cashout;
	}

}
