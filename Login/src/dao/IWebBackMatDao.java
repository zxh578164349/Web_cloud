package dao;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebBackMatDao {
	// 穝糤
	public void addBackMat(WebBackmat backmat);

	// 沮兵ン琩高计沮
	public List<WebBackmat> selectBackMat(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebBackmat findBackById(WebBackmatId id);

	public void delete(WebBackmatId id);

	// 沮る琩计沮
	public List<WebBackmat> selByYymm(String yymm, String factNo);
	
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
