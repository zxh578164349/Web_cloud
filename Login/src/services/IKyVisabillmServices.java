package services;

import java.util.List;

import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import util.PageBean;

public interface IKyVisabillmServices {
	public PageBean findPageBean(int pageSize, int page, String factNo,String billNo,String visaMk);
	public void add(KyVisabillm vbm);
	public KyVisabillm findById(String factNo, String visaSort, String billNo);
	public void delete(String factNo,String visaSort,String billNo,KyzExpectmatmLog delLog);
	
	public List<KyVisabillm>findByVisaMk(String visaMk);
	public List<KyVisabillm> findAllVbm();
	public List<KyVisabillm> findByVisaMk2(String visaMk);
	
	public KyVisabillm findByBillNo(String billNo);
	
	public List<KyVisabillm>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<KyVisabillm>list);//大批量添加 20160216

}
