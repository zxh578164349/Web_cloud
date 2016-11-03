/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebErpProductinFormationDao;

import services.IWebErpProductinFormationServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpProductinFormationServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 上午11:28:07   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 上午11:28:07   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpProductinFormationServicesImpl implements IWebErpProductinFormationServices{

	/**
	 * 日期:2016/11/1
	 * 描述:
	 */
	private IWebErpProductinFormationDao weberppfdao;
	
	public void setWeberppfdao(IWebErpProductinFormationDao weberppfdao){
		this.weberppfdao=weberppfdao;
	}

	public List<Object[]> findItemcategoryAble(){
		// TODO Auto-generated method stub
		return weberppfdao.findItemcategoryAble();
	}

	/**
	 * 日期:2016/11/3
	 * 描述:
	 */
	
	
	public List<Object[]> findNamece(String itemcategory){
		// TODO Auto-generated method stub
		return weberppfdao.findNamece(itemcategory);
	}

}
