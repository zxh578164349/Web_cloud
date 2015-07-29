package dao.impl;

import java.util.List;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebEmailDao;
import entity.WebCc;
import entity.WebEmail;

public class WebEmailDaoImpl extends Basedao implements IWebEmailDao {

	public List<WebEmail> getEmail(String sendif) {
		String hql = "from WebEmail where sengif=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sendif);
		return query.list();
	}

	public List<WebCc> getCC(String sendif) {
		String hql = "from WebCc where sendif=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, sendif);
		return query.list();
	}

}
