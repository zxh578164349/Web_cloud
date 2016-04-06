/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebbussort;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebbussortDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/6 上午10:15:29   
 * 修改人：Administrator   
 * 修改时间：2016/4/6 上午10:15:29   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebbussortDao {
	public List<VWebbussort>findByYymm(String yymm,String yymm2);
	

}
