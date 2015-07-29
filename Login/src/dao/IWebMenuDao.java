package dao;

import java.util.List;

import entity.WebMenu;

public interface IWebMenuDao {
	// 根據用戶查找菜單
	public List findAllMenu();

	// 查找菜單相應的子菜單
	public WebMenu findSubMenuById(Integer mid);

	// 根據菜單名找菜單對象
	public WebMenu selByname(String name);
}
