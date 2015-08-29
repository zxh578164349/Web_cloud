package services;

import util.PageBean;
import entity.WebuserEmail;

public interface IWebuserEmailServices {
	public String findEmailPWD(String factNo,String email);
	public void add(WebuserEmail email);
	public WebuserEmail findById(String factNo,String email,String emailpwd);
	public void delete(String factNo,String email,String emailpwd);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email);

}
