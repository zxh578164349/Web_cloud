/**
 * 
 */
package dao;

import java.util.List;

import entity.WebFormula;
import entity.WebTabpom;

import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebFormulaDao   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:15:15   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:15:15   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebFormulaDao{
	public PageBean findPageBean(int page,int pageSize,WebFormula formula);
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate);
	public void add(WebFormula formula);
	public WebFormula findById(String formulaIndex);
	public void delete(String formulaIndex);
	

}
