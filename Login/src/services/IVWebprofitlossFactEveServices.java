/**
 * 
 */
package services;

import java.util.List;

import entity.VWebprofitlossFactEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebprofitlossFactEveServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/10 下午1:59:26   
 * 修改人：Administrator   
 * 修改时间：2016/5/10 下午1:59:26   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebprofitlossFactEveServices {
	public List<VWebprofitlossFactEve>findByYymm(String factNo,String yymm,String yymm2);
	public List<VWebprofitlossFactEve>findByYymm(String yymm);

}
