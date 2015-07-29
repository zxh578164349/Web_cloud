package dao;

import java.util.List;

import util.PageBean;
import entity.WebUser;

public interface IWebUserDao {
	// 根據用戶名查找用戶對象
	public WebUser selByName(String name);

	// 修改用戶信息
	public void updates(WebUser webUser);

	// 獲取所有用戶信息
	public List<WebUser> getUsers(int page, int rows, String conditions,
			String fact);

	// 計算集合數量
	public int totlePage(String conditions);

	/**
	 * 相同名字的用戶(用於獲取一個用戶擁有多少個廠別)
	 */
	public List findMoreUser(String uname);

	// 設置賬戶是否可用
	public void updateKy(int id, int available);

	// 根據id找到用戶
	public WebUser selByuserId(int id);

	// 廠別
	public WebUser selByuserId(String factno, String username);

	public WebUser selByuserId(int id, String fact);

	public List<WebUser> findSameUser(WebUser webUser);
	
	
	public PageBean findPageBean(int pageSize,int page,String userName,String factNo);
	
	public void add(WebUser user);
	
	public List<WebUser> findByEmailDwr(String email);
	public List<WebUser> findByUserNameDwr(String factNo,String name);
	public WebUser findByNameAndFactNoDwr(String factNo,String name);
	public void delete(int id);
	public WebUser findByIdDwr2(String factNo,String userName);
	public PageBean findPageBean_init(int pageSize, int page, String userName,String factNo);
	
	public WebUser findUser(String username,String pwd,String factNo);
	
			
	
}
