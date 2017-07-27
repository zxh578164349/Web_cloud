/**
 * 
 */
package services;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.VWebBrProandest;
import entity.WebBrEstimatingitem;
import entity.WebBrProduct;
import entity.WebBrProductitem;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：IWebBrProductServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午2:04:03   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午2:04:03   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebBrProductServices{
	public List<Object[]> findByFactno(String factNo);
	public PageBean findPageBean(int pageSize,int page,String factNo);
	public void add(List<WebBrProduct> listbrpro);
	public WebBrProduct findById(String factNo,Integer wid);
	public void delete(WebBrProduct obj,KyzExpectmatmLog log);
	
	public void add2(List<WebBrProductitem>listitem);
	public Integer findByfactNoAndyymmdd(String factNo,String yymmdd);
	public Integer findByfactNoAndyymmdd2(String factNo,String yymmdd);
	public void add3(List<WebBrEstimatingitem>listest);
	public Integer findByFactNo2(String factNo);
	public void add2_3(List list);
	public List<WebBrProductitem>findByfactNoAndYymmdd_print(String factNo,String yymmdd,String yymmdd2);
	public PageBean fincPageBean(int pageSize,int page,String factNo,String yymmdd,String yymmdd2);
	public List<VWebBrProandest>findByfactNoAndYymmdd_print2(String factNo,String yymmdd,String yymmdd2);
	public List<VWebBrProandest> findByfactCodeAndfactNoAndYymmdd_print2(String factNo,String factCode,String yymmdd);
	public List<WebBrEstimatingitem>findEstByYymmdd(String yymmdd,String yymmdd2);

}
