package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebMatsDao {
	// �s�W
	public void addMats(WebMats mats);

	// �ھڱ���d�߼ƾ�
	public List<WebMats> selectMats(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebMats findById(WebMatsId id);

	public List<WebMats> selByYymm(String yymm, String factNo);

	public void delete(WebMatsId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
