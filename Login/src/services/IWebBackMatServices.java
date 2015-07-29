package services;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebBackMatServices {
	// 新增
	public void addBackMat(WebBackmat backmat);

	// 根據條件查詢數據
	public List<WebBackmat> selBackmats(String factNo, String yymm, int page,
			int rows);

	// 查詢全部數據的總頁數
	public int totlePage(String factNo, String yymm);

	// 刪除單條數據
	public boolean deleteBackMat(String billNo);

	public WebBackmat findBackById(WebBackmatId id);

	public void delete(WebBackmatId id);

	public List<WebBackmat> selByYymm(String yymm, String factNo);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);
}
