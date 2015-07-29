package services;

import java.util.List;

import util.PageBean;
import entity.KyzMat;

public interface IKyzMatServices {
	public void add(KyzMat mat);
	public PageBean findPageBean(int pageSize,int page,String fromDate,String endDate,String matCname,String bNo,String mNo,String sNo,String factNo,String matNo);
	public String makeMatNo(String bNo,String mNo,String sNo);
	public KyzMat findById(String matNo);
	public void delete(String matNo);
	public List<KyzMat> findWithNoPage(String fromDate, String endDate,String matCname, String bNo, String mNo, String sNo,String factNo,String matNo);
			

}
