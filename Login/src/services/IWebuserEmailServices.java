package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebuserEmail;

public interface IWebuserEmailServices {

	public void add(WebuserEmail email);
	public WebuserEmail findById(String factNo,String email,String emailpwd);
	public void delete(String factNo,String email,String emailpwd,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email);
	public List<WebuserEmail> findByFactNoAEmailPwd(String factNo,String email);
	public List<String> findByFactNoAEmailPwd2(String factNo, String email);

}
