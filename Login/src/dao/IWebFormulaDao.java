/**
 * 
 */
package dao;

import java.util.List;

import entity.WebFormula;
import entity.WebFormulaItems;
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
	public PageBean findPageBean(int page,int pageSize,WebFormula formula,String issuedDate_a,String issuedDate_b);
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate);
	public void add(WebFormula formula);
	
	/**
	 * 不加載關聯的屬性
	 * @Title: findById_nosession
	 * @Description: TODO
	 * @param @param formulaIndex
	 * @param @return
	 * @return WebFormula
	 * @throws
	 * @author web
	 * @date 2016/11/22
	 */
	public WebFormula findById_nosession(String formulaIndex);
	
	/**
	 * 加載關聯的屬性
	 * @Title: findById
	 * @Description: TODO
	 * @param @param formulaIndex
	 * @param @return
	 * @return WebFormula
	 * @throws
	 * @author web
	 * @date 2016/11/22
	 */
	public WebFormula findById(String formulaIndex);
	public void delete(String formulaIndex);	
	
	public void addItems(List<WebFormulaItems> webFormulaItemses);
	public WebFormulaItems findById(int itemid);
	public void deleteItems(int itemid);
	
}
