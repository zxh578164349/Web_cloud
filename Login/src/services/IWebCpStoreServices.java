package services;

import java.util.List;
import util.PageBean;
import entity.WebCpstore;
import entity.WebCpstoreId;

public interface IWebCpStoreServices {
	// 穝糤
	public void addWebCpStore(WebCpstore store);

	// 沮兵ン琩高计沮
	public List<WebCpstore> selCpstore(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebCpstore findById(WebCpstoreId id);

	public List<WebCpstore> selByYymm(String yymm, String factNo);

	public void delete(WebCpstoreId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
