/**
 * 
 */
package services.impl;

import java.util.List;

import dao.IWebFormulaDao;
import entity.WebFormula;
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

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate){
		// TODO Auto-generated method stub
		return webformuladao.findFormulaIndex(factNo,factCode,createDate);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void add(WebFormula formula){
		// TODO Auto-generated method stub
		webformuladao.add(formula);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public WebFormula findById(String formulaIndex){
		// TODO Auto-generated method stub
		return webformuladao.findById(formulaIndex);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void delete(String formulaIndex){
		// TODO Auto-generated method stub
		webformuladao.delete(formulaIndex);
	}

	
}
