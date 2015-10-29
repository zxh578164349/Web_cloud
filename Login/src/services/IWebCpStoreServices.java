package services;

import java.util.List;
import util.PageBean;
import entity.WebCpstore;
import entity.WebCpstoreId;

public interface IWebCpStoreServices {
	// �s�W
	public void addWebCpStore(WebCpstore store);

	// �ھڱ���d�߼ƾ�
	public List<WebCpstore> selCpstore(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebCpstore findById(WebCpstoreId id);

	public List<WebCpstore> selByYymm(String yymm, String factNo);

	public void delete(WebCpstoreId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
