package dao;

import java.util.List;

import entity.KyzExpectmatmFile;

public interface IKyzExpectmatmFileDao {
	public void add(KyzExpectmatmFile file);
	public List<KyzExpectmatmFile> findByBillNo(String billNo);
	public void delete(KyzExpectmatmFile file);
	public KyzExpectmatmFile findById(Integer id);
	public void delete(Integer id);


}
