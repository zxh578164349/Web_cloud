package dao;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebBackMatDao {
	// �s�W
	public void addBackMat(WebBackmat backmat);

	// �ھڱ���d�߼ƾ�
	public List<WebBackmat> selectBackMat(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebBackmat findBackById(WebBackmatId id);

	public void delete(WebBackmatId id);

	// �ھڤ���d�ƾ�
	public List<WebBackmat> selByYymm(String yymm, String factNo);
	
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
