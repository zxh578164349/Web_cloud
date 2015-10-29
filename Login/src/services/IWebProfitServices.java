package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebProfitServices {
	// 穝糤
	public void addProfit(WebProfit profit);

	// 沮兵ン琩高计沮
	public List<WebProfit> selProfit(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebProfit findById(WebProfitId id);

	public List<WebProfit> selByYymm(String yymm, String factNo);

	public void delete(WebProfitId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
