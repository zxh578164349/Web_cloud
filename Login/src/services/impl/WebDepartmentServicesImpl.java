/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebDepartmentDao;

import entity.WebDepartment;
import services.IWebDepartmentServices;
import util.PageBean;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebDepartmentServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/12/15 下午2:54:35   
 * 修改人：Administrator   
 * 修改时间：2017/12/15 下午2:54:35   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebDepartmentServicesImpl implements IWebDepartmentServices{

	/**
	 * 日期:2017/12/15
	 * 描述:
	 */
	private IWebDepartmentDao webdepDao;
	
	public void setWebdepDao(IWebDepartmentDao webdepDao){
		this.webdepDao=webdepDao;
	}

	public List<WebDepartment> findWebDepartmentByFactNo(String factNo){
		// TODO Auto-generated method stub
		return webdepDao.findWebDepartmentByFactNo(factNo);
	}

	public PageBean findPageBean(int page, int pageSize, String factNo) {
		// TODO Auto-generated method stub
		return webdepDao.findPageBean(page, pageSize, factNo);
	}

	public WebDepartment findById(String depId) {
		// TODO Auto-generated method stub
		return webdepDao.findById(depId);
	}

	public void add(WebDepartment dep) {
		// TODO Auto-generated method stub
		webdepDao.add(dep);
	}

}
