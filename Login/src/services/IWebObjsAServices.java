package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjA3;
import entity.WebObjsA;

public interface IWebObjsAServices {
	public void addMore(List<WebObjsA>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);	
	public WebObjsA findById(String factNo,String factcode,String yymm);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<WebObjsA> findByYymm(String factno,String yymm);
	
	public List<VWebobjA> findByVwebobja(String yymm);
	public List<VWebobjA2> findByVwebobja2(String yymm);
	public List<VWebobjA3> findByVwebobja3(String yymmdd);
	public List<WebObjsA> findObjByDay(String yymmdd);
	public List<WebObjsA> findObjByMonth(String yymm);
	public List<String[]> findNoInput(String yymmdd);

}
