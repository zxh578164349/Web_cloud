package services;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebMats2Services {
	// �s�W
	public void addMats2(WebMats2 mats2);

	// �ھڱ���d�߼ƾ�
	public List<WebMats2> selMats2(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebMats2 findById(WebMats2Id id);

	public List<WebMats2> selByYymm(String yymm, String factNo);

	public void delete(WebMats2Id id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
