package dao;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.Webmix2;
import entity.Webproduted;
import entity.WebprodutedId;

public interface IWebProdutedDao {
	// �s�W
	public void addWebProdutedDao(Webproduted produte);

	// �ھڱ��d�߼ƾ�
	public List<Webproduted> selectProduted(String factNo, String yymm,
			int page, int rows);

	// �d�ߥ����ƾڪ��`����
	public int totlePage(String factNo, String yymm);

	public Webproduted findById(WebprodutedId id);

	public List<Webproduted> selByYymm(String yymm, String factNo);

	public void delete(WebprodutedId id,KyzExpectmatmLog delLog);

	public Webproduted selBycan(String factNo, java.util.Date date,
			String factCode);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<Webproduted> findByAny(String factNo,String beginDate,String endDate);
	public Webproduted findById(String factNo,String factCode,String yymm);
	
	public List<Webproduted>findByFactNoYm(String factNo,String yymm);
}
