package dao;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebSideDao {
	// 穝糤
	public void addSide(WebSide side);

	// 沮兵ン琩高计沮
	public List<WebSide> selectSide(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebSide findById(WebSideId id);

	public List<WebSide> selByYymm(String yymm, String factNo);

	public void delete(WebSideId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
