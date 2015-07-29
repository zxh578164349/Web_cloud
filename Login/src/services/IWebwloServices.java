package services;

import java.util.Date;
import java.util.List;

import util.PageBean;

import entity.Webcost;
import entity.Webmix2;
import entity.Webwlo;
import entity.WebwloId;

public interface IWebwloServices {
	// 穝糤
	public void addWebWloDao(Webwlo wlo);

	// 沮兵ン琩高计沮
	public List<Webwlo> selectWloDao(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public Webwlo findById(WebwloId id);

	public List<Webwlo> selByYymm(String yymm, String factNo);

	public void delete(WebwloId id);

	public Webwlo selBycan(String factNo, Date date, String factCode);
	public boolean check(String factNo,String factCode,String yymm);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
	
	public List<Webwlo> findByAny(String factNo, String beginDate,String endDate);
	public Webwlo findById(String factNo,String factCode,String yymm);
}
