/**
 * 
 */
package services.impl;

import java.util.List;

import services.IWebErpBrankProcessServices;

import dao.IWebErpBrankProcessDao;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpBrankProcessServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午1:19:20   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午1:19:20   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpBrankProcessServicesImpl implements IWebErpBrankProcessServices{

	/**
	 * 日期:2016/11/1
	 * 描述:
	 */
	
	private IWebErpBrankProcessDao weberpbpdao;
	
	public void setWeberpbpdao(IWebErpBrankProcessDao weberpbpdao){
		this.weberpbpdao=weberpbpdao;
	}

	public List<Object[]> findObj(String sysCode){
		// TODO Auto-generated method stub
		return weberpbpdao.findObj(sysCode);
	}

}
