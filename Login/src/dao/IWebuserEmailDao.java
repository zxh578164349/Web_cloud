package dao;

import java.util.List;

import util.PageBean;
import entity.WebuserEmail;

public interface IWebuserEmailDao {

	public void add(WebuserEmail email);
	public WebuserEmail findById(String factNo,String email,String emailpwd);
	public void delete(String factNo,String email,String emailpwd);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email);
	public List<WebuserEmail> findByFactNoAEmailPwd(String factNo,String email);
	public List<String> findByFactNoAEmailPwd2(String factNo,String email);

}
