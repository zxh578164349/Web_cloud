package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebMats2Services {
	// 穝糤
	public void addMats2(WebMats2 mats2);

	// 沮兵ン琩高计沮
	public List<WebMats2> selMats2(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebMats2 findById(WebMats2Id id);

	public List<WebMats2> selByYymm(String yymm, String factNo);

	public void delete(WebMats2Id id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
