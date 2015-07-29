package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebBackpfDao {
	// �s�W
	public void addBackpf(WebBackpf backpf);

	// �ھڱ���d�߼ƾ�
	public List<WebBackpf> selectBackpf(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebBackpf findBackById(WebBackpfId id);

	public List<WebBackpf> selByYymm(String yymm, String factNo);

	public void delete(WebBackpfId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
