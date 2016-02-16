package services;

import java.util.List;

import util.PageBean;
import entity.KyzContactletter;

public interface IKyzContactLetterServices {
	public void add(KyzContactletter letter);
	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort,String billNo,String userNm,String timeCreate,String timeCreate2);			
	public String makeBillNo(String factNo, String timeCreate);
	public KyzContactletter findById(String factNo, String billNo);
	public void delete(String factNo,String billNo);
	public String findTitleByBillno(String billNo);
	
	public List<Object[]>findTitle(String factNo);
	
	public List<KyzContactletter>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<KyzContactletter>list);//大批量添加 20160216

}
