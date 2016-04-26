/**
 * 
 */
package services.impl;

import dao.IWebremittancelistDao;
import entity.Webremittancelist;
import services.IWebremittancelistServices;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebremittancelistServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/25 下午3:59:59   
 * 修改人：Administrator   
 * 修改时间：2016/4/25 下午3:59:59   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebremittancelistServicesImpl implements IWebremittancelistServices{

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	private IWebremittancelistDao webremitdao;
	
	
	public void setWebremitdao(IWebremittancelistDao webremitdao) {
		this.webremitdao = webremitdao;
	}

	public void add(Webremittancelist obj) {
		// TODO Auto-generated method stub
		webremitdao.add(obj);
		
	}

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public Webremittancelist findById(String billNo) {
		// TODO Auto-generated method stub
		return webremitdao.findById(billNo);
	}

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public void delete(String billNo) {
		// TODO Auto-generated method stub
		webremitdao.delete(billNo);
	}

	/**
	 * 日期:2016/4/25
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String visaTypem,
			String factNo, String billNo) {
		// TODO Auto-generated method stub
		return webremitdao.findPageBean(pageSize, page, visaTypem, factNo, billNo);
	}

	/**
	 * 日期:2016/4/26
	 * 描述:
	 */
	
	
	public String findMaxBillNo(String factNo, String yymm) {
		// TODO Auto-generated method stub
		return webremitdao.findMaxBillNo(factNo, yymm);
	}

}
