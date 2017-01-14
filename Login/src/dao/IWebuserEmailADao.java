package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebuserEmailA;

public interface IWebuserEmailADao {
	public void add(WebuserEmailA email);
	public WebuserEmailA findById(String factNo,String email,String emailPwd,String visaSort,String typeMk);
	public void delete(String factNo,String email,String emailPwd,String visaSort,String typeMk,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email,String visaSort,String typeMk);
	
	/**
	 * 同步簽核人
	 * @Title: findByEmail
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param email
	 * @param @param visaSort
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2017/1/12
	 */
	public List<String> findByEmail(String factNo,String email,String visaSort);
	
	/**
	 * 同步知會人
	 * @Title: findByEmail2
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param email
	 * @param @param visaSort
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2017/1/12
	 */
	public List<String> findByEmail2(String factNo,String email,String visaSort);

}
