package services.impl;

import java.util.List;
import dao.IWebSubmenuDao;
import entity.WebSubmenu;
import services.IWebSubmenuService;

public class WebSubmenuServiceImpl implements IWebSubmenuService {

	private IWebSubmenuDao submenuDao;

	public IWebSubmenuDao getSubmenuDao() {
		return submenuDao;
	}

	public void setSubmenuDao(IWebSubmenuDao submenuDao) {
		this.submenuDao = submenuDao;
	}

	public WebSubmenu selById(String subName) {
		return submenuDao.selById(subName);
	}

	public List<WebSubmenu> selByjurId(int jurid) {
		return submenuDao.selByjurId(jurid);
	}

	public boolean delSubmenu(int jurid) {
		try {
			submenuDao.delSubmenu(jurid);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean addSubmenu(WebSubmenu submenu) {
		try {
			submenuDao.addSubmenu(submenu);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<WebSubmenu> findByFactno(String factNo) {
		// TODO Auto-generated method stub
		return submenuDao.findByFactno(factNo);
	}

}
