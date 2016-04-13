/**
 * 
 */
package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVKpifactNewDao;
import entity.VKpifactNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpifactNewDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:37:09   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:37:09   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpifactNewDaoImpl extends Basedao implements IVKpifactNewDao{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	
	
	public VKpifactNew findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VKpifactNew where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		return (VKpifactNew)query.uniqueResult();
	}

}
