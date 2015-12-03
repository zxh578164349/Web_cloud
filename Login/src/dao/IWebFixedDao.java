package dao;

import java.util.List;

import util.PageBean;
import entity.WebFixed;

public interface IWebFixedDao {
	public void addWebFixed(WebFixed fix);

	public void deleteWebFixed(String id);

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String yymm,String yymm_s,String lostmk);

	public WebFixed findById(String id);

	public List findById2(String majorid);// �P�@�j�����Ҧ���ASSETSCODING

	public List findAllFixedassetsId();// �?�Ҧ�fixedassets_id

	public List<WebFixed> findByFactNo(String factNo, String yymm,String yymm_s,String lostmk);
	
	public String findByFixId(String fixedId);

}
