package dao;

import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebOutbackDao {
	// �s�W
	public void addOutback(WebOutback out);

	// �ھڱ���d�߼ƾ�
	public List<WebOutback> selectOutback(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebOutback findById(WebOutbackId id);

	public List<WebOutback> selByYymm(String yymm, String factNo);

	public void delete(WebOutbackId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
