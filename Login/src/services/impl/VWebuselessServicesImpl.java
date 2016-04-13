/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IVWebuselessDao;

import entity.VWebuseless;
import services.IVWebuselessServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebuselessServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 下午2:54:55   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 下午2:54:55   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebuselessServicesImpl implements IVWebuselessServices{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	private IVWebuselessDao vwebuselessdao;
	
	
	public void setVwebuselessdao(IVWebuselessDao vwebuselessdao) {
		this.vwebuselessdao = vwebuselessdao;
	}


	public List<VWebuseless> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		return vwebuselessdao.findByYymm(yymm);
	}

}
