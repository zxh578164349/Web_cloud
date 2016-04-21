/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWeballobjDao;

import entity.Weballobj;
import services.IWeballobjServices;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WeballobjServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/3/24 下午1:11:14   
 * 修改人：Administrator   
 * 修改时间：2016/3/24 下午1:11:14   
 * 修改备注：   
 * @version    
 *    
 **/
public class WeballobjServicesImpl implements IWeballobjServices{
	private IWeballobjDao weballobjdao;
	

	public void setWeballobjdao(IWeballobjDao weballobjdao) {
		this.weballobjdao = weballobjdao;
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public void addMore(List<Weballobj> list) {
		// TODO Auto-generated method stub
		weballobjdao.addMore(list);
	}

	/**
	 * 日期:2016/3/24
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymm,String yymm2) {
		// TODO Auto-generated method stub
		return weballobjdao.findPageBean(pageSize, page, factNo, yymm, yymm2);
	}

	/**
	 * 日期:2016/4/21
	 * 描述:
	 */
	
	
	public List<Weballobj> findAllobj(String factNo, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		return weballobjdao.findAllobj(factNo, yymm, yymm2);
	}

}
