package dao;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebZwcashDao {
	// �s�W
	public void addZwcash(WebZwcash cash);

	// �ھڱ���d�߼ƾ�
	public List<WebZwcash> selectZwcash(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebZwcash findById(WebZwcashId id);

	public List<WebZwcash> selByYymm(String yymm, String factNo);

	public void delete(WebZwcashId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
