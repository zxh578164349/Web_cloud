/**
 * 
 */
package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebErpProductinFormationDao;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpProductinFormationDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 上午11:15:25   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 上午11:15:25   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpProductinFormationDaoImpl extends Basedao implements IWebErpProductinFormationDao{

	/**
	 * 日期:2016/11/1
	 * 描述:
	 */
	
	
	public List<String> findItemCodeAble(){
		// TODO Auto-generated method stub
		String hql="select itemcode from WebErpProductinFormation where state=3 and status<>0";
		return super.findAll(hql,null);
	}

}
