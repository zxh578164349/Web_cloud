/**
 * 
 */
package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebErpBrankProcessDao;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpBrankProcessDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午1:09:57   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午1:09:57   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpBrankProcessDaoImpl extends Basedao implements IWebErpBrankProcessDao{

	/**
	 * 日期:2016/11/1
	 * 描述:
	 */
	
	
	public List<Object[]> findObj(String sysCode){
		// TODO Auto-generated method stub
		String hql="select id,sysno,name from WebErpBrankProcess where syscode=?";
		String[]objs={sysCode};
		return super.findAll(hql,objs);
	}

}
