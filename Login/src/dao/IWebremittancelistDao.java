/**
 * 
 */
package dao;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebUser;
import entity.Webremittancelist;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebremittancelistDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/25 下午2:53:02   
 * 修改人：Administrator   
 * 修改时间：2016/4/25 下午2:53:02   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebremittancelistDao {
	public void add(Webremittancelist obj);
	public Webremittancelist findById(String billNo);
	public Webremittancelist findById_notype(String billNo);
	public void delete(String billNo,KyzExpectmatmLog log);
	public PageBean findPageBean(int pageSize,int page,String visaTypem,String factNo,String billNo,WebUser user);
	public String findMaxBillNo(String factNo,String yymm);
	
}
