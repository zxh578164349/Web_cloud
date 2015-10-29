package dao;

import java.util.List;

import entity.WebJurisdiction;

public interface IWebJurisdictionDao {
	// 新增權限
	public void addJurisdiction(WebJurisdiction Jurisdiction);

	// 根據用戶刪除權限
	public int delByUserId(int userId);

	// 返回用戶有哪些權限
	public List<WebJurisdiction> selbyid(int userid);

	// 刪除權限
	public void delJur(WebJurisdiction jurisdiction);

	// 為用戶分配權限
	public void add(WebJurisdiction jurisdiction);

	// 根據菜單名找對象
	public WebJurisdiction selBymenuName(String name, int userid);
	
	public WebJurisdiction findById(int id);
}
