package services;

import java.util.Date;
import java.util.List;

import util.PageBean;

import entity.Webmix2;
import entity.Webmixperson;
import entity.WebmixpersonId;

public interface IWebMixPersonServices {
	// 穝糤
	public void addWebMixPerson(Webmixperson mixperson);

	// 沮兵ン琩高计沮
	public List<Webmixperson> selectMixperson(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public Webmixperson findById(WebmixpersonId id);

	public List<Webmixperson> selByYymm(String yymm, String factNo);

	public void delete(WebmixpersonId id);

	public Webmixperson selBycan(String factNo, Date date, String factCode);
	
	public boolean check(String factNo,String factCode,String yymm);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
	public List<Webmixperson> findByAny(String factNo,String beginDate,String endDate);
	public Webmixperson findById(String factNo,String factCode,String yymm);
}
