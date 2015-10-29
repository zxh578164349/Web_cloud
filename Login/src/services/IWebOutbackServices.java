package services;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebOutbackServices {
	// �s�W
	public void addOutback(WebOutback back);

	// �ھڱ���d�߼ƾ�
	public List<WebOutback> selOutback(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebOutback findById(WebOutbackId id);

	public List<WebOutback> selByYymm(String yymm, String factNo);

	public void delete(WebOutbackId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
