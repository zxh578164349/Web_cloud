/**
 * 
 */
package services;

import java.util.Map;

import util.PageBean;
import entity.KyVisabillm;
import entity.KyzExpectmatmLog;
import entity.WebUser;
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
	public Webremittancelist findById_notype(String billNo);//不查找webtype類型名稱
	public void delete(String billNo,KyzExpectmatmLog log);
	public PageBean findPageBean(int pageSize,int page,String visaTypem,String factNo,String billNo,WebUser usre);
	public String findMaxBillNo(String factNo,String yymm);
	public Map<String, Object> print(String factNo, String billNo, String sort,KyVisabillm vbm);

}
