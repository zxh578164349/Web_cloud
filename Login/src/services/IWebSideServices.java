package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebSideServices {
	// �s�W
	public void addside(WebSide side);

	// �ھڱ���d�߼ƾ�
	public List<WebSide> selSide(String factNo, String yymm, int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebSide findById(WebSideId id);

	public List<WebSide> selByYymm(String yymm, String factNo);

	public void delete(WebSideId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
