/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IVKpiWebprofitlossDao;

import entity.VKpiWebprofitloss;
import services.IVKpiWebprofitlossServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpiWebprofitlossServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/9/6 下午2:26:19   
 * 修改人：Administrator   
 * 修改时间：2016/9/6 下午2:26:19   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpiWebprofitlossServicesImpl implements IVKpiWebprofitlossServices{

	/**
	 * 日期:2016/9/6
	 * 描述:
	 */
	private IVKpiWebprofitlossDao vpikprofitdao;
	
	


	public void setVpikprofitdao(IVKpiWebprofitlossDao vpikprofitdao){
		this.vpikprofitdao=vpikprofitdao;
	}




	public List<VKpiWebprofitloss> findVKpiWebprofitloss(String factNo,String yymm,String yymm2){
		// TODO Auto-generated method stub
		return vpikprofitdao.findVKpiWebprofitloss(factNo,yymm,yymm2);
	}




	/**
	 * 日期:2016/9/8
	 * 描述:
	 */
	
	
	public List<VKpiWebprofitloss> findVKpiWebprofitloss(List<String> list_factcode,String yymm){
		// TODO Auto-generated method stub
		return vpikprofitdao.findVKpiWebprofitloss(list_factcode,yymm);
	}

}
