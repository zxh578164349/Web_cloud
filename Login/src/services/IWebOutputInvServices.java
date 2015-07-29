package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebOutputInvServices {
	// 穝糤
	public void addoutPut(WebOutputinv output);

	// 沮兵ン琩高计沮
	public List<WebOutputinv> selOutputinv(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebOutputinv findById(WebOutputinvId id);

	public List<WebOutputinv> selByYymm(String yymm, String factNo);

	public void delete(WebOutputinvId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
