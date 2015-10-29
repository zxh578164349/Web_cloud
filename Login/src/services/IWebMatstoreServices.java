package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebMatstoreServices {
	// 穝糤
	public void addMatstore(WebMatstore store);

	// 沮兵ン琩高计沮
	public List<WebMatstore> selMatstore(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebMatstore findById(WebMatstoreId id);

	public List<WebMatstore> selByYymm(String yymm, String factNo);

	public void delete(WebMatstoreId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
