package dao;

import java.util.List;

import entity.KyzExpectmatmLog;
import entity.KyzMat;
import util.PageBean;

public interface IKyzMatDao {
	public PageBean findPageBean(int pageSize,int page,String fromDate,String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo);
	public void add(KyzMat mat);
	public List<String> findAllMatNo();
	public KyzMat findById(String matNo);
	public void delete(String matNo,KyzExpectmatmLog delLog);
	public List<KyzMat>findWithNoPage(String fromDate,String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo);

}
