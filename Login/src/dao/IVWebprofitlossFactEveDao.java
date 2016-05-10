/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebprofitlossFactEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebprofitlossFactEveDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/10 下午1:38:57   
 * 修改人：Administrator   
 * 修改时间：2016/5/10 下午1:38:57   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebprofitlossFactEveDao {
	public List<VWebprofitlossFactEve>findByYymm(String factNo,String yymm,String yymm2);
	public List<VWebprofitlossFactEve>findByYymm(String yymm);

}
