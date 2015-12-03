package services;

import java.util.List;

import util.PageBean;
import entity.WebFixed;

public interface IWebFixedServices {
	public void addWebFixed(WebFixed fix);

	public void deleteWebFixed(String id);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm_s,String lostmk);

	public WebFixed findById(String id);

	public List findById2(String id);

	public List findAllFixedassetsId();

	public String makeSssetscoding(String factNo, String majorid, String yymm);

	public List<WebFixed> findByFactNo(String factNo, String yymm,String yymm_s,String lostmk);
	public String findByFixId(String fixedId);
}
