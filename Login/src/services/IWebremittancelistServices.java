/**
 * 
 */
package services;

import util.PageBean;
import entity.Webremittancelist;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebremittancelistServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/25 下午3:59:16   
 * 修改人：Administrator   
 * 修改时间：2016/4/25 下午3:59:16   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebremittancelistServices {
	public void add(Webremittancelist obj);
	public Webremittancelist findById(String billNo);
	public void delete(String billNo);
	public PageBean findPageBean(int pageSize,int page,String visaTypem,String factNo,String billNo);

}
