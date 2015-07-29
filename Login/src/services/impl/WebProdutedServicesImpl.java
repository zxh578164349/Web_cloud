package services.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.IWebProdutedDao;

import entity.Webproduted;
import entity.WebprodutedId;
import services.IWebProdutedServices;
import util.PageBean;

public class WebProdutedServicesImpl implements IWebProdutedServices {
	private IWebProdutedDao produtedDao;

	public IWebProdutedDao getProdutedDao() {
		return produtedDao;
	}

	public void setProdutedDao(IWebProdutedDao produtedDao) {
		this.produtedDao = produtedDao;
	}

	public void addWebProdutedDao(Webproduted produte) {
		produtedDao.addWebProdutedDao(produte);
	}

	public List<Webproduted> selectProduted(String factNo, String yymm,
			int page, int rows) {
		return produtedDao.selectProduted(factNo, yymm, page, rows);
	}

	public int totlePage(String factNo, String yymm) {
		return produtedDao.totlePage(factNo, yymm);
	}

	public Webproduted findById(WebprodutedId id) {
		return produtedDao.findById(id);
	}

	public List<Webproduted> selByYymm(String yymm, String factNo) {
		return produtedDao.selByYymm(yymm, factNo);
	}

	public void delete(WebprodutedId id) {
		produtedDao.delete(id);
	}

	public Webproduted selBycan(String factNo, Date date, String factCode) {
		// TODO Auto-generated method stub
		return produtedDao.selBycan(factNo, date, factCode);
	}

	public boolean check(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		Boolean flag=false;
		DateFormat format=new SimpleDateFormat("yyyyMM");
		Date date=null;
		try {
			date=format.parse(yymm);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Webproduted produted=this.selBycan(factNo, date, factCode);
		if(produted!=null){
			flag=true;
		}
		return flag;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return produtedDao.findPageBean(pageSize, page, factNo, yymm);
	}

	public List<Webproduted> findByAny(String factNo, String beginDate,
			String endDate) {
		// TODO Auto-generated method stub
		return produtedDao.findByAny(factNo, beginDate, endDate);
	}

	public Webproduted findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return produtedDao.findById(factNo, factCode, yymm);
	}

}
