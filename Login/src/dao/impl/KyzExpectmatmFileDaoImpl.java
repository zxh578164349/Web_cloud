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

	public KyzExpectmatmFile findById(Integer id) {
		// TODO Auto-generated method stub
		return (KyzExpectmatmFile)super.findById(id,KyzExpectmatmFile.class);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		KyzExpectmatmFile file=findById(id);
		super.delete(file);
	}

	/**
	 * 日期:2016/2/25
	 * 描述:
	 */
	
	
	public List<String> findBillNo(String factNo, String visaTypeM) {
		// TODO Auto-generated method stub
		String hql="select billno from KyzExpectmatmFile where factNo=? and visaTypeM=?";
		String[]objs={factNo,visaTypeM};
		return super.findAll(hql, objs);
	}

}
