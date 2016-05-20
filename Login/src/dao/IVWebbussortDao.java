/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebbussort;
import entity.VWebbussortFcode;

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
	
	/**
	 * 分廠別狀態
	 * @Title: findByYymm2
	 * @Description: TODO
	 * @param @param yymm
	 * @param @param yymm2
	 * @param @return
	 * @return List<VWebbussortFcode>
	 * @throws
	 * @author web
	 * @date 2016/4/19
	 */
	public List<VWebbussortFcode>findByYymm2(String yymm,String yymm2);
	

}
