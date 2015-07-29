package services;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebBackMatServices {
	// �s�W
	public void addBackMat(WebBackmat backmat);

	// �ھڱ���d�߼ƾ�
	public List<WebBackmat> selBackmats(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	// �R������ƾ�
	public boolean deleteBackMat(String billNo);

	public WebBackmat findBackById(WebBackmatId id);

	public void delete(WebBackmatId id);

	public List<WebBackmat> selByYymm(String yymm, String factNo);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
