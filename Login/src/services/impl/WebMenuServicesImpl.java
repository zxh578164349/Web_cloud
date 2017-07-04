package services.impl;

import java.util.List;
import dao.IWebMenuDao;
import entity.WebMenu;
import services.IWebMenuServices;

public class WebMenuServicesImpl implements IWebMenuServices {
	private IWebMenuDao menuDao;

	public void setMenuDao(IWebMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public List findAllMenu(String typeMk) {
		// TODO Auto-generated method stub
		return menuDao.findAllMenu(typeMk);
	}

	public WebMenu findMenuById(Integer mid) {
		// TODO Auto-generated method stub
		return menuDao.findSubMenuById(mid);
	}

	public WebMenu selByname(String name) {
		return menuDao.selByname(name);
	}

}
