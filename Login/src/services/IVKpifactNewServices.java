/**
 * 
 */
package services;

import entity.VKpifactNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVKpifactNewServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:41:13   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:41:13   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVKpifactNewServices {
	public VKpifactNew findById(String factNo,String factCode,String yymm);

}
