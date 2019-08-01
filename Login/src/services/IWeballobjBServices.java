/**
 * 
 */
package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.VWeballobjasumwebyield;
import entity.VWeballobjasumwebyield2019;
import entity.VWeballobjbStorage;
import entity.WeballobjB;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWeballobjServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 下午1:10:30   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 下午1:10:30   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWeballobjBServices {
	public void addMore(List<WeballobjB>list);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm,String yymm2);
	public List<WeballobjB>findAllobj(String factNo,String yymm,String yymm2);
	public WeballobjB findById(String factNo,String factcode,String yymm);
	public void delete(String factNo,String factCode,String yymm,KyzExpectmatmLog log);
	public List<WeballobjB>findObj(String yymm,String yymm2);	
	public List<String>findFactCodes(String factNo,String yymm,String yymm2);
	public List<WeballobjB>findWeballobjB(List<String>list_factcode,String yymm);
	public List<WeballobjB>findobjA41(String yymm);
	public List<WeballobjB> findProDiff(String factNo, String yymm, String yymm2);
	public List<VWeballobjbStorage>findStorage(String yymm);

}
