/**
 * 
 */
package dao.impl;

import java.util.List;
import java.util.Map;

import util.PageBean;
import dao.Basedao;
import dao.IWeballobjDao;
import entity.Weballobj;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WeballobjDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 上午11:14:59   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 上午11:14:59   
 * 修改备注：   
 * @version    
 *    
 **/
public class WeballobjDaoImpl extends Basedao implements IWeballobjDao{

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public void addMore(List<Weballobj>list) {
		// TODO Auto-generated method stub
		try{
			for(Weballobj obj:list){
				getSession().merge(obj);			
			}
			getSession().flush();
			getSession().clear();
		}catch(Exception e){
			System.out.println("**************************"+e+"**********************");
		}
		
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm) {
		// TODO Auto-generated method stub
		return null;
	}

}
