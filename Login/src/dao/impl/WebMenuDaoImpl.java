package dao.impl;

import java.util.List;
import org.hibernate.Query;
import dao.Basedao;
import dao.IWebMenuDao;
import entity.WebMenu;

public class WebMenuDaoImpl extends Basedao implements IWebMenuDao {

	public List findAllMenu() {
		// TODO Auto-generated method stub
		String hql = "from WebMenu  order by menuid";
		List<WebMenu>list=super.findAll(hql, null);
		for(WebMenu menu:list){
			menu.getSubmenus().size();
		}
		return list;
	}

	public WebMenu findSubMenuById(Integer mid) {
		// TODO Auto-generated method stub
		String hql = "from WebMenu w where w.menuid=?";
		Integer[] objs = { mid };
		List list = super.findAll(hql, objs);
		if (list.size() > 0) {
			return (WebMenu) list.get(0);
		}
		return null;
	}

	public WebMenu selByname(String name) {
		String hql = "from WebMenu where menuname=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		return (WebMenu) query.uniqueResult();
	}

}
