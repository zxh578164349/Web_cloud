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
import entity.WebTabpom;

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
	
	
	public PageBean findPageBean(int page,int pageSize,WebFormula formula){
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFormula where 1=1 ");
		hql2.append("select count(formulaIndex) ");
		if(formula.getFormulaIndex()!=null&&!"".equals(formula.getFormulaIndex())){
			hql.append(" and formulaIndex=:formulaIndex");
			map.put("formulaIndex",formula.getFormulaIndex());
		}
		if(formula.getFormulaNo()!=null&&!"".equals(formula.getFormulaNo())){
			hql.append(" and formulaNo=:formulaNo");
			map.put("formulaNo",formula.getFormulaNo());
		}
		if(formula.getFactNo().getFactNo()!=null&&!"".equals(formula.getFactNo().getFactNo())){
			hql.append(" and factNo.factNo=:factNo");
			map.put("factNo",formula.getFactNo().getFactNo());
		}
		if(formula.getFactCode().getId()!=null){//Integer類型
			hql.append(" and factCode.id=:id");
			map.put("id",formula.getFactCode().getId());
		}
		if(formula.getIssuedDate()!=null&&!"".equals(formula.getIssuedDate())){
			hql.append(" and issuedDate=:issuedDate");
			map.put("issuedDate",formula.getIssuedDate());
		}
		if(formula.getAssignBrand()!=null&&!"".equals(formula.getAssignBrand())){
			hql.append(" and assignBrand=:assignBrand");
			map.put("assignBrand",formula.getAssignBrand());
		}
		/**********************以下爲Double類型*******************************/
		if(formula.getPom().getHardness()!=null&&formula.getPom().getHardness2()!=null){
			hql.append(" and pom.hardness>=:hardness1 and and pom.hardness<=:hardness2");
			map.put("hardness1",formula.getPom().getHardness()-formula.getPom().getHardness2());
			map.put("hardness2",formula.getPom().getHardness()+formula.getPom().getHardness2());
		}else if(formula.getPom().getHardness()!=null){
			hql.append(" and pom.hardness=:hardness");
			map.put("hardness",formula.getPom().getHardness());
		}
		
		if(formula.getPom().getForces()!=null){
			hql.append(" and pom.forces=:forces");
			map.put("forces",formula.getPom().getForces());
		}
		if(formula.getPom().getExtend()!=null){
			hql.append(" and pom.extend=:extend");
			map.put("extend",formula.getPom().getExtend());
		}
		if(formula.getPom().getTearingC()!=null){
			hql.append(" and pom.tearingC=:tearingC");
			map.put("tearingC",formula.getPom().getTearingC());
		}
		if(formula.getPom().getTearingK()!=null){
			hql.append(" and pom.tearingK=:tearingK");
			map.put("tearingK",formula.getPom().getTearingK());
		}
		if(formula.getPom().getProportion()!=null&&formula.getPom().getProportion2()!=null){
			hql.append(" and pom.proportion>=:proportion1 and pom.proportion<=:proportion2");
			map.put("proportion1",formula.getPom().getProportion()-formula.getPom().getProportion2());
			map.put("proportion2",formula.getPom().getProportion()+formula.getPom().getProportion2());
		}else if(formula.getPom().getProportion()!=null){
			hql.append(" and pom.proportion=:proportion");
			map.put("proportion",formula.getPom().getProportion());
		}
		if(formula.getPom().getWresistingAkron()!=null){
			hql.append(" and pom.wresistingAkron=:wresistingAkron");
			map.put("wresistingAkron",formula.getPom().getWresistingAkron());
		}
		if(formula.getPom().getWresistingDin()!=null){
			hql.append(" and pom.wresistingDin=:wresistingDin");
			map.put("wresistingDin",formula.getPom().getWresistingDin());
		}
		if(formula.getPom().getRatioA()!=null){
			hql.append(" and pom.ratioA=:ratioA");
			map.put("ratioA",formula.getPom().getRatioA());
		}
		if(formula.getPom().getRatioB()!=null){
			hql.append(" and pom.ratioB=:ratioB");
			map.put("ratioA",formula.getPom().getRatioB());
		}
		if(formula.getPom().getAbleBend()!=null){
			hql.append(" and pom.ableBend=:ableBend");
			map.put("ableBend",formula.getPom().getAbleBend());
		}
		if(formula.getPom().getAbleYellow()!=null){
			hql.append(" and pom.ableYellow=:ableYellow");
			map.put("ableYellow",formula.getPom().getAbleYellow());
		}
		if(formula.getPom().getDefyPress()!=null){
			hql.append(" and pom.defyPress=:defyPress");
			map.put("defyPress",formula.getPom().getDefyPress());
			
		}
		if(formula.getPom().getDefyEle()!=null){
			hql.append(" and pom.defyEle=:defyEle");
			map.put("defyEle",formula.getPom().getDefyEle());
		}
		if(formula.getPom().getAgeing()!=null){
			hql.append(" and pom.ageing=:ageing");
			map.put("ageing",formula.getPom().getAgeing());
		}
		if(formula.getPom().getContract()!=null){
			hql.append(" and pom.contract=:contract");
			map.put("contract",formula.getPom().getContract());
		}
		if(formula.getPom().getElasticity()!=null){
			hql.append(" and pom.elasticity=:elasticity");
			map.put("elasticity",formula.getPom().getElasticity());
		}
		if(formula.getPom().getCompression()!=null){
			hql.append(" and pom.compression=:compression");
			map.put("compression",formula.getPom().getCompression());
		}
		if(formula.getPom().getDivision()!=null){
			hql.append(" and pom.division=:division");
			map.put("division",formula.getPom().getDivision());
		}
		if(formula.getPom().getModulus300()!=null){
			hql.append(" and pom.modulus300=:modulus300");
			map.put("modulus300",formula.getPom().getModulus300());
		}
		if(formula.getPom().getSpitCream()!=null){
			hql.append(" and pom.spitCream=:spitCream");
			map.put("spitCream",formula.getPom().getSpitCream());
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
			obj.getFactNo().getFactSname();
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
