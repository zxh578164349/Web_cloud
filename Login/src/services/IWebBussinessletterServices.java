package services;

import java.util.List;

import util.PageBean;
import entity.KyzContactletter;
import entity.WebBussinessletter;

public interface IWebBussinessletterServices {
	public void add(WebBussinessletter buss);
	public PageBean findPageBean(int pageSize,int page,String billNo,String factNo);
	public String makeBillNo(String factNo,String createDate);
	public WebBussinessletter findById(String billNo);
	public void delete(WebBussinessletter letter);
	public void delete(String billNo);
	
	public List<WebBussinessletter>findBefor2Month();//兩箇月之前沒有添加刪除標記的函文20160216
	public void addLarge(List<WebBussinessletter>list);//大批量添加 20160216

}
