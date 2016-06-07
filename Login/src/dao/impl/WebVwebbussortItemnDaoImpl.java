/**
 * 
 */
package dao.impl;

import java.util.List;

import dao.Basedao;
import dao.IWebVwebbussortItemnDao;
import entity.WebVwebbussortItemn;
import entity.WebVwebussortSubitem;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebVwebbussortItemnDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/6/1 上午11:10:42   
 * 修改人：Administrator   
 * 修改时间：2016/6/1 上午11:10:42   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebVwebbussortItemnDaoImpl extends Basedao implements IWebVwebbussortItemnDao{

	/**
	 * 日期:2016/6/1
	 * 描述:
	 */
	
	
	public List<WebVwebbussortItemn> findAll() {
		// TODO Auto-generated method stub
		String hql="from WebVwebbussortItemn";
		List<WebVwebbussortItemn>list=super.findAll(hql, null);
		return list;
	}

	/**
	 * 日期:2016/6/1
	 * 描述:
	 */
	
	
	public List<WebVwebussortSubitem> findAll2() {
		// TODO Auto-generated method stub
		String hql="from WebVwebussortSubitem order by webVwebbussortItemn.mid,sid";
		List<WebVwebussortSubitem>list=super.findAll(hql, null);
		for(WebVwebussortSubitem obj:list){
			obj.getWebVwebbussortItemn().getItemname();
		}
		return list;
	}

}
