/**
 * 
 */
package dao.impl;

import org.hibernate.Query;

import dao.Basedao;
import dao.IWebremittancelistsDao;
import entity.KyzExpectmatmLog;
import entity.Webremittancelists;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebremittancelistsDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/29 上午8:03:23   
 * 修改人：Administrator   
 * 修改时间：2016/4/29 上午8:03:23   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebremittancelistsDaoImpl extends Basedao implements IWebremittancelistsDao{

	/**
	 * 日期:2016/4/29
	 * 描述:
	 */
	
	
	public Webremittancelists findById(String billNo, String itemNo) {
		// TODO Auto-generated method stub
		String hql="from Webremittancelists where id.webremittancelist.billNo=? and id.itemNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		query.setString(1, itemNo);
		Webremittancelists obj=(Webremittancelists)query.uniqueResult();
		return obj;
	}

	/**
	 * 日期:2016/4/29
	 * 描述:
	 */
	
	
	public void delete(String billNo, String itemNo,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		Webremittancelists obj=this.findById(billNo, itemNo);
		super.delete(obj,log);
	
	}

}
