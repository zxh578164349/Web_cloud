/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IVWebprofitlossFactEveDao;

import entity.VWebprofitlossFactEve;
import services.IVWebprofitlossFactEveServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossFactEveServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/10 下午2:02:32   
 * 修改人：Administrator   
 * 修改时间：2016/5/10 下午2:02:32   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossFactEveServicesImpl implements IVWebprofitlossFactEveServices{

	/**
	 * 日期:2016/5/10
	 * 描述:
	 */
	private IVWebprofitlossFactEveDao vwebprolossfactdao;
	
	
	public void setVwebprolossfactdao(IVWebprofitlossFactEveDao vwebprolossfactdao) {
		this.vwebprolossfactdao = vwebprolossfactdao;
	}

	public List<VWebprofitlossFactEve> findByYymm(String factNo, String yymm,
			String yymm2) {
		// TODO Auto-generated method stub
		return vwebprolossfactdao.findByYymm(factNo, yymm, yymm2);
	}

	/**
	 * 日期:2016/5/10
	 * 描述:
	 */
	
	
	public List<VWebprofitlossFactEve> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		return vwebprolossfactdao.findByYymm(yymm);
	}

}
