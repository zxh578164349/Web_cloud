/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebVwebbussortItemnDao;

import entity.WebVwebbussortItemn;
import entity.WebVwebussortSubitem;
import services.IWebVwebbussortItemnServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebVwebbussortItemnServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/6/1 上午11:14:37   
 * 修改人：Administrator   
 * 修改时间：2016/6/1 上午11:14:37   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebVwebbussortItemnServicesImpl implements IWebVwebbussortItemnServices{

	/**
	 * 日期:2016/6/1
	 * 描述:
	 */
	private IWebVwebbussortItemnDao webvwebbussortitemdao;
	
	public void setWebvwebbussortitemdao(
			IWebVwebbussortItemnDao webvwebbussortitemdao) {
		this.webvwebbussortitemdao = webvwebbussortitemdao;
	}





	public List<WebVwebbussortItemn> findAll() {
		// TODO Auto-generated method stub
		return webvwebbussortitemdao.findAll();
	}





	/**
	 * 日期:2016/6/1
	 * 描述:
	 */
	
	
	public List<WebVwebussortSubitem> findAll2() {
		// TODO Auto-generated method stub
		return webvwebbussortitemdao.findAll2();
	}

}
