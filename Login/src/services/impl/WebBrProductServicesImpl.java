/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebBrProductDao;

import entity.KyzExpectmatmLog;
import entity.WebBrEstimatingitem;
import entity.WebBrProduct;
import entity.WebBrProductitem;
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


	public List<Object[]> findByFactno(String factNo){
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


	/**
	 * 日期:2017/7/18
	 * 描述:
	 */
	
	
	public WebBrProduct findById(String factNo,Integer wid){
		// TODO Auto-generated method stub
		return webbrprodao.findById(factNo,wid);
	}


	/**
	 * 日期:2017/7/18
	 * 描述:
	 */
	
	
	public void delete(WebBrProduct obj,KyzExpectmatmLog log){
		// TODO Auto-generated method stub
		webbrprodao.delete(obj,log);
	}


	/**
	 * 日期:2017/7/19
	 * 描述:
	 */
	
	
	public void add2(List<WebBrProductitem> listitem){
		// TODO Auto-generated method stub
		webbrprodao.add2(listitem);
	}


	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public Integer findByfactNoAndyymmdd(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findByfactNoAndyymmdd(factNo,yymmdd);
	}


	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public Integer findByfactNoAndyymmdd2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findByfactNoAndyymmdd2(factNo,yymmdd);
	}


	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public void add3(List<WebBrEstimatingitem> listest){
		// TODO Auto-generated method stub
		webbrprodao.add3(listest);
	}


	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public Integer findByFactNo2(String factNo){
		// TODO Auto-generated method stub
		return webbrprodao.findByFactNo2(factNo);
	}


	/**
	 * 日期:2017/7/21
	 * 描述:
	 */
	
	
	public void add2_3(List list){
		// TODO Auto-generated method stub
		webbrprodao.add2_3(list);
	}

}
