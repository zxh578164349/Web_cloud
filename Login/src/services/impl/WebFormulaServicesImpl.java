/**
 * 
 */
package services.impl;

import dao.IWebFormulaDao;
import services.IWebFormulaServices;
import util.PageBean;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebFormulaServicesImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:16:49   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:16:49   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebFormulaServicesImpl implements IWebFormulaServices{
	private IWebFormulaDao webformuladao;

	public void setWebformuladao(IWebFormulaDao webformuladao){
		this.webformuladao=webformuladao;
	}

	/**
	 * 日期:2016/11/3
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int page,int pageSize,String formulaIndex){
		// TODO Auto-generated method stub
		return webformuladao.findPageBean(page,pageSize,formulaIndex);
	}

	
}
