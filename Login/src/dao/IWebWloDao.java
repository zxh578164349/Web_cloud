package dao;

import java.util.List;

import util.PageBean;

import entity.Webmix2;
import entity.Webwlo;
import entity.WebwloId;

public interface IWebWloDao {
	// �s�W
	public void addWebWloDao(Webwlo wlo);

	// �ھڱ��d�߼ƾ�
	public List<Webwlo> selectWloDao(String factNo, String yymm, int page,
			int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public Webwlo findById(WebwloId id);

	public List<Webwlo> selByYymm(String yymm, String factNo);

	public void delete(WebwloId id);

	public Webwlo selBycan(String factNo, java.util.Date date, String factCode);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<Webwlo> findByAny(String factNo, String beginDate,String endDate);
	public Webwlo findById(String factNo,String factCode,String yymm);
	
}
