package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebMatstoreDao {
	// 穝糤
	public void addMatstore(WebMatstore matstore);

	// 沮兵ン琩高计沮
	public List<WebMatstore> selectMatstore(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计s
	public int totlePage(String factNo, String yymm);

	public WebMatstore findById(WebMatstoreId id);

	public List<WebMatstore> selByYymm(String yymm, String factNo);

	public void delete(WebMatstoreId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
