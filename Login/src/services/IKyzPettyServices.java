package services;

import java.util.List;

import entity.KyzPetty;
import util.PageBean;

public interface IKyzPettyServices {
	public PageBean findPageBean(int pageSize,int page,String factNo,String billNo,String createTime);
	public void add(KyzPetty kyzpetty);
	public String makeItemNo(String factNo,String payDate);
	public KyzPetty findById(String factNo,String billNo);
	public void delete(String factNo,String billNo);
	public String makeBillNo(String factNo,String payDate);
	
	public List<KyzPetty> findByAnyThing(String factNo,String dateTime,String dateTime2, String expenseMk,String taxmMk);
			

}
