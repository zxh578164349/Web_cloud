/**
 * 
 */
package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IVKpifactEveNewDao;
import entity.VKpifactEveNew;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VKpifactEveNewDaoImple   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 上午8:49:00   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 上午8:49:00   
 * 修改备注：   
 * @version    
 *    
 **/
public class VKpifactEveNewDaoImple extends Basedao implements IVKpifactEveNewDao{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	
	
	public VKpifactEveNew findById(String factNo, String factCode, String yymm) {
		// TODO Auto-generated method stub
		String hql="from VKpifactEveNew where id.factNo=? and id.factCode=? and id.yymm=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, yymm);
		return (VKpifactEveNew)query.uniqueResult();
	}

}
