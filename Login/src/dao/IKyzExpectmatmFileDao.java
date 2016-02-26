package dao;

import java.util.List;

import entity.KyzExpectmatmFile;
import entity.KyzExpectmatmLog;

public interface IKyzExpectmatmFileDao {
	public void add(KyzExpectmatmFile file);
	public List<KyzExpectmatmFile> findByBillNo(String billNo);
	public void delete(KyzExpectmatmFile file,KyzExpectmatmLog delLog);
	public KyzExpectmatmFile findById(Integer id);
	public void delete(Integer id);
	public List<String>findBillNo(String factNo,String visaTypeM);


}
