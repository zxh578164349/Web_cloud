package dao.impl;

import java.util.List;
import org.hibernate.Query;
import dao.Basedao;
import dao.IWebJurisdictionDao;
import entity.WebJurisdiction;

public class WebJurisdictionDaoImpl extends Basedao implements
		IWebJurisdictionDao {

	public void addJurisdiction(WebJurisdiction Jurisdiction) {
		save(Jurisdiction);
	}

	public int delByUserId(int userId) {
		String hql = "delete WebJurisdiction j where j.webUser.id=:userid";
		Query query = getSession().createQuery(hql);
		query.setParameter("userid", userId);
		int i = query.executeUpdate();
		System.out.println(i);
		return i;
	}

	public List<WebJurisdiction> selbyid(int userid) {
		String hql = "from WebJurisdiction where webUser.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, userid);
		return query.list();
	}

	public void delJur(WebJurisdiction jurisdiction) {
		delete(jurisdiction);
	}

	public void add(WebJurisdiction jurisdiction) {
		save(jurisdiction);
	}

	public WebJurisdiction selBymenuName(String name, int userid) {
		String hql = "from WebJurisdiction where webMenu.menuname=? and webUser.id=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		query.setInteger(1, userid);
		return (WebJurisdiction) query.uniqueResult();
	}

	public WebJurisdiction findById(int id) {
		// TODO Auto-generated method stub
		WebJurisdiction jur=(WebJurisdiction)super.findById(id, WebJurisdiction.class);
		return jur;
	}

}
