package dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Transaction;
//import org.hibernate.classic.Session;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.opensymphony.xwork2.ActionContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import entity.KyzExpectmatmLog;
import entity.WebJurisdiction;

/**
 * A data access object (DAO) providing persistence and search support for
 * WebJurisdiction entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see entity.WebJurisdiction
 * @author MyEclipse Persistence Tools
 */

public class Basedao extends HibernateDaoSupport {
	private static final Log log = LogFactory.getLog(Basedao.class);
	private static final int PAGENUMS=20;

	// property constants
	protected void initDao() {
		// do nothing
	}

	public void save(Object transientInstance) {
		log.debug("saving WebJurisdiction instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Object persistentInstance,KyzExpectmatmLog delLog) {
		log.debug("deleting WebJurisdiction instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			getHibernateTemplate().merge(delLog);//數據刪除記錄
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	public void delete(Object persistentInstance) {
		log.debug("deleting WebJurisdiction instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Object findById(java.lang.Integer id, Class clas) {
		log.debug("getting WebJurisdiction instance with id: " + id);
		try {
			Object instance = (Object) getHibernateTemplate().get(clas, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	public Object findById_long(java.lang.Long id, Class clas) {
		log.debug("getting WebJurisdiction instance with id: " + id);
		try {
			Object instance = (Object) getHibernateTemplate().get(clas, id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findAll(String hql, Object[] object) {
		log.debug("finding all WebJurisdiction instances");
		try {
			return getHibernateTemplate().find(hql, object);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Object merge(Object detachedInstance) {
		log.debug("merging WebJurisdiction instance");
		try {
			Object result = (Object) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}
	public void update(Object obj){
		try{
			getHibernateTemplate().update(obj);
		}catch(RuntimeException re){
			log.error("update failed", re);
			throw re;
		}
	}

	public void attachDirty(Object instance) {
		log.debug("attaching dirty WebJurisdiction instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Object instance) {
		log.debug("attaching clean WebJurisdiction instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static Basedao getFromApplicationContext(ApplicationContext ctx) {
		return (Basedao) ctx.getBean("WebJurisdictionDAO");
	}


	public List queryForPage(final String hql, final int offset,
			final int length, final Map<String, Object> map) {
		    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
								map.get(key).getClass().getName().equals("java.util.ArrayList")){
							//System.out.println(map.get(key).getClass().getName());
							query.setParameterList(key,(List<String>)map.get(key));
							
						}else{
							query.setParameter(key, map.get(key));
						}
						
					}
				}
				query.setFirstResult(offset);
				query.setMaxResults(PAGENUMS);
				List list = query.list();
				return list;
			}
		});
		return list;
	}

	
	public int getAllRowCount(final String hql, final Map<String, Object> map) {
		    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
								map.get(key).getClass().getName().equals("java.util.ArrayList")){
							query.setParameterList(key, (List<String>)map.get(key));
						}else{
							query.setParameter(key, map.get(key));
						}					
					}
				}
				List list = query.list();
				return list;
			}
		});
		return list.size();
	}
	public Integer getAllRowCount2(final String hql, final Map<String, Object> map) {	    		
			Query query =getSession().createQuery(hql);
			if (map != null && !map.isEmpty()) {
				for (String key : map.keySet()) {
					if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
							map.get(key).getClass().getName().equals("java.util.ArrayList")){
						query.setParameterList(key, (List<String>)map.get(key));
					}else{
						query.setParameter(key, map.get(key));
					}
					
				}
			}
			String result =(String)query.uniqueResult().toString();			
			return Integer.valueOf(result);
			
}
	public long getRowNums(String hql,Object[]objs){
		Query query=getSession().createQuery(hql);
		if(objs!=null){
			for(int i=0;i<objs.length;i++){
				query.setParameter(i, objs[i]);
			}
		}
		return (Long)query.uniqueResult();
	}

	


	public List getAllWithNoPage(final String hql, final Map<String, Object> map) {
		    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				if (map != null && !map.isEmpty()) {
					for (String key : map.keySet()) {
						if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
								map.get(key).getClass().getName().equals("java.util.ArrayList")	){
							//System.out.println(map.get(key).getClass().getName());
							query.setParameterList(key, (List<String>)map.get(key));
						}else{
							query.setParameter(key, map.get(key));
						}						
					}
				}
				List list = query.list();
				return list;
			}
		});
		return list;
	}
	
	public List getAllWithNoPage_sql(final String hql, final Map<String, Object> map) {
	    List list = getHibernateTemplate().executeFind(new HibernateCallback() {
		public Object doInHibernate(Session session)
				throws HibernateException, SQLException {
			Query query = session.createSQLQuery(hql);
			if (map != null && !map.isEmpty()) {
				for (String key : map.keySet()) {
					if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
							map.get(key).getClass().getName().equals("java.util.ArrayList")	){
						//System.out.println(map.get(key).getClass().getName());
						query.setParameterList(key, (List<String>)map.get(key));
					}else{
						query.setParameter(key, map.get(key));
					}						
				}
			}
			List list = query.list();
			return list;
		}
	});
	return list;
}
	
	public void deleteList(List list){
		try{
			getHibernateTemplate().deleteAll(list);	
		}catch(RuntimeException re){
			log.error("deleteList Fail",re);
			throw re;
		}
		
	}
	
	public void addList(List list){
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<list.size();i++){
				getSession().merge(list.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
				}
			}
		}catch(Exception e){
			tx.rollback();
			//e.printStackTrace();
			System.out.println("***************************************************8"+e);
		}
	}

}