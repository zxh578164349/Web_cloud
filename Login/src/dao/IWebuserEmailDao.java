package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebuserEmail;

public interface IWebuserEmailDao {

	public void add(WebuserEmail email);
	public WebuserEmail findById(String factNo,String email,String emailpwd,String typeMk);
	public void delete(String factNo,String email,String emailpwd,String typeMk,KyzExpectmatmLog delLog);
	public PageBean findPageBean(int pageSize,int page,String factNo,String email);
	public List<WebuserEmail> findByFactNoAEmailPwd(String factNo,String email);
	/**
	 * 同步簽核人
	 * @Title: findByFactNoAEmailPwd2
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param email
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2017/1/12
	 */
	public List<String> findByFactNoAEmailPwd2(String factNo,String email);
	
	/**
	 * 同步知會人
	 * @Title: findByFactNoAEmailPwd2
	 * @Description: TODO
	 * @param @param factNo
	 * @param @param email
	 * @param @return
	 * @return List<String>
	 * @throws
	 * @author web
	 * @date 2017/1/12
	 */
	public List<String> findByFactNoAEmailPwd3(String factNo,String email);

}
