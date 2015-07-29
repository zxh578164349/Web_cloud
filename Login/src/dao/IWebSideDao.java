package dao;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebSideDao {
	// �s�W
	public void addSide(WebSide side);

	// �ھڱ���d�߼ƾ�
	public List<WebSide> selectSide(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebSide findById(WebSideId id);

	public List<WebSide> selByYymm(String yymm, String factNo);

	public void delete(WebSideId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
