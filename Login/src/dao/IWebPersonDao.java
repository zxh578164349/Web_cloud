package dao;

import java.util.List;
import util.PageBean;

import entity.*;

public interface IWebPersonDao {
	// �s�W
	public void addPerson(WebPerson person);

	// �ھڱ���d�߼ƾ�
	public List<WebPerson> selectPerson(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public WebPerson findById(WebPersonId id);

	public List<WebPerson> selByYymm(String yymm, String factNo);

	public void delete(WebPersonId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
