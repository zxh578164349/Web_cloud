/**
 * 
 */
package services;

import java.util.List;

import entity.VWebErpProductinFormation;

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
	 * @Title: findNamece
	 * @Description: 根據類別查找對應的原料  state=3 and status <> 0
	 * @param @return
	 * @return List<Object[]>
	 * @throws
	 * @author web
	 * @date 2016/11/3
	 */
	public List<Object[]>findNamece(String selfchar1);
	
	/**
	 * 可用配方原料類別代號
	 * @Title: findTypeNo
	 * @Description: TODO
	 * @param @return
	 * @return List<VWebErpProductinFormation>
	 * @throws
	 * @author web
	 * @date 2016/11/7
	 */
	public List<VWebErpProductinFormation>findTypeNo();
	
	public List<Object[]>findNamece(List<String>selfchar1s);
	

}
