/**
 * 
 */
package dao;

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
	public PageBean findPageBean(int page,int pageSize,String formulaIndex);

}
