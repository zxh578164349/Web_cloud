package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
				}else if(i>1816){
					objs=list.get(i).split("__");//注意：分解的數組比總列數要多齣1箇，所以開始要j=5+1
					
					for(int j=5+1;j<objs_head.length-1;j++){//objs_head-1:表示排除最後一箇"汇总"列
						WebFactorder order=new WebFactorder();
						order.setFactSname(objs[1]);
						order.setBrank(objs[2]);
						order.setCustomer(objs[3]);
						order.setModelNo(objs[4]);
						order.setComponent(objs[5]);
						order.setOrderData(Double.valueOf(objs[j]));//循環獲取各箇日期的數據
						order.setYymm(objs_head[j]);//循環獲取日期
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


	public PageBean findPageBean(int pageSize, int page, List<String> factNos,
			String brank, String customer, String model, String component) {
		// TODO Auto-generated method stub
		//System.out.println(factNos.getClass().getName());
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebFactorder where 1=1 ");
		hql2.append("select count(*) ");
		if(factNos!=null&&factNos.size()>0){
			hql.append("and factNo in (:factnos)");
			map.put("factnos", factNos);
		}
		hql2.append(hql);
		hql.append(" order by factNo");
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(rows!=null){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		int currentPage=0;
		currentPage=currentPage>totalPage?totalPage:PageBean.countCurrentPage(page);
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

}
