/**
 * 
 */
package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebDepartmentDao;
import entity.WebDepartment;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebDepartmentDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/12/15 下午2:47:44   
 * 修改人：Administrator   
 * 修改时间：2017/12/15 下午2:47:44   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebDepartmentDaoImpl extends Basedao implements IWebDepartmentDao{

	/**
	 * 日期:2017/12/15
	 * 描述:
	 */
	
	
	public List<WebDepartment> findWebDepartmentByFactNo(String factNo){
		// TODO Auto-generated method stub
		String hql="from WebDepartment where factNo=?";
		String[]objs={factNo};
		List<WebDepartment>list=super.findAll(hql,objs);
		return list;
	}

}
