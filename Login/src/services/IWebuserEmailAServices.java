package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebuserEmailA;

public interface IWebuserEmailAServices {
	public void add(WebuserEmailA email);
	public WebuserEmailA findById(String factNo,String email,String emailPwd,String visaSort);
	public boolean deleteObj(String factNo,String email,String emailPwd,String visaSort,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email,String visaSort);
	public List<String> findByEmail(String factNo,String email,String visaSort);

}
