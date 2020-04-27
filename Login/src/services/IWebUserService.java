package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebOperationToUser;
import entity.WebUser;
import entity.WebUserOperation;

public interface IWebUserService {
	// �ھڥΤ�W��Τ��H
	public WebUser selbyName(String name);

	// �ק�Τ�H��
	public boolean updateUser(WebUser webUser);

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
	
	public PageBean findPageBean(int pageSize,int page,String userName,String factNo,String userType);
	
	public WebUser findByIdDWR(String factNo,String username);
	
	public void add(WebUser user);
	
	public List<WebUser> findByEmailDwr(String email);
	public List<WebUser> findByUserNameDwr(String factNo,String name);
	public WebUser findByNameAndFactNoDwr(String factNo,String name);
	public void delete(int id,KyzExpectmatmLog delLog);
	public WebUser findByIdDwr2(String factNo, String userName);
	public WebUser findUser(String username,String pwd,String factNo);
	
	public List<WebUser> findByEmailDwr2(String factNo,String email);
	public String findEmailPWD(String email);
	public WebUser findUserByFactNoAEmail(String factNo,String email);
	public List<WebUserOperation>findAllOperations();
	
	public void addWebOperations(List<WebOperationToUser>list);
	
	public List<WebOperationToUser> findoperations(Integer userid);
	public void delete_operation(List<WebOperationToUser>list);
	public List<WebUser>findByWeeklyMk();
	
}
