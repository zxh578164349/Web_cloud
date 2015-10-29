package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebProfitServices {
	// �s�W
	public void addProfit(WebProfit profit);

	// �ھڱ���d�߼ƾ�
	public List<WebProfit> selProfit(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebProfit findById(WebProfitId id);

	public List<WebProfit> selByYymm(String yymm, String factNo);

	public void delete(WebProfitId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
