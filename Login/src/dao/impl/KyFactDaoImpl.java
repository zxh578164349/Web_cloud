package dao.impl;

import java.util.List;
import org.hibernate.Query;
import dao.*;
import entity.KyFact;

public class KyFactDaoImpl extends Basedao implements IKyFactDao {

	public List fidnAllKyFact() {
		// TODO Auto-generated method stub
		String hql = "from KyFact";
		return super.findAll(hql, null);
	}

	public String selByid(String id) {
		String hql = "from KyFact where factNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, id);
		KyFact fact = (KyFact) query.uniqueResult();
		if (fact != null) {
			return fact.getFactSname();
		}
		return null;

	}
}
