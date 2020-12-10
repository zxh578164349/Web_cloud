/**
 * 
 */
package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebFormulaDao;
import entity.KyzExpectmatmLog;
import entity.VWebFact;
import entity.WebErpBrankProcess;
import entity.WebErpProductinFormation;
import entity.WebFormula;
import entity.WebFormulaItems;
import entity.WebTabpom;
import entity.WebTabpomfile;
import entity.WebUser;

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
	
	
	public PageBean findPageBean(int page,int pageSize,WebFormula formula,String issuedDate_a,String issuedDate_b){
		// TODO Auto-generated method stub				
		Map<String,Object>map_result=this.hqlMap(formula,issuedDate_a,issuedDate_b);
		StringBuffer hql=(StringBuffer)(StringBuffer)map_result.get("hql");
		StringBuffer hql2=(StringBuffer)(StringBuffer)map_result.get("hql2");
		Map<String,Object>map=(Map<String,Object>)map_result.get("hql_map");
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
		
		/*Set<WebFormula>set=new HashSet<WebFormula>(list);
		list=new ArrayList<WebFormula>(set);*/
		
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
	
	public List<WebFormula> findList(WebFormula formula,String issuedDate_a,String issuedDate_b){
		Map<String,Object>map_result=this.hqlMap(formula,issuedDate_a,issuedDate_b);
		StringBuffer hql=(StringBuffer)map_result.get("hql");
		Map<String,Object>map=(Map<String,Object>)map_result.get("hql_map");
		List<WebFormula>list=super.getAllWithNoPage(hql.toString(),map);
		for(WebFormula obj:list){
		    for(WebFormulaItems item:obj.getWebFormulaItemses()){
				item.getFk_weberp_pf().getNamec1();
		        item.getFk_weberp_pf().getNamec2();
				item.getFk_weberp_pf().getSelfchar1Name();
			}
			obj.getFactCode().getName();
			obj.getFactNo().getFactSname();
			if(obj.getPom()!=null){
				obj.getPom().getWebBrank().getName();
			}
			
		}
		return list;
	}
	
	public Map<String,Object> hqlMap(WebFormula formula,String issuedDate_a,String issuedDate_b){
		Map<String,Object>map_result=new HashMap<String,Object>();
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		StringBuffer hql3=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");		
				
		if(formula==null){
			formula=new WebFormula(new VWebFact(),new WebErpBrankProcess(),new WebTabpom(new WebErpBrankProcess()));
		}
		
		if(formula.getWebFormulaItemses().size()>0&&formula.getWebFormulaItemses().get(0).getFk_weberp_pf().getNamec2()!=null
				&&!"".equals(formula.getWebFormulaItemses().get(0).getFk_weberp_pf().getNamec2())){
			hql.append("select new WebFormula(w.formulaIndex,w.factNo,w.formulaNo,w.formulaName,w.factCode,w.magnification," +
					"w.semifinishedProductHardness,w.productHardness,w.brandBody,w.color,w.issuedDate,w.assignBrand" +
					") from WebFormula  w inner join  w.webFormulaItemses b where 1=1 ");
			
			hql2.append("select count(w.formulaIndex) from WebFormula w inner join  w.webFormulaItemses b where 1=1 ");
			hql3.append(" and b.fk_weberp_pf.namec2 like:namec2 ");
			map.put("namec2","%"+formula.getWebFormulaItemses().get(0).getFk_weberp_pf().getNamec2()+"%");
		}else{
			hql.append("from WebFormula w where 1=1 ");
			hql2.append("select count(w.formulaIndex) from WebFormula w where 1=1 ");
		}
		
		
		//隻有使用者用戶才廠別
		if("0".equals(user.getUserType())){
			if(formula.getFactNo().getFactNo()==null||formula.getFactNo().getFactNo().equals("")){
				String factNo=(String)ActionContext.getContext().getSession().get("factNo");//用戶的登錄廠別
				formula.getFactNo().setFactNo(factNo);
			}
			if(formula.getFactNo().getFactNo()!=null&&!"".equals(formula.getFactNo().getFactNo())&&!formula.getFactNo().getFactNo().equals("tw")){
				hql3.append(" and w.factNo.factNo=:factNo");
				map.put("factNo",formula.getFactNo().getFactNo());
			}
		}		
		if(formula.getFormulaIndex()!=null&&!"".equals(formula.getFormulaIndex())){
			hql3.append(" and w.formulaIndex=:formulaIndex");
			map.put("formulaIndex",formula.getFormulaIndex());
		}
		if(formula.getFormulaNo()!=null&&!"".equals(formula.getFormulaNo())){
			hql3.append(" and w.formulaNo=:formulaNo");
			map.put("formulaNo",formula.getFormulaNo());
		}
		
		if(formula.getFactCode().getId()!=null){//Integer類型
			hql3.append(" and w.factCode.id=:id");
			map.put("id",formula.getFactCode().getId());
		}
		
		if(issuedDate_a!=null&&!"".equals(issuedDate_a)){
			hql3.append(" and w.issuedDate>=:issuedDate_a");
			map.put("issuedDate_a",issuedDate_a);
		}
		if(issuedDate_b!=null&&!"".equals(issuedDate_b)){
			hql3.append(" and w.issuedDate<=:issuedDate_b");
			map.put("issuedDate_b",issuedDate_b);
		}
		if(formula.getAssignBrand()!=null&&!"".equals(formula.getAssignBrand())){
			hql3.append(" and w.assignBrand=:assignBrand");
			map.put("assignBrand",formula.getAssignBrand());
		}
		if(formula.getFormulaName()!=null&&!"".equals(formula.getFormulaName())){
			hql3.append(" and w.formulaName like :formulaName ");
			map.put("formulaName", "%"+formula.getFormulaName()+"%");
		}
		/**********************以下爲Double類型*******************************/		
		 if(formula.getPom().getHardness()!=null){
				hql3.append(" and w.pom.hardness between :hardness-w.pom.hardness2 and :hardness+w.pom.hardness2");
				map.put("hardness",formula.getPom().getHardness());
			}
		
		if(formula.getPom().getForces()!=null){
			hql3.append(" and w.pom.forces=:forces");
			map.put("forces",formula.getPom().getForces());
		}
		if(formula.getPom().getExtend()!=null){
			hql3.append(" and w.pom.extend=:extend");
			map.put("extend",formula.getPom().getExtend());
		}
		if(formula.getPom().getTearingC()!=null){
			hql3.append(" and w.pom.tearingC=:tearingC");
			map.put("tearingC",formula.getPom().getTearingC());
		}
		if(formula.getPom().getTearingK()!=null){
			hql3.append(" and w.pom.tearingK=:tearingK");
			map.put("tearingK",formula.getPom().getTearingK());
		}		
		if(formula.getPom().getProportion()!=null){
			hql3.append(" and w.pom.proportion between :proportion-w.pom.proportion2 and :proportion+w.pom.proportion2");
			map.put("proportion",formula.getPom().getProportion());
		}
		if(formula.getPom().getWresistingAkron()!=null){
			hql3.append(" and w.pom.wresistingAkron=:wresistingAkron");
			map.put("wresistingAkron",formula.getPom().getWresistingAkron());
		}
		if(formula.getPom().getWresistingDin()!=null){
			hql3.append(" and w.pom.wresistingDin=:wresistingDin");
			map.put("wresistingDin",formula.getPom().getWresistingDin());
		}
		if(formula.getPom().getRatioA()!=null){
			hql3.append(" and w.pom.ratioA=:ratioA");
			map.put("ratioA",formula.getPom().getRatioA());
		}
		if(formula.getPom().getRatioB()!=null){
			hql3.append(" and pom.ratioB=:ratioB");
			map.put("ratioB",formula.getPom().getRatioB());
		}
		if(formula.getPom().getAbleBend()!=null){
			hql3.append(" and w.pom.ableBend=:ableBend");
			map.put("ableBend",formula.getPom().getAbleBend());
		}
		if(formula.getPom().getAbleYellow()!=null){
			hql3.append(" and w.pom.ableYellow=:ableYellow");
			map.put("ableYellow",formula.getPom().getAbleYellow());
		}
		if(formula.getPom().getDefyPress()!=null){
			hql3.append(" and w.pom.defyPress=:defyPress");
			map.put("defyPress",formula.getPom().getDefyPress());
			
		}
		if(formula.getPom().getDefyEle()!=null){
			hql3.append(" and w.pom.defyEle=:defyEle");
			map.put("defyEle",formula.getPom().getDefyEle());
		}
		if(formula.getPom().getAgeing()!=null&&!"".equals(formula.getPom().getAgeing())){
			hql3.append(" and w.pom.ageing=:ageing");
			map.put("ageing",formula.getPom().getAgeing());
		}
		if(formula.getPom().getContract()!=null){
			hql3.append(" and w.pom.contract=:contract");
			map.put("contract",formula.getPom().getContract());
		}
		if(formula.getPom().getElasticity()!=null){
			hql3.append(" and w.pom.elasticity=:elasticity");
			map.put("elasticity",formula.getPom().getElasticity());
		}
		if(formula.getPom().getCompression()!=null){
			hql3.append(" and w.pom.compression=:compression");
			map.put("compression",formula.getPom().getCompression());
		}
		if(formula.getPom().getDivision()!=null){
			hql3.append(" and w.pom.division=:division");
			map.put("division",formula.getPom().getDivision());
		}
		if(formula.getPom().getModulus300()!=null){
			hql3.append(" and w.pom.modulus300=:modulus300");
			map.put("modulus300",formula.getPom().getModulus300());
		}
		if(formula.getPom().getSpitCream()!=null){
			hql3.append(" and w.pom.spitCream=:spitCream");
			map.put("spitCream",formula.getPom().getSpitCream());
		}
		if(formula.getPom().getAuthentications()!=null){
			hql3.append(" and w.pom.authentications=:authentications");
			map.put("authentications",formula.getPom().getAuthentications());
		}
		if(formula.getPom().getWebBrank().getId()!=null){
			hql3.append(" and w.pom.webBrank.id=:webBrank");
			map.put("webBrank",formula.getPom().getWebBrank().getId());
		}
		if(formula.getPom().getHardness2()!=null){
			hql3.append(" and w.pom.hardness2=:hardness2");
			map.put("hardness2", formula.getPom().getHardness2());
		}
		if(formula.getPom().getProportion2()!=null){
			hql3.append(" and w.pom.proportion2=:proportion2");
			map.put("proportion2", formula.getPom().getProportion2());
		}
		
		hql.append(hql3);
		hql2.append(hql3);
		hql.append(" order by w.createDate desc, w.formulaIndex ");
		
		map_result.put("hql",hql);
		map_result.put("hql2",hql2);
		map_result.put("hql_map",map);
		return map_result;
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public List<String> findFormulaIndex(String factNo,String factCode,String createDate){
		// TODO Auto-generated method stub
		String hql="select formulaIndex from WebFormula where factNo.factNo=? and factCode.id=? and createDate like ? order by formulaIndex desc";
		Object[]objs={factNo,Integer.parseInt(factCode),createDate+"%"};
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
		
	public WebFormula findById_nosession(String formulaIndex){
		String hql="from WebFormula where formulaIndex=?";
		Query query=getSession().createQuery(hql);
		query.setString(0,formulaIndex);
		WebFormula obj=(WebFormula)query.uniqueResult();
		return obj;
	}
	
	public WebFormula findById(String formulaIndex){
		// TODO Auto-generated method stub
		
		WebFormula obj=findById_nosession(formulaIndex);
		
		//子集排序
		List<WebFormulaItems> sub_list =obj.getWebFormulaItemses();	
		for(int i=0;i<sub_list.size()-1;i++){
			for(int j=0;j<sub_list.size()-1-i;j++){
				if(sub_list.get(j).getSectionNo()==sub_list.get(j+1).getSectionNo()){//相同配方階段的才排序
					if(sub_list.get(j).getFk_weberp_pf().getSelfchar1().compareTo(sub_list.get(j+1).getFk_weberp_pf().getSelfchar1())>0){
						sub_list.add(j,sub_list.get(j+1));
						sub_list.add(j+2,sub_list.get(j+1));
						sub_list.remove(j+1);
						sub_list.remove(j+2);
					}
				}
				
			}
			
		}
		
		obj.getFactNo().getFactSname();
		obj.getFactCode().getName();
		if(obj.getVbm()!=null){
			obj.getVbm().getId().getBillNo();
			obj.getVbm().getKyVisabillses().size();
		}
		for(WebFormulaItems item:obj.getWebFormulaItemses()){
			item.getFk_weberp_pf().getNamec1();
			item.getFk_weberp_pf().getNamec2();
			item.getFk_weberp_pf().getSelfchar1();
			
		}
		if(obj.getPom()!=null){
			obj.getPom().getWebBrank().getSysno();			
			obj.getPom().getWebTabpomfiles().size();
		}
		
		return obj;
	}

	/**
	 * 日期:2016/11/4
	 * 描述:
	 */
	
	
	public void delete(String formulaIndex,KyzExpectmatmLog log){
		// TODO Auto-generated method stub
		WebFormula obj=findById_nosession(formulaIndex);
		super.delete(obj,log);
	}

	/**
	 * 日期:2016/11/25
	 * 描述:
	 */
	
	
	public void addItems(List<WebFormulaItems> webFormulaItemses){
		// TODO Auto-generated method stub
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<webFormulaItemses.size();i++){
				getSession().merge(webFormulaItemses.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
				}				
			}
			//tx.commit();
		}catch(Exception e){
			tx.rollback();
			System.out.println(e);
		}
		
	}

	public WebFormulaItems findById(int itemid) {
		// TODO Auto-generated method stub
		String hql="from WebFormulaItems where itemId=?";
		Query query=getSession().createQuery(hql);
		query.setInteger(0, itemid);
		WebFormulaItems obj=(WebFormulaItems)query.uniqueResult();
		obj.getFk_weberp_pf().getNamec1();
		obj.getFk_weberp_pf().getNamec2();
		return obj;
	}

	public void deleteItems(int itemid,KyzExpectmatmLog log) {
		// TODO Auto-generated method stub
		WebFormulaItems obj=findById(itemid);
		log.setBillNo(obj.getWebFormula().getFormulaIndex());
		log.setContent(obj.getItemId()+"__"+obj.getFk_weberp_pf().getNamec1()+"__"+obj.getFk_weberp_pf().getNamec2());
		super.delete(obj,log);
	}

	
	

}
