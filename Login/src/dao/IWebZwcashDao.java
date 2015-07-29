package dao;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebZwcashDao {
	// 穝糤
	public void addZwcash(WebZwcash cash);

	// 沮兵ン琩高计沮
	public List<WebZwcash> selectZwcash(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebZwcash findById(WebZwcashId id);

	public List<WebZwcash> selByYymm(String yymm, String factNo);

	public void delete(WebZwcashId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
