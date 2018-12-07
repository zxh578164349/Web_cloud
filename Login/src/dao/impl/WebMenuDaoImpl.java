package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import dao.Basedao;
import dao.IWebMenuDao;
import entity.WebMenu;
import entity.WebUser;

public class WebMenuDaoImpl extends Basedao implements IWebMenuDao {

	public List findAllMenu(String typeMk) {
		// TODO Auto-generated method stub
		//String hql = "from WebMenu order by menuid";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebMenu where 1=1 ");
		if(typeMk!=null&&!typeMk.equals("")){
			hql.append(" and typeMk=:typeMk ");
			map.put("typeMk",typeMk);
		}
		hql.append("and enableMk='Y' order by menuid");
		//hql.append(" order by menuid");
		List<WebMenu>list=super.getAllWithNoPage(hql.toString(),map);
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
