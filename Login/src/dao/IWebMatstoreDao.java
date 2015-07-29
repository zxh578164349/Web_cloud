package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebMatstoreDao {
	// �s�W
	public void addMatstore(WebMatstore matstore);

	// �ھڱ���d�߼ƾ�
	public List<WebMatstore> selectMatstore(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����s
	public int totlePage(String factNo, String yymm);

	public WebMatstore findById(WebMatstoreId id);

	public List<WebMatstore> selByYymm(String yymm, String factNo);

	public void delete(WebMatstoreId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
