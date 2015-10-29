package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IKyzScmClassmDao;

public class KyzScmClassmDaoImpl extends Basedao implements IKyzScmClassmDao{

	public List<Object[]> findBN() {
		// TODO Auto-generated method stub
		String hql="select distinct id.scmBclassNo,scmBclassNm from KyzScmClassm order by id.scmBclassNo";		
		return super.findAll(hql, null);
	}

	public List<Object[]> findMN(String bigNo) {
		// TODO Auto-generated method stub
		String hql="select distinct id.scmMclassNo,scmMclassNm from KyzScmClassm where id.scmBclassNo=? order by id.scmMclassNo";
		String[]objs={bigNo};
		return super.findAll(hql, objs);
	}

	public List<Object[]> findSN(String middleNo) {
		// TODO Auto-generated method stub
		String hql="select distinct id.scmSclassNo,scmSclassNm from KyzScmClassm where id.scmMclassNo=? order by id.scmSclassNo";
		String[]objs={middleNo};
		return super.findAll(hql, objs);
	}

}
