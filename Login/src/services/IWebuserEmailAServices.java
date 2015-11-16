package services;

import java.util.List;

import util.PageBean;
import entity.WebuserEmailA;

public interface IWebuserEmailAServices {
	public void add(WebuserEmailA email);
	public WebuserEmailA findById(String factNo,String email,String emailPwd,String visaSort);
	public void delete(String factNo,String email,String emailPwd,String visaSort);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email,String visaSort);
	public List<String> findByEmail(String factNo,String email,String visaSort);

}
