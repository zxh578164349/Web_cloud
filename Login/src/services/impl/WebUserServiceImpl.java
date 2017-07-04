package services.impl;

import java.util.List;
import services.IWebUserService;
import util.PageBean;
import dao.IWebUserDao;
import entity.KyzExpectmatmLog;
import entity.WebOperationToUser;
import entity.WebUser;
import entity.WebUserOperation;

public class WebUserServiceImpl implements IWebUserService {

	private IWebUserDao webUserDao;

	public IWebUserDao getWebUserDao() {
		return webUserDao;
	}

	public void setWebUserDao(IWebUserDao webUserDao) {
		this.webUserDao = webUserDao;
	}

	public WebUser selbyName(String name) {
		WebUser user = webUserDao.selByName(name);
		return user;
	}

	public boolean updateUser(WebUser webUser) {
		try {
			webUserDao.updates(webUser);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public List<WebUser> getUsers(int page, int rows, String conditions,
			String fact) {
		return webUserDao.getUsers(page, rows, conditions, fact);
	}

	public int totlePage(String conditions) {
		return webUserDao.totlePage(conditions);
	}

	public List findMoreUser(String uname) {
		// TODO Auto-generated method stub
		return webUserDao.findMoreUser(uname);
	}
	public int findMoreUser2(String uname) {
		// TODO Auto-generated method stub
		return webUserDao.findMoreUser2(uname);
	}

	public boolean updateKy(int id, int available) {
		try {
			webUserDao.updateKy(id, available);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public WebUser selByuserId(int id) {
		return webUserDao.selByuserId(id);
	}

	public WebUser selByuserId(String factno, String username) {
		return webUserDao.selByuserId(factno, username);
	}

	public WebUser selByuserId(int id, String username) {
		return webUserDao.selByuserId(id, username);
	}

	public List<WebUser> findSameUser(WebUser user) {
		// TODO Auto-generated method stub
		return webUserDao.findSameUser(user);
	}

	public PageBean findPageBean(int pageSize, int page, String userName,String factNo,String userType) {
		// TODO Auto-generated method stub
		return webUserDao.findPageBean(pageSize, page, userName, factNo,userType);
	}

	public WebUser findByIdDWR(String factNo, String username) {
		// TODO Auto-generated method stub
		return webUserDao.selByuserId(factNo, username);
	}

	public void add(WebUser user) {
		// TODO Auto-generated method stub
		webUserDao.add(user);
	}

	public List<WebUser> findByEmailDwr(String email) {
		// TODO Auto-generated method stub
		return webUserDao.findByEmailDwr(email);
	}

	public List<WebUser> findByUserNameDwr(String factNo,String name) {
		// TODO Auto-generated method stub
		List<WebUser> list=webUserDao.findByUserNameDwr(factNo,name);		
		return list;
	}

	public WebUser findByNameAndFactNoDwr(String factNo, String name) {
		// TODO Auto-generated method stub
		return webUserDao.findByNameAndFactNoDwr(factNo, name);
	}

	public void delete(int id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		webUserDao.delete(id,delLog);
	}

	public WebUser findByIdDwr2(String factNo, String userName) {
		// TODO Auto-generated method stub
		return webUserDao.findByIdDwr2(factNo, userName);
	}
	

	public WebUser findUser(String username, String pwd, String factNo) {
		// TODO Auto-generated method stub
		return webUserDao.findUser(username, pwd, factNo);
	}

	public List<WebUser> findByEmailDwr2(String factNo, String email) {
		// TODO Auto-generated method stub
		return webUserDao.findByEmailDwr2(factNo, email);
	}

	public String findEmailPWD( String email) {
		// TODO Auto-generated method stub
		return webUserDao.findEmailPWD(email);
	}

	public WebUser findUserByFactNoAEmail(String factNo, String email) {
		// TODO Auto-generated method stub
		return webUserDao.findUserByFactNoAEmail(factNo, email);
	}

	/**
	 * 日期:2016/12/30
	 * 描述:
	 */
	
	
	public List<WebUserOperation> findAllOperations(){
		// TODO Auto-generated method stub
		return webUserDao.findAllOperations();
	}

	/**
	 * 日期:2017/1/2
	 * 描述:
	 */
	
	
	public void addWebOperations(List<WebOperationToUser> list){
		// TODO Auto-generated method stub
		webUserDao.addWebOperations(list);
	}

	/**
	 * 日期:2017/1/3
	 * 描述:
	 */
	
	
	public List<WebOperationToUser> findoperations(Integer userid){
		// TODO Auto-generated method stub
		return webUserDao.findoperations(userid);
	}

	/**
	 * 日期:2017/1/3
	 * 描述:
	 */
	
	
	public void delete_operation(List<WebOperationToUser>list){
		// TODO Auto-generated method stub
		webUserDao.delete_operation(list);
	}


}
