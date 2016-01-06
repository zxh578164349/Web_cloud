package services;

import util.PageBean;
import entity.WebBussinessletter;

public interface IWebBussinessletterServices {
	public void add(WebBussinessletter buss);
	public PageBean findPageBean(int pageSize,int page,String billNo,String factNo);
	public String makeBillNo(String factNo,String createDate);

}
