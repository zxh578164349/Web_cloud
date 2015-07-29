package services;

import java.util.List;

import entity.WebJurisdiction;

public interface IWebJurisdictionService {
	// 新增權限
	public boolean addJurisdiction(WebJurisdiction jurisdiction);

	// 刪除用戶的所有權限
	public int delByUserId(int userId);

	// 查看用戶用那些權限
	public List<WebJurisdiction> selSub(int userid);

	// 刪除權限
	public boolean delJur(WebJurisdiction jurisdiction);

	public boolean add(WebJurisdiction jurisdiction);

	public WebJurisdiction selBymenuName(String name, int id);
	public WebJurisdiction findById(int id);
}
