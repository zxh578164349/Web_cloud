package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebPfavgPriceServices {
	// �s�W
	public void addprice(WebPfavgprice price);

	// �ھڱ���d�߼ƾ�
	public List<WebPfavgprice> selPfavgprice(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebPfavgprice findById(WebPfavgpriceId id);

	public List<WebPfavgprice> selByYymm(String yymm, String factNo);

	public void delete(WebPfavgpriceId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
