package dao;

import java.util.List;

import util.PageBean;

import entity.Webmix2;
import entity.Webproduted;
import entity.WebprodutedId;

public interface IWebProdutedDao {
	// 穝糤
	public void addWebProdutedDao(Webproduted produte);

	// 沮兵ン琩高计沮
	public List<Webproduted> selectProduted(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public Webproduted findById(WebprodutedId id);

	public List<Webproduted> selByYymm(String yymm, String factNo);

	public void delete(WebprodutedId id);

	public Webproduted selBycan(String factNo, java.util.Date date,
			String factCode);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
	public List<Webproduted> findByAny(String factNo,String beginDate,String endDate);
	public Webproduted findById(String factNo,String factCode,String yymm);
}
