/**
 * 
 */
package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebFormulaDao;
import entity.WebFormula;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebFormulaDaoImpl   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/3 上午11:15:40   
 * 修改人：Administrator   
 * 修改时间：2016/11/3 上午11:15:40   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebFormulaDaoImpl extends Basedao implements IWebFormulaDao{

	/**
	 * 日期:2016/11/3
	 * 描述:
	 */
	
	
	public PageBean findPageBean(int page,int pageSize,String formulaIndex){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFormula where 1=1 ");
		hql2.append("select count(formulaIndex) ");
		if(formulaIndex!=null&&!formulaIndex.equals("")){
			hql.append(" and formulaIndex=:formulaIndex");
			map.put("formulaIndex",formulaIndex);
		}
		hql2.append(hql);
		hql.append(" order by formulaIndex ");
		int currentPage=PageBean.countCurrentPage(page);
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null){
			allrow=super.getAllRowCount2(hql2.toString(),map);
		}
		int totalPage=PageBean.countTotalPage(pageSize,allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize,currentPage);
		List<WebFormula>list=super.queryForPage(hql.toString(),offset,pageSize,map);
		for(WebFormula obj:list){
			obj.getFactCode().getName();
			//obj.getFactNo().getFactSname();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate){
		// TODO Auto-generated method stub
		String hql="select formulaIndex from WebFormula where factNo.factNo=? and factCode.id=? and createDate=? order by formulaIndex desc";
		Object[]objs={factNo,Integer.parseInt(factCode),createDate};
		return super.findAll(hql,objs);
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void add(WebFormula formula){
		// TODO Auto-generated method stub
		super.merge(formula);
		
	}
	
	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public WebFormula findById(String formulaIndex){
		// TODO Auto-generated method stub
		String hql="from WebFormula where formulaIndex=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,formulaIndex);
		return (WebFormula)query.uniqueResult();
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void delete(String formulaIndex){
		// TODO Auto-generated method stub
		WebFormula obj=findById(formulaIndex);
		super.delete(obj);
	}

	
	

}
