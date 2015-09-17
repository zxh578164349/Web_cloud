package dao;

import java.util.List;

import entity.KyVisabillm;
import util.PageBean;

public interface IKyVisaBillmDao {
	public PageBean findPageBean(int pageSize, int page, String factNo,String BillNo,String visaMk);
	public void add(KyVisabillm vbm);
	public KyVisabillm findById(String factNo,String visaSort,String billNo);
	public void delete(String factNo,String visaSort,String billNo);
	
	public List<KyVisabillm>findByVisaMk(String visaMk);
	public List<KyVisabillm> findByVisaMk2(String visaMk);
	public List<KyVisabillm>findAllVbm();
	
	
	

}
