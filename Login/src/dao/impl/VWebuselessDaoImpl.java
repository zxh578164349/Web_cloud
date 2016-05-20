/**
 * 
 */
package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IVWebuselessDao;
import entity.VWebuseless;

/**   
 *    
 * 项目名称：Login   
 * 类名称：VWebuselessDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/13 下午2:51:49   
 * 修改人：Administrator   
 * 修改时间：2016/4/13 下午2:51:49   
 * 修改备注：   
 * @version    
 *    
 **/
public class VWebuselessDaoImpl extends Basedao implements IVWebuselessDao{

	/**
	 * 日期:2016/4/13
	 * 描述:
	 */
	
	
	public List<VWebuseless> findByYymm(String yymm) {
		// TODO Auto-generated method stub
		String hql="from VWebuseless where id.yymm=?";
		String[]objs={yymm};
		return super.findAll(hql, objs);
	}

}
