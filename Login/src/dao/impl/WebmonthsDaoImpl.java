package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebmonthsDao;
import entity.WebMonths;

public class WebmonthsDaoImpl extends Basedao implements IWebmonthsDao{
	
	public void addWebmonths(WebMonths obj) {
		// TODO Auto-generated method stub
		try{
			
			WebMonths obj2=findWebmonths(obj.getId().getYymm(),obj.getId().getObjType());
			if(obj2!=null){
				super.delete(obj2);	
			}			
			super.merge(obj);
		}catch(Exception e){
			e.printStackTrace();
			getSession().getTransaction().rollback();
			getSession().close();
		}
		
	}

	public WebMonths findWebmonths(String yymm, String objType) {
		// TODO Auto-generated method stub
		String hql="from WebMonths where id.yymm=? and id.objType=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, yymm);
		query.setString(1, objType);
		WebMonths obj=(WebMonths)query.uniqueResult();
		return obj;
	}

}
