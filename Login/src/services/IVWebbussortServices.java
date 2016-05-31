/**
 * 
 */
package services;

import java.util.List;

import entity.VWebbussort;
import entity.VWebbussortFcode;
import entity.VWebbussortFcodeNew;
import entity.VWebbussortNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVWebbussortServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/6 上午10:20:05   
 * 修改人：Administrator   
 * 修改时间：2016/4/6 上午10:20:05   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVWebbussortServices {
	public List<VWebbussort>findByYymm(String yymm,String yymm2);
	public List<VWebbussortFcode>findByYymm2(String yymm,String yymm2);
	
	/*****************項目重新排序后*********************/
	public List<VWebbussortNew>findByYymm_new(String yymm,String yymm2);
	public List<VWebbussortFcodeNew>findByYymm2_new(String yymm,String yymm2);

}
