/**
 * 
 */
package services;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.WebBrProduct;

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
	public List<WebBrProduct> findByFactno(String factNo);
	public PageBean findPageBean(int pageSize,int page,String factNo);
	public void add(List<WebBrProduct> listbrpro);
	public WebBrProduct findById(Integer wid);
	public void delete(WebBrProduct obj,KyzExpectmatmLog log);

}
