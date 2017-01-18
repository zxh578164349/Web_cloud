/**
 * 
 */
package dao;

import java.util.List;
import java.util.Map;

import entity.KyzExpectmatmLog;
import entity.Weballobj;

import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWeballobjDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 上午11:08:48   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 上午11:08:48   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWeballobjDao {
	public void addMore(List<Weballobj>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<Weballobj>findAllobj(String factNo,String yymm,String yymm2);
	public Weballobj findById(String factNo,String factcode,String yymm);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<Weballobj>findObj(String yymm,String yymm2);
	
	public List<String>findFactCodes(String factNo,String yymm,String yymm2);

}
