package dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import com.opensymphony.xwork2.ActionContext;

import util.PageBean;
import dao.Basedao;
import dao.IWebcashoutDao;
import entity.Webcashout;

public class WebcashoutDaoImpl extends Basedao implements IWebcashoutDao{

	public void add(Webcashout cashout) {
		// TODO Auto-generated method stub
		super.merge(cashout);
	}

	public PageBean findPageBean(int pageSize,int page,String factNo,String factCode,String date) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object> map=new HashMap<String,Object>();
		hql.append("from Webcashout where 1=1");
		hql2.append("select count(id.factNo) ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(factCode!=null&&!factCode.equals("")){
			hql.append(" and id.factCode=:factcode");
			map.put("factcode", factCode);
		}
		if(date!=null&&!date.equals("")){
			hql.append(" and to_char(id.yymmdd,'yyyymm')=:date");
			map.put("date", date);
		}
		if(factNo.equals("nothing")&&(factCode==null||factCode.equals(""))&&(date==null||date.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql2.append(hql);
		hql.append(" order by id.factNo,id.factCode,id.yymmdd desc");
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allrow");
		int allrow=0;
		if(rows!=null&&rows!=0&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allrow", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<Webcashout> list=super.queryForPage(hql.toString(), offset, pageSize, map);
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public Webcashout findById(String factNo, String factCode, String date) {
		// TODO Auto-generated method stub
		String hql="from Webcashout where id.factNo=? and id.factCode=? and to_char(id.yymmdd,'yyyymmdd')=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, factCode);
		query.setString(2, date);
		Webcashout cashout=(Webcashout)query.uniqueResult();
		return cashout;
	}

	public void delete(String factNo, String factCode, String date) {
		// TODO Auto-generated method stub
		Webcashout cashout=this.findById(factNo, factCode, date);
		super.delete(cashout);
		
	}

}
