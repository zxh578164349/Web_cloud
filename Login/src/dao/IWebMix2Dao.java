package dao;

import java.sql.Date;
import java.util.List;

import util.PageBean;

import entity.Webcost;
import entity.Webmix2;
import entity.Webmix2Id;
import entity.Webwlo;


public interface IWebMix2Dao {
	// 穝糤
		public void addWebMix2(Webmix2 mix2);

		// 沮兵ン琩高计沮
		public List<Webmix2> selectMix2(String factNo, String yymm, int page,
				int rows);

		// 琩高场计沮羆计
		public int totlePage(String factNo, String yymm);

		public Webmix2 findById(Webmix2Id id);

		public List<Webmix2> selByYymm(String yymm, String factNo);

		public void delete(Webmix2Id id);
		
		public Webmix2 selBycan(String factNo,java.util.Date date,String factCode);
		public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
		
		public List<Webmix2> findByAny(String factNo, String beginDate,String endDate);
		public Webmix2 findById(String factNo,String factCode,String yymm);

}
