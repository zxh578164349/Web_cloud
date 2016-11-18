package services;

import java.util.List;

import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import util.PageBean;

public interface IKyVisabillmServices {
	public void add(KyVisabillm vbm);
	public KyVisabillm findById(String factNo, String visaSort, String billNo);
	public void delete(String factNo,String visaSort,String billNo,KyzExpectmatmLog delLog);
	
	public List<KyVisabillm>findByVisaMk(String visaMk);
	public List<KyVisabillm> findAllVbm();
	public List<KyVisabillm> findByVisaMk2(String visaMk);
	
	public KyVisabillm findByBillNo(String billNo);
	
	public List<KyVisabillm>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public List<KyVisabillm>findBefor2Month2();//兩箇月之前沒有添加刪除標記的，且沒有簽核的函文20160929
	public void addLarge(List<KyVisabillm>list);//大批量添加 20160216
	
	public boolean add2(KyVisabillm vbm);

}
