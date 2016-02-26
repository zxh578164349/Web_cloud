package dao;

import java.util.List;

import util.PageBean;
import entity.*;

public interface IWebMix1Dao {
	public void add(WebYieldData mix1);

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm);

	public List<WebYieldData> findByFactNo(String factNo);

	public WebYieldData findById(WebYieldDataId id);

	public void delete(WebYieldDataId id,KyzExpectmatmLog delLog);

}
