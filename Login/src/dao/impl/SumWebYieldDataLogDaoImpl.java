package dao.impl;

import dao.Basedao;
import dao.ISumWebYieldDataLogDao;
import entity.SumWebYieldDataLog;

public class SumWebYieldDataLogDaoImpl extends Basedao implements ISumWebYieldDataLogDao{

	public void add(SumWebYieldDataLog log) {
		// TODO Auto-generated method stub
		super.merge(log);
	}

}
