/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebprofitlossEve;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebprofitlossEveDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/5 上午8:46:26   
 * 修改人：Administrator   
 * 修改时间：2016/5/5 上午8:46:26   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebprofitlossEveDao {
	public List<VWebprofitlossEve>findByYymm(String yymm,String yymm2);
	public List<VWebprofitlossEve>findByYymm(String yymm);

}
