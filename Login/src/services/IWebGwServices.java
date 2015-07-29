package services;

import java.util.List;
import util.PageBean;
import entity.WebGw;
import entity.WebGwId;

public interface IWebGwServices {
	// �s�W
	public void addWebGw(WebGw gw);

	// �ھڱ���d�߼ƾ�
	public List<WebGw> selGw(String factNo, String yymm, int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebGw findById(WebGwId id);

	public List<WebGw> selByYymm(String yymm, String factNo);

	public void delete(WebGwId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
