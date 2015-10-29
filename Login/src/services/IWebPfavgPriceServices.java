package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebPfavgPriceServices {
	// 穝糤
	public void addprice(WebPfavgprice price);

	// 沮兵ン琩高计沮
	public List<WebPfavgprice> selPfavgprice(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebPfavgprice findById(WebPfavgpriceId id);

	public List<WebPfavgprice> selByYymm(String yymm, String factNo);

	public void delete(WebPfavgpriceId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
