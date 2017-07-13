/**
 * 
 */
package dao;

import java.util.List;

import util.PageBean;

import entity.WebBrProduct;

/**   
 *    
 * 项目名称：WebLogin   
 * 类名称：IWebBrProductDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/7/13 下午1:49:08   
 * 修改人：Administrator   
 * 修改时间：2017/7/13 下午1:49:08   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebBrProductDao{
	public List<WebBrProduct> findByFactno(String factNo);
	public PageBean findPageBean(int pageSize,int page,String factNo);

}
