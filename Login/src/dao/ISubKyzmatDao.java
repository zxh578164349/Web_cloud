package dao;

import java.util.List;

import util.PageBean;

import entity.KyzMat;
import entity.SubKyzmat;

public interface ISubKyzmatDao {
	public void add(SubKyzmat sub);
	public List<String>findByMatNo(String matNo);
	public PageBean findPageBean(int pageSize,int page,String fromDate,String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo);
	public List<SubKyzmat>findWithNoPage(String fromDate,String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo);
	public SubKyzmat findById(String userName,String factNo,String matNo);
	public void delete(String userName,String factNo,String matNo);

}
