package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;

import dao.Basedao;
import dao.IWebFactorderDao;
import entity.WebFactorder;

public class WebFactorderDaoImpl extends Basedao implements IWebFactorderDao{

	public void add(WebFactorder order) {
		// TODO Auto-generated method stub
		super.merge(order);
	}
	public WebFactorder findByOrderId(long orderid) {
		// TODO Auto-generated method stub
		return (WebFactorder)super.findById_long(orderid, WebFactorder.class);
	}
	public void delete(long orderid) {
		// TODO Auto-generated method stub
		WebFactorder order=findByOrderId(orderid);
		super.delete(order);
	}
	


	/**
	 * 大批量導入數據
	 */
	public void addLarge(List<String>list) {
		// TODO Auto-generated method stub
		String[]objs_head=null;	
		Transaction tx=null;
		try{
			tx=getSession().beginTransaction();
			for(int i=0;i<list.size();i++){
				String[]objs=null;
				if(i==0){
					objs_head=list.get(0).split("__");//該表頭包含了日期（日期從第5列開始）
				}else{
					objs=list.get(i).split("__");//注意：分解的數組比總列數要多齣1箇，所以開始要j=5+1
					
					for(int j=5+1;j<objs_head.length-1;j++){//objs_head-1:表示排除最後一箇"汇总"列
						WebFactorder order=new WebFactorder();
						order.setFactSname(objs[1]);
						order.setBrank(objs[2]);
						order.setCustomer(objs[3]);
						order.setModelNo(objs[4]);
						order.setComponent(objs[5]);
						try{
							order.setOrderData(Double.valueOf(objs[j]));//循環獲取各箇日期的數據
						}catch(Exception e){
							order.setOrderData(0.0);//報錯時，給值-1,標記數據格式不對
						}						
						order.setYymm(objs_head[j].replace("/", ""));//循環獲取日期
						order.setColTemp("1");//臨時標記列
						getSession().save(order);
						if((i*j)%25==0){
							getSession().flush();
							getSession().clear();
						}						
					}
				}			
							
			}
			//tx.commit();
		    //getSession().close();
		}catch(Exception e){
			System.out.println(e);
			tx.rollback();
		}		
		
		
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
					objs_head=list.get(0);//該表頭包含了日期（日期從第5列開始）
				}else{
					objs=list.get(i);//注意：分解的數組比總列數要多齣1箇，所以開始要j=5+1
					try{
						for(int j=6+1;j<objs_head.size();j++){//for2
							
							WebFactorder order=new WebFactorder();
							order.setFactSname(objs.get(1));
							order.setFactArea(objs.get(2));
							order.setBrank(objs.get(3));
							order.setCustomer(objs.get(4));
							order.setModelNo(objs.get(5));
							order.setComponent(objs.get(6));							
							order.setOrderData(Double.valueOf(objs.get(j)));//循環獲取各箇日期的數據													
							order.setYymm(objs_head.get(j).replace("/", ""));//循環獲取日期
							order.setFactNo(objs.get(objs.size()-1));
							order.setColTemp(username);//臨時標記列
							getSession().save(order);																																																																					
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
							order.setFactSname(objs.get(1));
							order.setFactArea(objs.get(2));
							order.setBrank(objs.get(3));
							order.setCustomer(objs.get(4));
							order.setModelNo(objs.get(5));
							order.setComponent(objs.get(6));							
							order.setOrderData(Double.valueOf(objs.get(j)));//循環獲取各箇日期的數據													
							order.setYymm(objs_head.get(j).replace("/", ""));//循環獲取日期
							order.setFactNo(objs.get(objs.size()-1));
							order.setColTemp(username);//臨時標記列
							getSession().save(order);																																																																					
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
		    getSession().close();
		}catch(Exception e){
			System.out.println(e);
			tx.rollback();
		}		
		
		
	}


	public PageBean findPageBean(int pageSize, int page, List<String> factAreas,
			List<String>brank, List<String>customer, List<String>model, List<String>component,String factNo,List<String>factNos,String yymm,String yymm2) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		hql2.append("select count(*) ");
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and factArea in (:factareas)");
			map.put("factareas", factAreas);
		}
		if(brank!=null&&brank.size()>0){
			hql.append(" and brank in(:brank) ");
			map.put("brank", brank);
		}
		if(customer!=null&&customer.size()>0){
			hql.append(" and customer in(:customer) ");
			map.put("customer", customer);
		}
		if(model!=null&&model.size()>0){
			hql.append(" and modelNo in(:model)");
			map.put("model", model);
		}
		if(component!=null&&component.size()>0){
			hql.append(" and component in(:component)");
			map.put("component", component);
		}		
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and factNo=:factno");
			map.put("factno", factNo);
		}
		if(factNo.equals("tw")&&factNos!=null&&factNos.size()>0){
			hql.append(" and factNo in(:factnos)");
			map.put("factnos", factNos);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm >=:yymm");
			map.put("yymm", yymm);
		}
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm<=:yymm2");
			map.put("yymm2", yymm2);
		}
		
		hql2.append(hql);
		hql.append(" order by factSname,brank,customer,modelNo,component,yymm");
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
		hql.append("select distinct component from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and factNo='nothing' ");
		}
		hql.append(" order by component");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findBrank(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct brank from WebFactorder where factArea in (:factsnames) order by brank";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct brank from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and factNo='nothing' ");
		}
		hql.append(" order by brank");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findCustomer(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct customer from WebFactorder where factArea in (:factsnames) order by customer";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct customer from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and factNo='nothing' ");
		}
		hql.append(" order by customer ");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	public List<String> findModel(List<String> factNos,List<String> factAreas) {
		// TODO Auto-generated method stub
		//String hql="select distinct modelNo from WebFactorder where factArea  in (:factsnames) order by modelNo";
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select distinct modelNo from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		if(factAreas!=null&&factAreas.size()>0){
			hql.append(" and factArea in(:factareas)");
			map.put("factareas", factAreas);
		}
		if(factNos==null&&factNos.size()==0&&factAreas==null&&factAreas.size()==0){
			hql.append(" and factNo='nothing' ");
		}
		hql.append(" order by modelNo ");
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
		String hql="select distinct factArea from WebFactorder where factNo in(:factnos) order by factArea";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}
	
	public List<WebFactorder> findWithNoPage(List<String> factSnames,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String year) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		if(factSnames!=null&&factSnames.size()>0){
			hql.append(" and factSname in (:factsnames)");
			map.put("factsnames", factSnames);
		}
		if(brank!=null&&brank.size()>0){
			hql.append(" and brank in(:brank) ");
			map.put("brank", brank);
		}
		if(customer!=null&&customer.size()>0){
			hql.append(" and customer in(:customer) ");
			map.put("customer", customer);
		}
		if(model!=null&&model.size()>0){
			hql.append(" and modelNo in(:model)");
			map.put("model", model);
		}
		if(component!=null&&component.size()>0){
			hql.append(" and component in(:component)");
			map.put("component", component);
		}
		if(year!=null&&!year.equals("")){
			hql.append(" and yymm like:year");
			map.put("year", year+"%");
		}
		hql.append(" order by factSname,brank,customer,modelNo,component,yymm");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	/**
	 * 如果大於12，證明數據有重複
	 * 如果小於12，需要補全
	 */
	public int findMonthData(String factSname, String brank, String customer,
			String model, String component, String year) {
		// TODO Auto-generated method stub
		String hql="select count(orderId) from WebFactorder where factSname=? and brank=? and customer=? and modelNo=? and component=? and yymm like ?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factSname);
		query.setString(1, brank);
		query.setString(2, customer);
		query.setString(2, model);
		query.setString(3, component);
		query.setString(4, year+"%");
		return (Integer)query.uniqueResult();
	}


	/**
	 * 在搜索條件下，找出所有不重複的（factNo，brank,customer,modelNo,compnent）
	 */
	public List<Object[]> findWebFactorder(List<String> factNos,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String year) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select max(orderId),factSname,brank,customer,modelNo,component from WebFactorder where 1=1 ");
		if(factNos!=null&&factNos.size()>0){
			hql.append(" and factSname in (:factnos)");
			map.put("factnos", factNos);
		}
		if(brank!=null&&brank.size()>0){
			hql.append(" and brank in(:brank) ");
			map.put("brank", brank);
		}
		if(customer!=null&&customer.size()>0){
			hql.append(" and customer in(:customer) ");
			map.put("customer", customer);
		}
		if(model!=null&&model.size()>0){
			hql.append(" and modelNo in(:model)");
			map.put("model", model);
		}
		if(component!=null&&component.size()>0){
			hql.append(" and component in(:component)");
			map.put("component", component);
		}
		if(year!=null&&!year.equals("")){
			hql.append(" and yymm like:year");
			map.put("year", year+"%");
		}
		hql.append(" group by factSname,brank,customer,modelNo,component ");
		hql.append(" order by factSname,brank,customer,modelNo,component");
		return super.getAllWithNoPage(hql.toString(), map);
	}


	/**
	 * 根據廠名，品牌，客戶，model,部件，年月找到一箇訂單（由於導入的excel文件存在重複的數據，所以要返回List
	 * 如果長度爲1，則不重複）
	 */
	public List<Double> findOrderdata(String factSname, String brank,
			String customer, String model, String component, String yymm) {
		// TODO Auto-generated method stub
		String hql="select orderData from WebFactorder where factSname=? and brank=? and customer=? and modelNo=? and component=? and yymm=?";
		String[]objs={factSname,brank,customer,model,component,yymm};
		return super.findAll(hql, objs);
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
		if(brank!=null&&brank.size()>0){
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
	
	


	




	


	
	
	

}
