package services;

import java.util.List;
import entity.WebMenu;

public interface IWebMenuServices {
	// 根據用戶查找相應的菜單
	public List findAllMenu();

	// 根據菜單查找對應的子菜單
	public WebMenu findMenuById(Integer mid);

	// 根據菜單名返回菜單對象
	public WebMenu selByname(String name);

}
