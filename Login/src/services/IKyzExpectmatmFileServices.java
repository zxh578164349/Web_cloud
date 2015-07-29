package services;

import java.util.List;

import entity.KyzExpectmatmFile;

public interface IKyzExpectmatmFileServices {
	public void add(KyzExpectmatmFile file);
	public List<KyzExpectmatmFile> findByBillNo(String billNo);
	public void delete(KyzExpectmatmFile file);

}
