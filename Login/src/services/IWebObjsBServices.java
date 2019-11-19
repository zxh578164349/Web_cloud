package services;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.VWebobjA;
import entity.VWebobjA2;
import entity.VWebobjBObj3;
import entity.VWebobjBObj;
import entity.VWebobjBObj4;
import entity.VWebobjBObj5;
import entity.VWebobjBYdate;
import entity.VWebydatabyfcode2;
import entity.WebObjsB;

public interface IWebObjsBServices {
	public List<VWebobjBYdate> findByYymm(String yymm);
	public List<VWebydatabyfcode2> findByYymm2(String yymm);
	
	
	public void addMore(List<WebObjsB>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);	
	public WebObjsB findById(String factNo,String factcode,String yymm);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<WebObjsB> findByYymm(String factno,String yymm);
	
	public List<VWebobjBObj> findByYymm2(String factno,String yymm);
	public List<VWebobjBObj> findObjByDay(String yymmdd);
	public List<VWebobjBObj3> findByVwebobjb3(String yymmdd);
	public List<VWebobjBObj> findObjByMonth(String yymm);	
	public List<VWebobjBObj4> findVWebobjBObj4(String yymm);
	public List<VWebobjBObj5> findVWebobjBObj5(String yymm);
	public List<String[]> findNoInput(String yymmdd);
	
	public void add(WebObjsB obj);
	
	

}
