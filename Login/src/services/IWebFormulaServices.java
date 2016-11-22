/**
 * 
 */
package services;

import java.util.List;
import java.util.Map;

import entity.KyVisabillm;
import entity.WebFormula;
import entity.WebTabpom;

import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebFormulaServices   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:16:22   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:16:22   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebFormulaServices{
	public PageBean findPageBean(int page,int pageSize,WebFormula formula,String issuedDate_a,String issuedDate_b);	
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate);
	public void add(WebFormula formula);
	public WebFormula findById(String formulaIndex);
	public WebFormula findById_nosession(String formulaIndex);
	public void delete(String formulaIndex);
	public Map<String,Object> print(String factNo,String billNo,KyVisabillm vbm);

}
