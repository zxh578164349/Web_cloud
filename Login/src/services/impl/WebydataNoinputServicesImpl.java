/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebydataNoinputDao;

import entity.WebydataNoinput;
import services.IWebydataNoinputServices;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebydataNoinputServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/4/8 下午4:14:55   
 * 修改人：Administrator   
 * 修改时间：2016/4/8 下午4:14:55   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebydataNoinputServicesImpl implements IWebydataNoinputServices{
	private IWebydataNoinputDao webydatenoinputdao;
	

	

	public void setWebydatenoinputdao(IWebydataNoinputDao webydatenoinputdao) {
		this.webydatenoinputdao = webydatenoinputdao;
	}

	/**
	 * 日期:2016/4/8
	 * 描述:
	 */
	
	
	public void addLarge(List<WebydataNoinput> list) {
		// TODO Auto-generated method stub
		webydatenoinputdao.addLarge(list);
	}

	/**
	 * 日期:2016/4/8
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int pageSize, int page, String factNo,
			String yymmdd, String yymmdd2) {
		// TODO Auto-generated method stub
		return webydatenoinputdao.findPageBean(pageSize, page, factNo, yymmdd, yymmdd2);
	}

}
