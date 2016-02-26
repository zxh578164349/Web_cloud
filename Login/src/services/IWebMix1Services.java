package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebYieldData;
import entity.WebYieldDataId;


public interface IWebMix1Services {
	public void add(WebYieldData mix1);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm);

	public List<WebYieldData> findByFactNo(String factNo);

	public WebYieldData findById(WebYieldDataId id);

	public void delete(WebYieldDataId id,KyzExpectmatmLog delLog);
	
	public boolean check(String factNo,String factCode,String yymm);

}
