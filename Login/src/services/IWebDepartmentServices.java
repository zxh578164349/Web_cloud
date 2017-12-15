/**
 * 
 */
package services;

import java.util.List;

import entity.WebDepartment;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：IWebDepartmentServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/12/15 下午2:53:25   
 * 修改人：Administrator   
 * 修改时间：2017/12/15 下午2:53:25   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebDepartmentServices{
	public List<WebDepartment>findWebDepartmentByFactNo(String factNo);

}
