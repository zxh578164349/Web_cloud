/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IVWebprofitlossEveDao;

import entity.VWebprofitlossEve;
import services.IVWebprofitlossEveServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebprofitlossEveServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/5/5 上午9:06:03   
 * 修改人：Administrator   
 * 修改时间：2016/5/5 上午9:06:03   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebprofitlossEveServicesImpl implements IVWebprofitlossEveServices{

	/**
	 * 日期:2016/5/5
	 * 描述:
	 */
	public IVWebprofitlossEveDao vwebprolossdao;
	
	
	public void setVwebprolossdao(IVWebprofitlossEveDao vwebprolossdao) {
		this.vwebprolossdao = vwebprolossdao;
	}


	public List<VWebprofitlossEve> findByYymm(String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return vwebprolossdao.findByYymm(yymm, yymm2);
	}


	/**
	 * 日期:2016/5/9
	 * 描述:
	 */
	
	
	public List<VWebprofitlossEve> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		return vwebprolossdao.findByYymm(yymm);
	}

}
