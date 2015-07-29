package dao;

import java.util.List;

import util.PageBean;

import entity.*;

public interface IWebMachineDao {
	// �s�W
	public void addmachine(WebMachin machine);

	// �ھڱ���d�߼ƾ�
	public List<WebMachin> selectWebMachin(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebMachin findById(WebMachinId id);

	public List<WebMachin> selByYymm(String yymm, String factNo);

	public void delete(WebMachinId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
