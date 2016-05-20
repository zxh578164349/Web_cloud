/**
 * 
 */
package services;

import java.util.List;

import entity.VWebprofitlossEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebprofitlossEveServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/5 上午9:05:27   
 * 修改人：Administrator   
 * 修改时间：2016/5/5 上午9:05:27   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebprofitlossEveServices {
	public List<VWebprofitlossEve>findByYymm(String factNo,String yymm,String yymm2);
	public List<VWebprofitlossEve>findByYymm(String yymm);

}
