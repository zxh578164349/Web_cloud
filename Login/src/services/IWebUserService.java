package services;

import java.util.List;

import util.PageBean;
import entity.WebUser;

public interface IWebUserService {
	// �ھڥΤ�W��Τ��H
	public WebUser selbyName(String name);

	// �ק�Τ�H��
	public boolean updateUser(WebUser webUser);

	// ����Ҧ��Τ�H��
	public List<WebUser> getUsers(int page, int rows, String conditions,
			String factNo);

	// �p��Τᦳ�h�֭�
	public int totlePage(String conditions);

	public List findMoreUser(String uname);
	public int findMoreUser2(String uname);

	// �]�m���O�_�i��
	public boolean updateKy(int id, int available);

	// �ھ�id���Τ�
	public WebUser selByuserId(int id);

	// �ھ�id�M�t�O��Τ�
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
