/**
 * 
 */
package dao;

import entity.VKpifactNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVKpifactNewDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:36:24   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:36:24   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVKpifactNewDao {
	public VKpifactNew findById(String factNo,String factCode,String yymm);

}
