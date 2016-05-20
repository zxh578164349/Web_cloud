/**
 * 
 */
package services.impl;

import dao.IVKpifactNewDao;
import entity.VKpifactNew;
import services.IVKpifactNewServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpifactNewServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:42:01   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:42:01   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpifactNewServicesImpl implements IVKpifactNewServices{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	private IVKpifactNewDao vkpifactnewDao;		
	public void setVkpifactnewDao(IVKpifactNewDao vkpifactnewDao) {
		this.vkpifactnewDao = vkpifactnewDao;
	}


	public VKpifactNew findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		return vkpifactnewDao.findById(factNo, factCode, yymm);
	}

}
