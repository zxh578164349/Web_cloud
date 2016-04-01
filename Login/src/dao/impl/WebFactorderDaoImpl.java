package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.GlobalMethod;
import util.PageBean;

import dao.Basedao;
import dao.IWebFactorderDao;
import entity.KyzExpectmatmLog;
import entity.WebFactorder;
import entity.WebFactorderId;

public class WebFactorderDaoImpl extends Basedao implements IWebFactorderDao{

	public void add(WebFactorder order) {
		// TODO Auto-generated method stub
		super.merge(order);
	}
	public WebFactorder findByOrderId(String factNo,String factArea,String yymm,String modelNo,String customer,String brank,String component) {
		// TODO Auto-generated method stub
		String hql="from WebFactorder where id.factNo=? and id.factArea=? and id.yymm=? and id.modelNo=? and id.customer=? and id.brank=? and id.component=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factArea);
		query.setString(2, yymm);
		query.setString(3, modelNo);
		query.setString(4, customer);
		query.setString(5, brank);
		query.setString(6, component);
		return (WebFactorder)query.uniqueResult();
	}
	public void delete(String factNo,String factArea,String yymm,String modelNo,String customer,String brank,String component,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		WebFactorder order=findByOrderId(factNo,factArea,yymm,modelNo,customer,brank,component);
		super.delete(order,delLog);
	}
	


	
	
	/**
	 * 经过事务声明配置
	 * 大批量導入數據20160117(修改版)
	 */
	public void addLarge2(List<List<String>>list,String username) {
		// TODO Auto-generated method stub
		List<String>objs_head=null;	
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<list.size();i++){//for1
				List<String>objs=null;
				if(i==0){
					objs_head=list.get(0);//該表頭包含了日期（日期從第6列開始）
				}else{
					objs=list.get(i);//注意：分解的數組比總列數要多齣1箇，所以開始要j=6+1
					try{
						for(int j=6+1;j<objs_head.size();j++){//for2
							
							WebFactorder order=new WebFactorder();
							WebFactorderId id=new WebFactorderId();
							order.setFactSname(objs.get(1));
							id.setFactArea(objs.get(2));
							id.setBrank(objs.get(3));
							id.setCustomer(objs.get(4));
							id.setModelNo(objs.get(5));
							id.setComponent(objs.get(6));																											
							id.setYymm(objs_head.get(j).replace("/", ""));//循環獲取日期
							id.setFactNo(objs.get(objs.size()-1));
							order.setOrderData(Double.valueOf(objs.get(j)));//循環獲取各箇日期的數據
							order.setColTemp(username);//臨時標記列
							order.setId(id);
							getSession().merge(order);																																																																					
					}//for2
						getSession().flush();
						getSession().clear();
						
					}catch(Exception e){
						System.out.println("******dao******"+e.toString()+"******dao******");
						//continue;
					}					
				}										
			}//for1
			/*tx.commit();
		    getSession().close();*/
		}catch(Exception e){
			System.out.println(e);
			tx.rollback();
		}		
		
		
	}
	
	
	
	/**
	 * 不经过事务声明配置
	 * 大批量導入數據20160124(修改版)
	 */
	public void addLarge3(List<List<String>>list,String username) {
		// TODO Auto-generated method stub
		List<String>objs_head=null;	
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<list.size();i++){//for1
				List<String>objs=null;
				if(i==0){
					objs_head=list.get(0);//該表頭包含了日期（日期從第5列開始）
				}else{
					objs=list.get(i);//注意：分解的數組比總列數要多齣1箇，所以開始要j=5+1
					try{
                       for(int j=6+1;j<objs_head.size();j++){//for2
							
							WebFactorder order=new WebFactorder();
							WebFactorderId id=new WebFactorderId();
							order.setFactSname(objs.get(1));
							id.setFactArea(objs.get(2));
							id.setBrank(objs.get(3));
							id.setCustomer(objs.get(4));
							id.setModelNo(objs.get(5));
							id.setComponent(objs.get(6));																											
							id.setYymm(objs_head.get(j).replace("/", ""));//循環獲取日期
							id.setFactNo(objs.get(objs.size()-1));
							order.setColTemp(username);//臨時標記列
							order.setOrderData(Double.valueOf(objs.get(j)));//循環獲取各箇日期的數據
							order.setId(id);
							getSession().merge(order);																																																																					
					    }//for2
						getSession().flush();
						getSession().clear();
						
					}catch(Exception e){
						System.out.println("******dao******"+e.toString()+"******dao******");
						//continue;
					}					
				}										
			}//for1
			tx.commit();
		}catch(Exception e){
			System.out.println(e);
			tx.rollback();
		}		
	    getSession().close();
		
		
	}


	public PageBean findPageBean(int pageSize, int page, List<String> factAreas,
			List<String>brank, List<String>customer, List<String>model, List<String>component,String factNo,List<String>factNos,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in (:factareas)");
			map.put("factareas", factAreas);
		}
		if(brank!=null&&brank.size()>0){
			if(brank.size()<1000){
				hql.append(" and id.brank in(:brank) ");
				map.put("brank", brank);
			}else{
				List<List<String>>list=GlobalMethod.subList(brank, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.brank in(:brank"+i+")) ");
					}else{
						hql.append(" id.brank in(:brank"+i+") or ");
					}
					map.put("brank"+i, list.get(i));
				}
			}			
		}
		if(customer!=null&&customer.size()>0){
			if(customer.size()<1000){
				hql.append(" and id.customer in(:customer) ");
				map.put("customer", customer);
			}else{
				List<List<String>>list=GlobalMethod.subList(customer, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.customer in(:customer"+i+")) ");
					}else{
						hql.append(" id.customer in(:customer"+i+") or ");
					}
					map.put("customer"+i, list.get(i));
				}
			}
			
		}
		if(model!=null&&model.size()>0){
			if(model.size()<1000){
				hql.append(" and id.modelNo in(:model)");
				map.put("model", model);
			}else{
				List<List<String>>list=GlobalMethod.subList(model, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.modelNo in(:model"+i+")) ");
					}else{
						hql.append(" id.modelNo in(:model"+i+") or ");
					}
					map.put("model"+i, list.get(i));
				}
			}
			
		}		
		if(component!=null&&component.size()>0){
			if(component.size()<1000){
				hql.append(" and id.component in(:component)");
				map.put("component", component);
			}else{
				List<List<String>>list=GlobalMethod.subList(component, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.component in(:component"+i+")) ");
					}else{
						hql.append(" id.component in(:component"+i+") or ");
					}
					map.put("component"+i, list.get(i));
				}
			}			
		}		
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(factNo.equals("tw")&&factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in(:factnos)");
			map.put("factnos", factNos);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm >=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factArea,id.brank,id.customer,id.modelNo,id.component,id.yymm");
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(rows!=null){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		int currentPage=PageBean.countCurrentPage(page);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		
		return bean;
	}


	public List<String> findComponent(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();		
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.component from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and id.factNo='nothing' ");
		}
		hql.append(" order by id.component");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findBrank(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct brank from WebFactorder where factArea in (:factsnames) order by brank";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.brank from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and id.factNo='nothing' ");
		}
		hql.append(" order by id.brank");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findCustomer(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct customer from WebFactorder where factArea in (:factsnames) order by customer";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.customer from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and id.factNo='nothing' ");
		}
		hql.append(" order by id.customer ");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findModel(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct modelNo from WebFactorder where factArea  in (:factsnames) order by modelNo";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct id.modelNo from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and id.factNo='nothing' ");
		}
		hql.append(" order by id.modelNo ");
		return super.getAllWithNoPage(hql.toString(), map);
	}
	
	/*public List<String> findFactSname(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct factSname from WebFactorder where factNo in(:factnos) order by factSname";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}*/
	public List<String> findFactArea(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct id.factArea from WebFactorder where id.factNo in(:factnos) order by id.factArea";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}
	
	


	


	/**
	 * 不定向分组统计
	 * @param factSnames
	 * @param brank
	 * @param customer
	 * @param model
	 * @param component
	 * @param year
	 * @return
	 */
	public List<Object[]> findByGroup(List<String>factNos,List<String> factAreas,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String factNo,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		String decode_factno="decode('y','n',FACT_NO,''),";
		String decode_factarea="decode('y','n',FACT_AREA,''),";
		String decode_brank="decode('y','n',brank,''),";
		String decode_customer="decode('y','n',customer,''),";
		String decode_model="decode('y','n',MODEL_NO,''),";
		String decode_component="decode('y','n',component,''),";
		if(factNos!=null&&factNos.size()>0){
			decode_factno="decode('y','y',FACT_NO,''),";
		}
		if(factAreas!=null&&factAreas.size()>0){
			decode_factarea="decode('y','y',FACT_AREA,''),";
		}
		if(brank!=null&&brank.size()>0){
			decode_brank="decode('y','y',brank,''),";
		}
		if(customer!=null&&customer.size()>0){
			decode_customer="decode('y','y',customer,''),";
		}
		if(model!=null&&model.size()>0){
			decode_model="decode('y','y',MODEL_NO,''),";
		}
		if(component!=null&&component.size()>0){
			decode_component="decode('y','y',component,''),";
		}
				
		StringBuffer hql=new StringBuffer();
		hql.append("select "+decode_factno);
		hql.append(decode_factarea);
		hql.append(decode_brank);
		hql.append(decode_customer);
		hql.append(decode_model);
		hql.append(decode_component);
		hql.append("yymm,sum(ORDER_DATA) from WEB_FACTORDER  where 1=1 ");
		Map<String,Object>map=new HashMap<String,Object>();
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and FACT_NO in(:factnos) ");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and FACT_AREA in(:factareas) ");
			map.put("factareas", factAreas);
		}
		if(brank!=null&&brank.size()>0){
			if(brank.size()<1000){
				hql.append(" and brank in(:branks) ");
				map.put("branks", brank);
			}else {
				List<List<String>>list=GlobalMethod.subList(brank, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" brank in(:branks"+i+")) ");
					}else{
						hql.append(" brank in(:branks"+i+") or ");						
					}
					map.put("branks"+i, list.get(i));
				}
			}
			
		}
		if(customer!=null&&customer.size()>0){
			if(customer.size()<1000){
				hql.append(" and customer in(:customers) ");
				map.put("customers", customer);
			}else{
				List<List<String>>list=GlobalMethod.subList(customer, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" customer in(:customer"+i+")) ");
					}else{
						hql.append(" customer in(:customer"+i+") or ");						
					}
					map.put("customer"+i, list.get(i));
					
				}
			}
			
		}
		if(model!=null&&model.size()>0){
			if(model.size()<1000){
				hql.append(" and MODEL_NO in(:modelNos) ");
				map.put("modelNos", model);
			}else{
				List<List<String>>list=GlobalMethod.subList(model, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" MODEL_NO in(:modelNos"+i+")) ");						
					}else{
						hql.append(" MODEL_NO in(:modelNos"+i+") or ");
					}
					map.put("modelNos"+i, list.get(i));
				}
			}
			
		}
		if(component!=null&&component.size()>0){
			if(component.size()<1000){
				hql.append(" and component in(:components) ");
				map.put("components", component);
			}else{
				List<List<String>>list=GlobalMethod.subList(component, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" component in(:components"+i+")) ");
					}else{
						hql.append(" component in(:components"+i+") or ");
					}
					map.put("components"+i, list.get(i));
				}
			}
			
		}		
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and FACT_NO=:factno ");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm >=:yymm ");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and yymm <=:yymm2 ");
			map.put("yymm2", yymm2);
		}
		hql.append(" group by "+decode_factno+decode_factarea+decode_brank+decode_customer+decode_model+decode_component+"yymm ");
		hql.append(" order by "+decode_factno+decode_factarea+decode_brank+decode_customer+decode_model+decode_component+"yymm ");
		List<Object[]>list=super.getAllWithNoPage_sql(hql.toString(), map);
		return list;
		
	}


	/**
	 * 在不定向分组统计的基础上，找出一年中不重复的（厂名+品牌+客户+模具+部件）
	 * @param factSnames
	 * @param brank
	 * @param customer
	 * @param model
	 * @param component
	 * @param year
	 * @return
	 */
	public List<Object[]> findByGroup2(List<String> factNos,List<String> factAreas,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String factNo,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		String decode_factno="decode('y','n',FACT_NO,''),";
		String decode_factarea="decode('y','n',FACT_AREA,''),";
		String decode_brank="decode('y','n',brank,''),";
		String decode_customer="decode('y','n',customer,''),";
		String decode_model="decode('y','n',MODEL_NO,''),";
		String decode_component="decode('y','n',component,''),";
		if(factNos!=null&&factNos.size()>0){
			decode_factno="decode('y','y',FACT_NO,''),";
		}
		if(factAreas!=null&&factAreas.size()>0){
			decode_factarea="decode('y','y',FACT_AREA,''),";
		}
		if(brank!=null&&brank.size()>0){
			decode_brank="decode('y','y',brank,''),";
		}
		if(customer!=null&&customer.size()>0){
			decode_customer="decode('y','y',customer,''),";
		}
		if(model!=null&&model.size()>0){
			decode_model="decode('y','y',MODEL_NO,''),";
		}
		if(component!=null&&component.size()>0){
			decode_component="decode('y','y',component,''),";
		}
				
		StringBuffer hql=new StringBuffer();
		hql.append("select "+decode_factno);
		hql.append(decode_factarea);
		hql.append(decode_brank);
		hql.append(decode_customer);
		hql.append(decode_model);
		hql.append(decode_component);
		hql.append("substr(yymm,0,2) from WEB_FACTORDER  where 1=1 ");
		Map<String,Object>map=new HashMap<String,Object>();
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and FACT_NO in(:factnos) ");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and FACT_AREA in(:factareas) ");
			map.put("factareas", factAreas);
		}
		/*if(brank!=null&&brank.size()>0){
			hql.append(" and brank in(:branks) ");
			map.put("branks", brank);
		}
		if(customer!=null&&customer.size()>0){
			hql.append(" and customer in(:customers) ");
			map.put("customers", customer);
		}
		if(model!=null&&model.size()>0){
			hql.append(" and MODEL_NO in(:modelNos) ");
			map.put("modelNos", model);
		}
		if(component!=null&&component.size()>0){
			hql.append(" and component in(:components) ");
			map.put("components", component);
		}*/
		if(brank!=null&&brank.size()>0){
			if(brank.size()<1000){
				hql.append(" and brank in(:branks) ");
				map.put("branks", brank);
			}else{
				List<List<String>>list=GlobalMethod.subList(brank, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" brank in(:branks"+i+")) ");
					}else{
						hql.append(" brank in(:branks"+i+") or ");						
					}
					map.put("branks"+i, list.get(i));
				}
			}
			
		}
		if(customer!=null&&customer.size()>0){
			if(customer.size()<1000){
				hql.append(" and customer in(:customers) ");
				map.put("customers", customer);
			}else{
				List<List<String>>list=GlobalMethod.subList(customer, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" customer in(:customer"+i+")) ");
					}else{
						hql.append(" customer in(:customer"+i+") or ");						
					}
					map.put("customer"+i, list.get(i));
					
				}
			}
			
		}
		if(model!=null&&model.size()>0){
			if(model.size()<1000){
				hql.append(" and MODEL_NO in(:modelNos) ");
				map.put("modelNos", model);
			}else{
				List<List<String>>list=GlobalMethod.subList(model, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" MODEL_NO in(:modelNos"+i+")) ");						
					}else{
						hql.append(" MODEL_NO in(:modelNos"+i+") or ");
					}
					map.put("modelNos"+i, list.get(i));
				}
			}
			
		}
		if(component!=null&&component.size()>0){
			if(component.size()<1000){
				hql.append(" and component in(:components) ");
				map.put("components", component);
			}else{
				List<List<String>>list=GlobalMethod.subList(component, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" component in(:components"+i+")) ");
					}else{
						hql.append(" component in(:components"+i+") or ");
					}
					map.put("components"+i, list.get(i));
				}
			}
			
		}
		
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and FACT_NO=:factno ");
			map.put("factno", factNo);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm>=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm2!=null&&!yymm2.equals("")){
			hql.append(" and yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		hql.append(" group by "+decode_factno+decode_factarea+decode_brank+decode_customer+decode_model+decode_component+"substr(yymm,0,2) ");
		hql.append(" order by "+decode_factno+decode_factarea+decode_brank+decode_customer+decode_model+decode_component+"substr(yymm,0,2) ");
		List<Object[]>list=super.getAllWithNoPage_sql(hql.toString(), map);
		return list;
	}
	/**
	 * 日期:2016/4/1
	 * 描述:
	 */
	
	
	public void deleteMore(List<String> factAreas, List<String> brank,
			List<String> customer, List<String> model, List<String> component,
			String factNo, List<String> factNos, String yymm, String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and id.factArea in (:factareas)");
			map.put("factareas", factAreas);
		}
		if(brank!=null&&brank.size()>0){
			if(brank.size()<1000){
				hql.append(" and id.brank in(:brank) ");
				map.put("brank", brank);
			}else{
				List<List<String>>list=GlobalMethod.subList(brank, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.brank in(:brank"+i+")) ");
					}else{
						hql.append(" id.brank in(:brank"+i+") or ");
					}
					map.put("brank"+i, list.get(i));
				}
			}			
		}
		if(customer!=null&&customer.size()>0){
			if(customer.size()<1000){
				hql.append(" and id.customer in(:customer) ");
				map.put("customer", customer);
			}else{
				List<List<String>>list=GlobalMethod.subList(customer, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.customer in(:customer"+i+")) ");
					}else{
						hql.append(" id.customer in(:customer"+i+") or ");
					}
					map.put("customer"+i, list.get(i));
				}
			}
			
		}
		if(model!=null&&model.size()>0){
			if(model.size()<1000){
				hql.append(" and id.modelNo in(:model)");
				map.put("model", model);
			}else{
				List<List<String>>list=GlobalMethod.subList(model, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.modelNo in(:model"+i+")) ");
					}else{
						hql.append(" id.modelNo in(:model"+i+") or ");
					}
					map.put("model"+i, list.get(i));
				}
			}
			
		}		
		if(component!=null&&component.size()>0){
			if(component.size()<1000){
				hql.append(" and id.component in(:component)");
				map.put("component", component);
			}else{
				List<List<String>>list=GlobalMethod.subList(component, 1000);
				hql.append(" and (");
				for(int i=0;i<list.size();i++){
					if(i==list.size()-1){
						hql.append(" id.component in(:component"+i+")) ");
					}else{
						hql.append(" id.component in(:component"+i+") or ");
					}
					map.put("component"+i, list.get(i));
				}
			}			
		}		
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(factNo.equals("tw")&&factNos!=null&&factNos.size()>0){
			hql.append(" and id.factNo in(:factnos)");
			map.put("factnos", factNos);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm >=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and id.yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		List<WebFactorder>list=super.getAllWithNoPage(hql.toString(), map);
		//super.deleteList(list);			
	}
	
	


	




	


	
	
	

}
