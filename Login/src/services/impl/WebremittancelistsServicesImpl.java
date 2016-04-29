/**
 * 
 */
package services.impl;

import dao.IWebremittancelistsDao;
import entity.KyzExpectmatmLog;
import entity.Webremittancelists;
import services.IWebremittancelistsServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebremittancelistsServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/29 上午8:10:50   
 * 修改人：Administrator   
 * 修改时间：2016/4/29 上午8:10:50   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebremittancelistsServicesImpl implements IWebremittancelistsServices{

	/**
	 * 日期:2016/4/29
	 * 描述:
	 */
	private IWebremittancelistsDao webremitsdao;
	
	
	public void setWebremitsdao(IWebremittancelistsDao webremitsdao) {
		this.webremitsdao = webremitsdao;
	}

	public Webremittancelists findById(String billNo, String itemNo) {
		// TODO Auto-generated method stub
		return webremitsdao.findById(billNo, itemNo);
	}

	/**
	 * 日期:2016/4/29
	 * 描述:
	 */
	
	
	public void delete(String billNo, String itemNo,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		webremitsdao.delete(billNo, itemNo,log);
	}

}
