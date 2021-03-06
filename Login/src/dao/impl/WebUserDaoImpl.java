package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Transaction;

import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import dao.Basedao;
import dao.IWebUserDao;
import entity.KyzExpectmatmLog;
import entity.WebOperationToUser;
import entity.WebUser;
import entity.WebUserOperation;

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
		/*WebUser wUser = (WebUser) findById(webUser.getId(), WebUser.class);
		wUser.setAvailable(webUser.getAvailable());
		wUser.setFactno(webUser.getFactno());
		wUser.setIp(webUser.getIp());
		wUser.setName(webUser.getName());
		wUser.setPwd(webUser.getPwd());
		wUser.setUsername(webUser.getUsername());
		//wUser.setAvailable(webUser.getAvailable());
		wUser.setUserread(webUser.getUserread());
		wUser.setEmail(webUser.getEmail());
		wUser.setDepartment(webUser.getDepartment());
		getSession().update(wUser);*/
		super.merge(webUser);
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
			if (user.getName().equals("管理員") && user.getFactno().equals("tw")) {
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
	public int findMoreUser2(String uname) {
		// TODO Auto-generated method stub
		String hql = "select count(username) from WebUser where lower(username)=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, uname.toLowerCase());
		int result=(Integer)query.uniqueResult();
		return result;
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
			//user.getWebJurisdictions().size();	//解決hibernate延遲問題	
			for(int i=0;i<user.getWebJurisdictions().size();i++){
				user.getWebJurisdictions().get(i).getWebMenu().getMenuname();
				user.getWebJurisdictions().get(i).getWebSubmenus().size();
			}
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
			//獲取各項目的權限
			for(int i=0;i<user.getWebJurisdictions().size();i++){
				user.getWebJurisdictions().get(i).getWebMenu().getMenuname();
				user.getWebJurisdictions().get(i).getWebSubmenus().size();				
			}
			//獲取操作的權限
			for(WebOperationToUser obj:user.getWebOperationToUsers()){
				obj.getWebUserOperation().getId();
				obj.getWebUserOperation().getOperationCname();
			}
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

	public PageBean findPageBean(int pageSize, int page, String userName,String factNo,String userType) {
		// TODO Auto-generated method stub
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		String factno_session=user.getFactno();
		if(factNo==null||"".equals(factNo)){
			if(!"tw".equals(factno_session)){
				factNo=factno_session;
			}						
		}
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		final Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		hql.append("from WebUser where 1=1");
		hql2.append("select count(id) ");
		if(userName!=null&&!userName.equals("")){
			hql.append(" and (lower(username) like :username or lower(name) like :username)");			
			map.put("username", "%"+userName.toLowerCase()+"%");
		}
		if(factNo!=null&&!"".equals(factNo)){
			hql.append(" and factno=:factNo");
			map.put("factNo", factNo);
		}
		if(userType!=null&&!"".equals(userType)){
			hql.append(" and userType=:userType");//查看使用者用戶還是訪客用戶（0：使用者    1：訪客）
			map.put("userType",userType);
		}		
		hql2.append(hql);
		hql.append(" order by id");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
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
	
	public List<WebUser> findByEmailDwr2(String factNo,String email) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and  email like ?";
		String[]objs={factNo,email+"%"};
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

	public void delete(int id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebUser user=this.selByuserId(id);
		super.delete(user,delLog);
		
	}

	public WebUser findByIdDwr2(String factNo, String userName) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and lower(username)=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, userName.toLowerCase());
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

	public String findEmailPWD( String email) {
		// TODO Auto-generated method stub
		String hql="select emailpassword from WebUser where email=? and emailpassword is not null";
		Query query=getSession().createQuery(hql);
		query.setString(0, email);
		String emailPwd=(String)query.uniqueResult();
		return emailPwd;
	}

	public WebUser findUserByFactNoAEmail(String factNo, String email) {
		// TODO Auto-generated method stub
		String hql="from WebUser where factno=? and lower(email)=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, email.toLowerCase());
		WebUser user=(WebUser)query.uniqueResult();
		return user;
	}

	/**
	 * 日期:2016/12/30
	 * 描述:
	 */
	
	
	public List<WebUserOperation> findAllOperations(){
		// TODO Auto-generated method stub
		String hql="select id,operationCname from WebUserOperation";
		return super.findAll(hql,null);
	}

	/**
	 * 日期:2017/1/2
	 * 描述:
	 */
	
	
	public void addWebOperations(List<WebOperationToUser> list){
		// TODO Auto-generated method stub
		Transaction tc=null;
		try{
			tc=getSession().beginTransaction();
			for(WebOperationToUser obj:list){
				//getSession().save(obj);
				getSession().merge(obj);
			}
		}catch(Exception e){
			tc.rollback();
			e.printStackTrace();
		}
	}

	/**
	 * 日期:2017/1/3
	 * 描述:
	 */
	
	
	public List<WebOperationToUser> findoperations(Integer userid){
		// TODO Auto-generated method stub
		String hql="from WebOperationToUser where webUser.id=?";
		Integer[]objs={userid};
		List<WebOperationToUser>list=super.findAll(hql,objs);
		return list;
	}

	/**
	 * 日期:2017/1/3
	 * 描述:
	 */
	
	
	public void delete_operation(List<WebOperationToUser>list){
		// TODO Auto-generated method stub
		Transaction tc=null;
		try{	
			tc=getSession().beginTransaction();
			for(WebOperationToUser obj:list){
				getSession().delete(obj);
			}
		}catch(Exception e){
			tc.rollback();
			e.printStackTrace();
		}
		
		
	}

	public List<WebUser> findByWeeklyMk() {
		// TODO Auto-generated method stub
		String hql="from WebUser where weeklyreportMk='Y'";
		List<WebUser>list=super.findAll(hql, null);
		return list;
	}


}
