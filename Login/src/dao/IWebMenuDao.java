package dao;

import java.util.List;

import entity.WebMenu;
import entity.WebUser;

public interface IWebMenuDao {

	public List findAllMenu(String typeMk);
	public WebMenu findSubMenuById(Integer mid);
	public WebMenu selByname(String name);
	

}
