/**
 * 
 */
package dao;

import java.util.List;

import entity.VKpiWebprofitloss;
import entity.VKpiWebprofitlossItems;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IVKpiWebprofitlossDao   
 * 类描述：重點指標彙總比較報表 
 * 创建人：Administrator   
 * 创建时间：2016/9/6 下午1:51:22   
 * 修改人：Administrator   
 * 修改时间：2016/9/6 下午1:51:22   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IVKpiWebprofitlossDao{
	public List<VKpiWebprofitloss>findVKpiWebprofitloss(String factNo,String yymm,String yymm2);
	public List<VKpiWebprofitloss>findVKpiWebprofitloss(List<String>list_factcode,String yymm);
	public List<VKpiWebprofitlossItems>findItems();

}
