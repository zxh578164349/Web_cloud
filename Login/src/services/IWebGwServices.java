package services;

import java.util.List;
import util.PageBean;
import entity.WebGw;
import entity.WebGwId;

public interface IWebGwServices {
	// 穝糤
	public void addWebGw(WebGw gw);

	// 沮兵ン琩高计沮
	public List<WebGw> selGw(String factNo, String yymm, int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebGw findById(WebGwId id);

	public List<WebGw> selByYymm(String yymm, String factNo);

	public void delete(WebGwId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
