package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IKyzExpectmatmFileDao;
import entity.KyzExpectmatmFile;

public class KyzExpectmatmFileDaoImpl extends Basedao implements IKyzExpectmatmFileDao{

	public void add(KyzExpectmatmFile file) {
		// TODO Auto-generated method stub
		super.merge(file);
	}

	public List<KyzExpectmatmFile> findByBillNo(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatmFile where billno=?";
		String[]objs={billNo};
		return super.findAll(hql, objs);
	}

	public void delete(KyzExpectmatmFile file) {
		// TODO Auto-generated method stub
		super.delete(file);
	}

}
