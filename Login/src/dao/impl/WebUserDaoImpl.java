package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import dao.Basedao;
import dao.IWebUserDao;
import entity.WebBackmat;
import entity.WebUser;

public class WebUserDaoImpl extends Basedao implements IWebUserDao {
	private int size;

	public WebUser selByName(String name) {
		String hql = "from WebUser where username = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		// WebUser webUser = (WebUser) query.uniqueResult();
		List<WebUser> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public void updates(WebUser webUser) {
		WebUser wUser = (WebUser) findById(webUser.getId(), WebUser.class);
		wUser.setAvailable(webUser.getAvailable());
		wUser.setFactno(webUser.getFactno());
		wUser.setIp(webUser.getIp());
		wUser.setName(webUser.getName());
		wUser.setPwd(webUser.getPwd());
		wUser.setUsername(webUser.getUsername());
		wUser.setAvailable(webUser.getAvailable());
		getSession().update(wUser);
	}

	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public List<WebUser> getUsers(int page, int rows, String conditions,
			String factno) {
		String hql = null;
		Query query = null;
		WebUser user = (WebUser) ActionContext.getContext().getSession()
				.get("loginUser");
		if (conditions != null && !conditions.equals("")) {
			if (user.getName().equals("¹ÜÀí†T") && user.getFactno().equals("tw")) {
				for (int i = conditions.length(); --i >= 0;) {
					if (Character.isDigit(conditions.charAt(i))
							|| Character.isLetter(conditions.charAt(i))) {
						hql = "from WebUser where workno=? ";
					}
					if (isChinese(conditions.charAt(i))) {
						hql = "from WebUser where name =? ";
					}
				}
				query = getSession().createQuery(hql);
				query.setString(0, conditions);
				size = query.list().size();
			} else {
				for (int i = conditions.length(); --i >= 0;) {
					if (Character.isDigit(conditions.charAt(i))
							|| Character.isLetter(conditions.charAt(i))) {
						hql = "from WebUser where workno=? and username <> ?";
					}
					if (isChinese(conditions.charAt(i))) {
						hql = "from WebUser where name =? and username <> ?";
					}
				}
				query = getSession().createQuery(hql);
				query.setString(0, conditions);
				query.setString(1, "admin");
				size = query.list().size();
			}
		} else {
			if (factno.equals("tw")) {
				hql = "from WebUser";
				query = getSession().createQuery(hql);
				size = query.list().size();
			} else {
				hql = "from WebUser where factno=? and username <> ?";
				query = getSession().createQuery(hql);
				query.setString(0, factno);
				query.setString(1, "admin");
				size = query.list().size();
			}
		}
		query.setFirstResult((page - 1) * rows);
		query.setMaxResults(rows);
		conditions = null;
		List<WebUser> list = query.list();
		return list;

	}

	public int totlePage(String conditions) {
		return size;
	}

	public List findMoreUser(String uname) {
		// TODO Auto-generated method stub
		String hql = "from WebUser w where lower(w.username)=?";
		String[] objs = { uname.toLowerCase() };
		List<WebUser> list = super.findAll(hql, objs);
		return list;
	}

	public void updateKy(int id, int available) {
		WebUser user = (WebUser) findById(id, WebUser.class);
		user.setAvailable(available);
		save(user);
	}

	public WebUser selByuserId(int id) {
		WebUser user = (WebUser) findById(id, WebUser.class);
		return user;
	}

	public WebUser selByuserId(String factno, String username) {
		String hql = "from WebUser where factno =? and lower(username)=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, factno);
		query.setString(1, username.toLowerCase());
		WebUser user = (WebUser) query.uniqueResult();
		if(user!=null){
			user.getWebJurisdictions().size();	//Àò¨ú¤l¶°ªºªø«×,¸Ñ¨Mhibernate©µ¿ðªº°ÝÃD	
		}			
		return user;
	}

	public WebUser selByuserId(int id, String fact) {
		String hql = "from WebUser where id=? and factno=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		query.setString(1, fact);
		WebUser user=(WebUser)query.uniqueResult();
		if(user!=null){
			user.getWebJurisdictions().size();//Àò¨ú¤l¶°ªºªø«×¡A¸Ñ¨Mhibernate©µ¿ðªº°ÝÃD
		}		
		return user;
	}

	public List<WebUser> findSameUser(WebUser webUser) {
		// TODO Auto-generated method stub
		String hql = "from WebUser w where w.username=? and w.pwd=?";
		String[] objs = { webUser.getUsername().trim(), webUser.getPwd().trim() };
		List<WebUser> users = super.findAll(hql, objs);
		return users;
	}

	public PageBean findPageBean(int pageSize, int page, String userName,
			String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		final Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from WebUser where 1=1");
		if(userName!=null&&!userName.equals("")){
			hql.append(" and (lower(username) like :username or lower(name) like :username)");			
			map.put("username", "%"+userName.toLowerCase()+"%");
		}
		if(factNo!=null&&!factNo.equals("")){
			hql.append(" and factno=:factNo");
			map.put("factNo", factNo);
		}
		
		hql.append(" order by id");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount(hql.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		final int offset=PageBean.countOffset(pageSize, currentPage);
		final int length=pageSize;
		List<WebUser> list=super.queryForPage(hql.toString(), offset, length, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}
	public PageBean findPageBean_init(int pageSize, int page, String userName,
			String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		final Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from WebUser where 1=1 ");
		if(userName!=null&&!userName.equals("")){
			hql.append(" and (username like :username or name like :username)");			
			map.put("username", "%"+userName+"%");
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and factno=:factNo");
			map.put("factNo", factNo);
		}
		
		hql.append(" order by id");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount(hql.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		final int offset=PageBean.countOffset(pageSize, currentPage);
		final int length=pageSize;
		List<WebUser> list=super.queryForPage(hql.toString(), offset, length, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public void add(WebUser user) {
		// TODO Auto-generated method stub
		super.merge(user);
	}

	public List<WebUser> findByEmailDwr(String email) {
		// TODO Auto-generated method stub
		String hql="from WebUser where email like ?";
		String[]objs={email+"%"};
		return super.findAll(hql, objs);
	}

	public List<WebUser> findByUserNameDwr(String factNo,String name) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and  name like ?";
		String[]objs={factNo,name+"%"};
		return super.findAll(hql, objs);
	}

	public WebUser findByNameAndFactNoDwr(String factNo, String name) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and name=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, name);
		return (WebUser)query.uniqueResult();
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		WebUser user=this.selByuserId(id);
		super.delete(user);
		
	}

	public WebUser findByIdDwr2(String factNo, String userName) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and username=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, userName);
		WebUser user=(WebUser)query.uniqueResult();
		return user;
	}

	public WebUser findUser(String username, String pwd, String factNo) {
		// TODO Auto-generated method stub
		String hql="from WebUser where username=? and pwd=? and factno=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, username);
		query.setString(1, pwd);
		query.setString(2, factNo);
		WebUser user=(WebUser)query.uniqueResult();
		return user;
	}


}
