package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebProfitCountServices {
	// 穝糤
	public void addProfitCount(WebProfitcount count);

	// 沮兵ン琩高计沮
	public List<WebProfitcount> selProfitcount(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebProfitcount findById(WebProfitcountId id);

	public List<WebProfitcount> selByYymm(String yymm, String factNo);

	public void delete(WebProfitcountId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
