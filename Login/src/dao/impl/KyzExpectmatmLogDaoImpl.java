package dao.impl;

import dao.Basedao;
import dao.IKyzExpcetmatmLogDao;
import entity.KyzExpectmatmLog;

public class KyzExpectmatmLogDaoImpl extends Basedao implements IKyzExpcetmatmLogDao{

	public void add(KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		super.merge(log);
	}

}
