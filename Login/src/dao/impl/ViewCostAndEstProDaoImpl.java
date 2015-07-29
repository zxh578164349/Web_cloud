package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IViewCostAndEstProDao;

public class ViewCostAndEstProDaoImpl extends Basedao implements
		IViewCostAndEstProDao {

	public List findAll() {
		// TODO Auto-generated method stub
		String hql = "from ViewCostandestpro";
		return super.findAll(hql, null);
	}

}
