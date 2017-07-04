/**
 * 
 */
package dao;

import java.util.List;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebErpBrankProcessDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午1:02:56   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午1:02:56   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebErpBrankProcessDao{
	public List<Object[]> findObj(String sysCode);

}
