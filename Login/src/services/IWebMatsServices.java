package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebMatsServices {
	// 穝糤
	public void addMats(WebMats mats);

	// 沮兵ン琩高计沮
	public List<WebMats> selMats(String factNo, String yymm, int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebMats findById(WebMatsId id);

	public List<WebMats> selByYymm(String yymm, String factNo);

	public void delete(WebMatsId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
