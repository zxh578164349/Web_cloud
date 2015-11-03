package services;

import java.util.List;

import util.PageBean;
import entity.WebUser;

public interface IWebUserService {
	// 根據用戶名找用戶對象
	public WebUser selbyName(String name);

	// 修改用戶信息
	public boolean updateUser(WebUser webUser);

	// 獲取所有用戶信息
	public List<WebUser> getUsers(int page, int rows, String conditions,
			String factNo);

	// 計算用戶有多少個
	public int totlePage(String conditions);

	public List findMoreUser(String uname);
	public int findMoreUser2(String uname);

	// 設置賬戶是否可用
	public boolean updateKy(int id, int available);

	// 根據id找到用戶
	public WebUser selByuserId(int id);

	// 根據id和廠別找用戶
	public WebUser selByuserId(String factno, String username);

	public WebUser selByuserId(int id, String username);

	public List<WebUser> findSameUser(WebUser user);
	
	public PageBean findPageBean(int pageSize,int page,String userName,String factNo);
	
	public WebUser findByIdDWR(String factNo,String username);
	
	public void add(WebUser user);
	
	public List<WebUser> findByEmailDwr(String email);
	public List<WebUser> findByUserNameDwr(String factNo,String name);
	public WebUser findByNameAndFactNoDwr(String factNo,String name);
	public void delete(int id);
	public WebUser findByIdDwr2(String factNo, String userName);
	public PageBean findPageBean_init(int pageSize, int page, String userName,String factNo);
	public WebUser findUser(String username,String pwd,String factNo);
	
	public List<WebUser> findByEmailDwr2(String factNo,String email);
	public String findEmailPWD(String email);
	public WebUser findUserByFactNoAEmail(String factNo,String email);
	
}
