package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebProfitCountServices {
	// �s�W
	public void addProfitCount(WebProfitcount count);

	// �ھڱ���d�߼ƾ�
	public List<WebProfitcount> selProfitcount(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebProfitcount findById(WebProfitcountId id);

	public List<WebProfitcount> selByYymm(String yymm, String factNo);

	public void delete(WebProfitcountId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
