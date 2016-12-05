package services.impl;

import java.util.List;

import dao.IWebTabpomfileDao;
import entity.KyzExpectmatmLog;
import entity.WebTabpomfile;
import services.IWebTabpomfileServices;

public class WebTabpomfileServicesImpl implements IWebTabpomfileServices{
	private IWebTabpomfileDao webtabfiledao;
	

	public void setWebtabfiledao(IWebTabpomfileDao webtabfiledao) {
		this.webtabfiledao = webtabfiledao;
	}

	public WebTabpomfile findById(String pomNo, String fileName) {
		// TODO Auto-generated method stub
		return webtabfiledao.findById(pomNo, fileName);
	}

	public void delete(String pomNo, String fileName,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webtabfiledao.delete(pomNo, fileName,log);
	}

	public List<Object[]> findByPomNo(String pomNo) {
		// TODO Auto-generated method stub
		return webtabfiledao.findByPomNo(pomNo);
	}

	public void add(WebTabpomfile file) {
		// TODO Auto-generated method stub
		webtabfiledao.add(file);
	}

}
