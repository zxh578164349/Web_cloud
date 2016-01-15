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


	/**
	 * 大批量導入數據
	 */
	public void addLarge(List<String>list) {
		// TODO Auto-generated method stub
		String[]objs_head=null;	
		Transaction tx=null;
		try{
			//tx=getSession().beginTransaction();
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
							order.setOrderData(-1.0);//報錯時，給值-1,標記數據格式不對
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
			//tx.rollback();
		}		
		
		
	}


	public PageBean findPageBean(int pageSize, int page, List<String> factSnames,
			List<String>brank, List<String>customer, List<String>model, List<String>component) {
		// TODO Auto-generated method stub
		//System.out.println(factNos.getClass().getName());
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		hql2.append("select count(*) ");
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


	public List<String> findComponent(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct component from WebFactorder where factNo  in (:factnos) order by component";
		Map<String,Object>map=new HashMap<String,Object>();
		//System.out.println(factSname.getClass().getName());
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}


	public List<String> findBrank(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct brank from WebFactorder where factNo in (:factnos) order by brank";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}


	public List<String> findCustomer(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct customer from WebFactorder where factNo in (:factnos) order by customer";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}


	public List<String> findModel(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct modelNo from WebFactorder where factNo  in (:factnos) order by modelNo";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}
	
	public List<String> findFactSname(List<String> factNos) {
		// TODO Auto-generated method stub
		String hql="select distinct factSname from WebFactorder where factNo in(:factnos) order by factSname";
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("factnos", factNos);
		return super.getAllWithNoPage(hql, map);
	}
	
	public List<WebFactorder> findWithNoPage(List<String> factSnames,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component,String yymm) {
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
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm like:yymm");
			map.put("yymm", yymm+"%");
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
			List<String> component, String yymm) {
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
		if(yymm!=null&&!yymm.equals("")){
			hql.append(" and yymm like:yymm");
			map.put("yymm", yymm+"%");
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


	public List<Object[]> findByGroup(List<String> factSnames,
			List<String> brank, List<String> customer, List<String> model,
			List<String> component, String yymm) {
		// TODO Auto-generated method stub
		String decode_fact="decode('y',:factsname_mk,FACT_SNAME,''),";
		String decode_brank="decode('y',:brank_mk,brank,''),";
		String decode_customer="decode('y',:customer_mk,customer,''),";
		String decode_model="decode('y',:modelno_mk,MODEL_NO,''),";
		String decode_component="decode('y',:component_mk,component,''),";
		StringBuffer hql=new StringBuffer();
		hql.append("select "+decode_fact);
		hql.append(decode_brank);
		hql.append(decode_customer);
		hql.append(decode_model);
		hql.append(decode_component);
		hql.append("yymm,sum(ORDER_DATA) from WEB_FACTORDER where 1=1 ");
		Map<String,Object>map=new HashMap<String,Object>();
		if(factSnames!=null&&factSnames.size()>0){
			hql.append(" and FACT_SNAME in(:factsnames) ");
			map.put("factsnames", factSnames);
			map.put("factsname_mk", "y");
		}else{
			map.put("factsname_mk", "n");
		}
		if(brank!=null&&brank.size()>0){
			hql.append(" and brank in(:branks) ");
			map.put("branks", brank);
			map.put("brank_mk", "y");
		}else{
			map.put("brank_mk", "n");
		}
		if(customer!=null&&customer.size()>0){
			hql.append(" and customer in(:customers) ");
			map.put("customers", customer);
			map.put("customer_mk", "y");
		}else{
			map.put("customer_mk", "n");
		}
		if(model!=null&&model.size()>0){
			hql.append(" and MODEL_NO in(:modelNos) ");
			map.put("modelNos", model);
			map.put("modelno_mk", "y");
		}else{
			map.put("modelno_mk", "n");
		}
		if(component!=null&&component.size()>0){
			hql.append(" and component in(:components) ");
			map.put("components", component);
			map.put("component_mk", "y");
		}else{
			map.put("component_mk", "n");
		}
		hql.append("group by "+decode_fact+decode_brank+decode_customer+decode_model+decode_component+"yymm ");		
		//hql.append("order by "+decode_fact+decode_brank+decode_customer+decode_model+decode_component+"yymm ");
		//List<Object[]>list=super.getAllWithNoPage_sql(hql.toString(), map);
		//String hql2="select decode('y','y',FACT_SNAME,''),decode('y','n',brank,''),decode('y','n', customer,''),decode('y','n',MODEL_NO,''), decode('y','n',component,''),yymm, sum(ORDER_DATA) from WEB_FACTORDER where 1=1  and FACT_SNAME in('北越加九(順益)有限公司RB廠')  group by decode('y', 'y',FACT_SNAME,''),decode('y','n',brank,''),decode('y','n',customer, ''),decode('y','n',MODEL_NO,''),decode('y','n',component,''),yymm ";
		Query query=getSession().createSQLQuery(hql.toString());
		if (map != null && !map.isEmpty()) {
			for (String key : map.keySet()) {
				if(map.get(key).getClass().getName().equals("com.opensymphony.xwork2.util.XWorkList")||
						map.get(key).getClass().getName().equals("java.util.ArrayList")	){					
					query.setParameterList(key, (List<String>)map.get(key));
				}else{
					query.setParameter(key, map.get(key));
				}
				System.out.println(map.get(key));
			}
		}
		return query.list();
	}


	


	
	
	

}
