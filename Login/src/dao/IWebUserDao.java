package dao;

import java.util.List;

import util.PageBean;
import entity.WebUser;

public interface IWebUserDao {
	// �ھڥΤ�W�d��Τ��H
	public WebUser selByName(String name);

	// �ק�Τ�H��
	public void updates(WebUser webUser);

	// ����Ҧ��Τ�H��
	public List<WebUser> getUsers(int page, int rows, String conditions,
			String fact);

	// �p�ⶰ�X�ƶq
	public int totlePage(String conditions);

	/**
	 * �ۦP�W�r���Τ�(�Ω�����@�ӥΤ�֦��h�֭Ӽt�O)
	 */
	public List findMoreUser(String uname);

	// �]�m���O�_�i��
	public void updateKy(int id, int available);

	// �ھ�id���Τ�
	public WebUser selByuserId(int id);

	// �t�O
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
