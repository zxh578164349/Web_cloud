package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebuserEmailA;

public interface IWebuserEmailADao {
	public void add(WebuserEmailA email);
	public WebuserEmailA findById(String factNo,String email,String emailPwd,String visaSort);
	public void delete(String factNo,String email,String emailPwd,String visaSort,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email,String visaSort);
	public List<String> findByEmail(String factNo,String email,String visaSort);

}
