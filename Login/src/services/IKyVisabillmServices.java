package services;

import entity.KyVisabillm;
import util.PageBean;

public interface IKyVisabillmServices {
	public PageBean findPageBean(int pageSize, int page, String factNo,String billNo,String visaMk);
	public void add(KyVisabillm vbm);
	public KyVisabillm findById(String factNo, String visaSort, String billNo);
	public void delete(String factNo,String visaSort,String billNo);

}
