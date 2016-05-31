/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IVWebbussortDao;

import entity.VWebbussort;
import entity.VWebbussortFcode;
import entity.VWebbussortFcodeNew;
import entity.VWebbussortNew;
import services.IVWebbussortServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebbussortServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/6 上午10:20:44   
 * 修改人：Administrator   
 * 修改时间：2016/4/6 上午10:20:44   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebbussortServicesImpl implements IVWebbussortServices{

	/**
	 * 日期:2016/4/6
	 * 描述:
	 */
	private IVWebbussortDao vwebsortdao;
	


	public void setVwebsortdao(IVWebbussortDao vwebsortdao) {
		this.vwebsortdao = vwebsortdao;
	}



	public List<VWebbussort> findByYymm(String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return vwebsortdao.findByYymm(yymm,yymm2);
	}



	/**
	 * 日期:2016/4/19
	 * 描述:
	 */
	
	
	public List<VWebbussortFcode> findByYymm2(String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return vwebsortdao.findByYymm2(yymm, yymm2);
	}



	/**
	 * 日期:2016/5/31
	 * 描述:
	 */
	
	
	public List<VWebbussortNew> findByYymm_new(String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return vwebsortdao.findByYymm_new(yymm, yymm2);
	}



	/**
	 * 日期:2016/5/31
	 * 描述:
	 */
	
	
	public List<VWebbussortFcodeNew> findByYymm2_new(String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return vwebsortdao.findByYymm2_new(yymm, yymm2);
	}

}
