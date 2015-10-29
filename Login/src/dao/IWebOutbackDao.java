package dao;

import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebOutbackDao {
	// 穝糤
	public void addOutback(WebOutback out);

	// 沮兵ン琩高计沮
	public List<WebOutback> selectOutback(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebOutback findById(WebOutbackId id);

	public List<WebOutback> selByYymm(String yymm, String factNo);

	public void delete(WebOutbackId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
