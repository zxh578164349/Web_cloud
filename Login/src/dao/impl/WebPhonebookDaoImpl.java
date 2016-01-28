package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebPhonebookDao;
import entity.VWebFact;
import entity.WebPhonebook;

public class WebPhonebookDaoImpl extends Basedao implements IWebPhonebookDao{

	public void add(WebPhonebook phone,long pbId) {
		// TODO Auto-generated method stub
		if(pbId==0){
			super.merge(phone);	
		}else{
			getSession().update(phone);
		}
			
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String department, String post, String userName) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append(" from WebPhonebook where 1=1 ");
		hql2.append("select count(pbId) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and fact.factNo=:factno");
			map.put("factno", factNo);
		}
		if(department!=null&&!department.equals("")){
			hql.append(" and department=:department");
			map.put("department", department.trim());
		}
		if(post!=null&&!post.equals("")){
			hql.append(" and post=:post");
			map.put("post", post.trim());
		}
		if(userName!=null&&!userName.equals("")){
			hql.append(" and username like:username");
			map.put("username", "%"+userName.trim()+"%");
		}
		hql2.append(hql);
		hql.append(" order by fact.factNo,department,post,userName");
		int currentPage=PageBean.countCurrentPage(page);
		Integer allrow=(Integer)ActionContext.getContext().getSession().get("allrow");
		if(allrow==null){
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<WebPhonebook> list=super.queryForPage(hql.toString(), offset, pageSize, map);
		for(int i=0;i<list.size();i++){
			list.get(i).getFact().getFactSname();
		}
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public WebPhonebook findById(long pbId) {
		// TODO Auto-generated method stub
		return (WebPhonebook)super.findById_long(pbId, WebPhonebook.class);
	}

	public void delete(long pbId) {
		// TODO Auto-generated method stub
		WebPhonebook book=this.findById(pbId);
		super.delete(book);
	}

	public void addLarge(Map<String, Object> map, String username) {
		// TODO Auto-generated method stub
		Transaction tx=null;
	      try{	    	  
	    	  //tx=getSession().beginTransaction();
	    	  for(String key:map.keySet()){
	    	  for(Object[] objs:(List<Object[]>)map.get(key)){
					WebPhonebook webphone=new WebPhonebook();
					VWebFact fact=new VWebFact();
					fact.setFactNo(key);
					webphone.setFact(fact);
					webphone.setDepartment(objs[2].toString());
					webphone.setUsername(objs[3].toString());
					webphone.setPost(objs[4].toString());
					webphone.setPhoneA(objs[5].toString());
					webphone.setPhoneB(objs[6].toString());
					webphone.setEmail(objs[7].toString());
					webphone.setPhoneC(objs[8].toString());
					webphone.setCreater(username);
					getSession().save(webphone);
					
				}
				getSession().flush();
				getSession().clear();
	    	  }
	      }catch(Exception e){
	    	  System.out.println("dao***********************"+e+"*************************dao");
	    	  //tx.rollback();
	      }
	      /*tx.commit();
	      getSession().close();*/
			
		
	}

	public List<WebPhonebook> findToPrint(String factNo, String department,
			String post, String userName) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from WebPhonebook where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and fact.factNo=:factno ");
			map.put("factno", factNo);
		}
		if(department!=null&&!department.equals("")){
			hql.append(" and department=:department ");
			map.put("department", department);
		}
		if(post!=null&&!post.equals("")){
			hql.append(" and post=:post ");
			map.put("post", post);
		}
		if(userName!=null&&!userName.equals("")){
			hql.append(" and username like:username ");
			map.put("username", userName);
		}
		hql.append(" order by fact.factNo,department,username");
		List<WebPhonebook>list=super.getAllWithNoPage(hql.toString(), map);
		for(WebPhonebook book:list){
			book.getFact().getFactSname();
		}
		return list;
	}

}
