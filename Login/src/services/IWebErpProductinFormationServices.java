/**
 * 
 */
package services;

import java.util.List;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpProductinFormationServices   
 * 类描述：   可用配方原料代號state=3 and status <> 0
 * 创建人：Administrator   
 * 创建时间：2016/11/1 上午11:26:01   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 上午11:26:01   
 * 修改备注：   
 * @version    
 *    
 **/
public interface IWebErpProductinFormationServices{
	/**
	 * 
	 * @Title: findItemcategoryAble
	 * @Description: 可用配方原料類別代號state=3 and status <> 0
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @author web
	 * @date 2016/11/3
	 */
	public List<Object[]>findItemcategoryAble();
	
	/**
	 * 
	 * @Title: findNamece
	 * @Description: 根據類別查找對應的原料
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @author web
	 * @date 2016/11/3
	 */
	public List<Object[]>findNamece(String itemcategory);
	

}
