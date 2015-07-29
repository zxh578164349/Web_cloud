package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebBackpfDao {
	// 穝糤
	public void addBackpf(WebBackpf backpf);

	// 沮兵ン琩高计沮
	public List<WebBackpf> selectBackpf(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebBackpf findBackById(WebBackpfId id);

	public List<WebBackpf> selByYymm(String yymm, String factNo);

	public void delete(WebBackpfId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
