/**
 * 
 */
package dao;

import java.util.List;

import entity.VWebErpProductinFormation;

/**   
 *    
 * 项目名称：Login   
 * 类名称：IWebErpProductinFormationDao   
 * 类描述：   可用配方原料代號state=3 and status <> 0
 * 创建人：Administrator   
 * 创建时间：2016/11/1 上午11:12:34   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 上午11:12:34   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebErpProductinFormationDao{
	public List<Object[]>findNamece(String selfchar1);
	public List<VWebErpProductinFormation>findTypeNo();
	public List<Object[]>findNamece(List<String>selfchar1s);
	
	public List<Object[]>findItemcategory();//所有大類別
	public List<Object[]>findNamece2(String itemcategory);//根據大類別找到產品名稱

	


}
