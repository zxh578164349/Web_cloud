/**
 * 
 */
package services.impl;

import dao.IVKpifactEveNewDao;
import entity.VKpifactEveNew;
import services.IVKpifactEveNewServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpifactEveNewServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:52:06   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:52:06   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpifactEveNewServicesImpl implements IVKpifactEveNewServices{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	private IVKpifactEveNewDao vkpievenewDao;
	
	
	public void setVkpievenewDao(IVKpifactEveNewDao vkpievenewDao) {
		this.vkpievenewDao = vkpievenewDao;
	}


	public VKpifactEveNew findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vkpievenewDao.findById(factNo, factCode, yymm);
	}

}
