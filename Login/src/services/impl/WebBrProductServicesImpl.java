/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebBrProductDao;

import entity.WebBrProduct;
import services.IWebBrProductServices;
import util.PageBean;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：WebBrProductServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午2:05:12   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午2:05:12   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebBrProductServicesImpl implements IWebBrProductServices{

	/**
	 * 日期:2017/7/13
	 * 描述:
	 */
	
	private IWebBrProductDao webbrprodao;
	
	
	public void setWebbrprodao(IWebBrProductDao webbrprodao){
		this.webbrprodao=webbrprodao;
	}


	public List<WebBrProduct> findByFactno(String factNo){
		// TODO Auto-generated method stub
		return webbrprodao.findByFactno(factNo);
	}


	/**
	 * 日期:2017/7/13
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize,int page,String factNo){
		// TODO Auto-generated method stub
		return webbrprodao.findPageBean(pageSize,page,factNo);
	}


	/**
	 * 日期:2017/7/17
	 * 描述:
	 */
	
	
	public void add(List<WebBrProduct> listbrpro){
		// TODO Auto-generated method stub
		webbrprodao.add(listbrpro);
	}

}
