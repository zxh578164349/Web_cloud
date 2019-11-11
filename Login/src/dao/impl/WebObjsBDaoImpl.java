package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebObjsBDao;
import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;

public class WebObjsBDaoImpl extends Basedao implements IWebObjsBDao{

	public List<VWebobjBYdate> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebobjBYdate where id.yymmdd like ? order by id.webFact.id.factNo,id.webFact.id.factArea";
		String[]objs={yymm+"%"};
		List<VWebobjBYdate>list=super.findAll(hql, objs);
		return list;
	}

	public List<VWebydatabyfcode2> findByYymm2(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebydatabyfcode2 where id.yymmdd like ?";
		String[]objs={yymm+"%"};
		List<VWebydatabyfcode2>list=super.findAll(hql, objs);
		return list;
	}

}
