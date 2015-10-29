package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebGwDao {
	// �s�W
	public void addGw(WebGw gw);

	// �ھڱ���d�߼ƾ�
	public List<WebGw> selectGw(String factNo, String yymm, int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebGw findById(WebGwId id);

	public List<WebGw> selByYymm(String yymm, String factNo);

	public void delete(WebGwId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
