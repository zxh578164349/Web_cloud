/**
 * 
 */
package dao;

import entity.VKpifactEveNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVKpifactEveNewDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:45:51   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:45:51   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVKpifactEveNewDao{
	public VKpifactEveNew findById(String factNo,String factCode,String yymm);

}
