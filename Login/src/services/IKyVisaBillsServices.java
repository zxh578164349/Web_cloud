package services;

import java.util.List;

import util.PageBean;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyVisabillsId;

public interface IKyVisaBillsServices {
	public List<KyVisabills> findVisaBills(String visaSort,String billNo);
	public void add(KyVisabills vbs);
	public KyVisabills findById(String factNo,String visaSort,String billNo,String itemNo);
	public void delete(String factNo, String visaSort, String billNo,String itemNo);
	public PageBean findPageBean(int pageSize,int page,String userName,String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,String email);
	public List<KyVisabills> findByFNN(String factNo,String userName);
	public PageBean findPageBean_tw(int pageSize,int page,String userName,String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,String email);
	public int findKyVisaBills_Int();
	
	public int findBillsWithNo(String visaSort,String billNo);
	public int findBillsWithNo2(String visaSort,String billNo);//visaMk=N  flowMk=Y
	public void delete(KyVisabills bils);
			

}
