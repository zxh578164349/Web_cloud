package services.impl;

import java.util.List;
import entity.*;
import dao.*;
import services.*;
import util.PageBean;

public class WebMats2ServicesImpl implements IWebMats2Services {
	private IWebMats2Dao mats2Dao;

	public void setMats2Dao(IWebMats2Dao mats2Dao) {
		this.mats2Dao = mats2Dao;
	}

	public void addMats2(WebMats2 mats2) {
		// TODO Auto-generated method stub
		mats2Dao.addMats2(mats2);
	}

	public List<WebMats2> selMats2(String factNo, String yymm, int page,
			int rows) {
		return mats2Dao.selectMats2(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return mats2Dao.totlePage(factNo, yymm);
	}

	public WebMats2 findById(WebMats2Id id) {
		// TODO Auto-generated method stub
		return mats2Dao.findById(id);
	}

	public List<WebMats2> selByYymm(String yymm, String factNo) {
		return mats2Dao.selByYymm(yymm, factNo);
	}

	public void delete(WebMats2Id id) {
		// TODO Auto-generated method stub
		mats2Dao.delete(id);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return mats2Dao.findPageBean(pageSize, page, factNo, yymm);
	}

}
