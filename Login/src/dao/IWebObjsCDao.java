package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebObjsC;

public interface IWebObjsCDao {
	public void add(WebObjsC obj);
	public void addMore(List<WebObjsC>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymmdd);	
	public WebObjsC findById(String factNo,String factcode,String yymmdd);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<String[]> findNoInput(String yymmdd);
	public List<WebObjsC> findObjByDay(String yymmdd);

}
