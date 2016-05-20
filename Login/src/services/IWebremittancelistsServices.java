/**
 * 
 */
package services;

import entity.KyzExpectmatmLog;
import entity.Webremittancelists;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebremittancelistsServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/29 上午8:08:47   
 * 修改人：Administrator   
 * 修改时间：2016/4/29 上午8:08:47   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebremittancelistsServices {
	public Webremittancelists findById(String billNo,String itemNog);
	public void delete(String billNo,String itemNo,KyzExpectmatmLog lo);

}
