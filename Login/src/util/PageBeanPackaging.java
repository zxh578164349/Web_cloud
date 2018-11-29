package util;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import entity.WebNewproduct;

public class PageBeanPackaging extends Basedao{
	
	public  PageBean pbPackaging(StringBuffer hql,StringBuffer hql2,int page,int pageSize,Map<String,Object>map){
		int allRow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");
		if(rows!=null&&rows!=0&&page>0){
			allRow=rows;
		}else{
			allRow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("allRow", allRow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allRow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		PageBean bean=new PageBean();
		bean.setAllRow(allRow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
		
		
	}

}
