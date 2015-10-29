package dao;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebYielePredictionDao {
	public List<WebYielePrediction> findByFactcode(String factno,
			String factcode, String yymm);

	public void addYPre(WebYielePrediction pre);

	// 沮兵ン琩高计沮
	public List<WebYielePrediction> selectYPre(String factNo, String yymm,
			int page, int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebYielePrediction findById(WebYielePredictionId id);

	public PageBean findYpreWithPage(int pageSize, int page, String factNo,
			String yymm);

	public void delete(WebYielePredictionId id);

	public List<WebYielePrediction> findAByFactNo(String factNo);

	public List<WebYielePrediction> getAllWithNoPage(String factNo, String yymm);

	public List<WebYielePrediction> findNullYpre(String factno,
			String factcode, String yymm);

	public List findNullYpre(String yymm);

}
