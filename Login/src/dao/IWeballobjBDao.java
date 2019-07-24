/**
 * 
 */
package dao;

import java.util.List;
import entity.KyzExpectmatmLog;
import entity.VWeballobjasumwebyield;
import entity.VWeballobjasumwebyield2019;
import entity.WeballobjB;
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
public interface IWeballobjBDao {
	public void addMore(List<WeballobjB>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<WeballobjB>findAllobj(String factNo,String yymm,String yymm2);
	//public List<VWeballobjasumwebyield>findAllobj2(String factNo,String yymm,String yymm2);
	public WeballobjB findById(String factNo,String factcode,String yymm);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<WeballobjB>findObj(String yymm,String yymm2);
	
	public List<String>findFactCodes(String factNo,String yymm,String yymm2);	
	public List<WeballobjB>findWeballobjB(List<String>list_factcode,String yymm);
	public List<WeballobjB>findobjA43(String yymm);

}
