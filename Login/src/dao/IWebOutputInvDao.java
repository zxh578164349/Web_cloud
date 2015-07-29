package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebOutputInvDao {
	// �s�W
	public void addOutput(WebOutputinv inv);

	// �ھڱ���d�߼ƾ�
	public List<WebOutputinv> selectOutputInv(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebOutputinv findById(WebOutputinvId id);

	public List<WebOutputinv> selByYymm(String yymm, String factNo);

	public void delete(WebOutputinvId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
