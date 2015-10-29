package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebPersonServices {
	// 穝糤
	public void addPerson(WebPerson person);

	// 沮兵ン琩高计沮
	public List<WebPerson> selPerson(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebPerson findById(WebPersonId id);

	public List<WebPerson> selByYymm(String yymm, String factNo);

	public void delete(WebPersonId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
