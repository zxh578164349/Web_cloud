package dao.impl;

import java.util.List;
import org.hibernate.Query;
import dao.Basedao;
import dao.IWebSubmenuDao;
import entity.WebSubmenu;

public class WebSubmenuDaoImpl extends Basedao implements IWebSubmenuDao {

	public WebSubmenu selById(String subName) {
		String hql = "from WebSubmenu where submenuname=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, subName);
		WebSubmenu webSubmenu = (WebSubmenu) query.uniqueResult();
		return webSubmenu;
	}

	public List<WebSubmenu> selByjurId(int jurid) {
		String hql = "from WebSubmenu where webJurisdiction.jurisdictionid=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, jurid);
		return query.list();
	}

	public void delSubmenu(int jurid) {
		List<WebSubmenu> list = selByjurId(jurid);
		for (int i = 0; i < list.size(); i++) {
			delete(list.get(i));
		}

	}

	public void addSubmenu(WebSubmenu submenu) {
		save(submenu);
	}

	public List<WebSubmenu> findByFactno(String factNo) {
		// TODO Auto-generated method stub
		String hql="from WebSubmenu where webJurisdiction.webUser.factno=? order by webJurisdiction.webUser.factno,webJurisdiction.webUser.username";
		String[]objs={factNo};
		return super.findAll(hql, objs);
	}

}
