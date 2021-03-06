/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebBrProductDao;

import entity.KyzExpectmatmLog;
import entity.VWebBrProandest;
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
	
	public void delete_pro(WebBrProductitem pro,KyzExpectmatmLog log){
		webbrprodao.delete_pro(pro,log);
	}
	
	public void delete_est(WebBrEstimatingitem est,KyzExpectmatmLog log){
		webbrprodao.delete_est(est,log);
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


	/**
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public List<WebBrProductitem> findByfactNoAndYymmdd_print(String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findByfactNoAndYymmdd_print(factNo,yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean_pro(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findPageBean_pro(pageSize,page,factNo,yymmdd,yymmdd2);
	}
	
	public PageBean findPageBean_est(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		return webbrprodao.findPageBean_est(pageSize,page,factNo,yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/24
	 * 描述:
	 */
	
	
	public List<VWebBrProandest> findByfactNoAndYymmdd_print2(String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findByfactNoAndYymmdd_print2(factNo,yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/25
	 * 描述:
	 */
	
	
	public List<VWebBrProandest> findByfactCodeAndfactNoAndYymmdd_print2(String factNo,String factCode,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findByfactCodeAndfactNoAndYymmdd_print2(factNo,factCode,yymmdd);
	}


	/**
	 * 日期:2017/7/27
	 * 描述:
	 */
	
	
	public List<WebBrEstimatingitem> findEstByYymmdd(String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findEstByYymmdd(yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/28
	 * 描述:
	 */
	
	
	public List<Object[]> findSumGroupByfCodeAndYymmdd(String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findSumGroupByfCodeAndYymmdd(yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public PageBean findPageBean_proAndest(int pageSize,int page,String factNo,String yymmdd,String yymmdd2){
		// TODO Auto-generated method stub
		return webbrprodao.findPageBean_proAndest(pageSize,page,factNo,yymmdd,yymmdd2);
	}


	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public List<Object[]> findPro(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findPro(factNo,yymmdd);
	}


	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public List<Object[]> findEst(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findEst(factNo,yymmdd);
	}
	
	public List<WebBrProductitem> findPro2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findPro2(factNo,yymmdd);
	}
	
	
	public List<WebBrEstimatingitem> findEst2(String factNo,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findEst2(factNo,yymmdd);
	}


	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public WebBrProductitem findById_Pro(String factNo,Integer wid,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findById_Pro(factNo,wid,yymmdd);
	}


	/**
	 * 日期:2017/7/31
	 * 描述:
	 */
	
	
	public WebBrEstimatingitem findById_Est(String factNo,String factCode,String yymmdd){
		// TODO Auto-generated method stub
		return webbrprodao.findById_Est(factNo,factCode,yymmdd);
	}


	/**
	 * 日期:2017/8/1
	 * 描述:
	 */
	
	
	public void update_pro(WebBrProductitem pro){
		// TODO Auto-generated method stub
		webbrprodao.update_pro(pro);
		
	}


	/**
	 * 日期:2017/8/1
	 * 描述:
	 */
	
	
	public void update_est(WebBrEstimatingitem est){
		// TODO Auto-generated method stub
		webbrprodao.update_est(est);
	}

}
