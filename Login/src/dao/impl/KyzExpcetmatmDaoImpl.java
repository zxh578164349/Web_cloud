package dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

import util.PageBean;

import com.lowagie.text.pdf.codec.Base64;
import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IKyzExpectmatmDao;
import entity.KyzExpectmatm;
import entity.KyzExpectmatmId;
import entity.KyzExpectmatmLog;
import entity.KyzExpectmats;
import entity.WebFixed;
import entity.WebUser;

public class KyzExpcetmatmDaoImpl extends Basedao implements IKyzExpectmatmDao {

	public void add(KyzExpectmatm kyz) {
		// TODO Auto-generated method stub
		super.merge(kyz);

	}

	public List<String> findBillNo(String factNo, String timeCreate) {
		// TODO Auto-generated method stub
		String hql = "select id.billNo from KyzExpectmatm where id. factNo=? and to_char(timeCreate,'yyyyMMdd')=?";
		String[] objs = { factNo, timeCreate };
		return super.findAll(hql, objs);
	}

	public List<KyzExpectmatm> getList(String memoSmk) {
		String hql = "from KyzExpectmatm where id.billNo=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, memoSmk);
		return query.list();
	}

	public KyzExpectmatm findById(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, id.getFactNo());
		query.setString(1, id.getBillNo());
		KyzExpectmatm kyz=(KyzExpectmatm)query.uniqueResult();
		if(kyz!=null){
			kyz.getKyzExpectmatses().size();
		}
		return kyz;
	}

	public PageBean findFixWithPage(int pageSize, int page, String factNo,
			String visaSort,String billNo,WebUser user,String timeCreate,String timeCreate2) {
		// TODO Auto-generated method stub
		int allRow=0;
		final Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer hql = new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		hql.append("from KyzExpectmatm where 1=1 ");
		hql2.append("select count(id.factNo) ");
		if (factNo != null && !factNo.equals("") && !factNo.equals("tw")&&!factNo.equals("nothing")) {
			hql.append(" and id.factNo =:factno ");
			map.put("factno", factNo);
		}
		if(visaSort!=null&&!visaSort.equals("")){
			hql.append(" and visaType like:visaType ");
			map.put("visaType", visaSort+"%");
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.billNo=:billNo ");
			map.put("billNo", billNo);
		}
		String adminmk=user.getAdminMk()==null?"no":user.getAdminMk();
		if(!adminmk.equals("Y")){
			hql.append(" and username=:usernm");
			map.put("usernm", user.getUsername());
		}
		if(timeCreate!=null&&!timeCreate.equals("")&&(timeCreate2==null||timeCreate2.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd')>=:timecreate");
			map.put("timecreate", timeCreate);
		}
		if(timeCreate!=null&&!timeCreate.equals("")&&(timeCreate2!=null&&!timeCreate2.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd') between :timecreate and :timecreate2");
			map.put("timecreate", timeCreate);
			map.put("timecreate2", timeCreate2);
		}
		if(timeCreate2!=null&&!timeCreate2.equals("")&&(timeCreate==null||timeCreate.equals(""))){
			hql.append(" and to_char(timeCreate,'yyyymmdd')<=:timecreate");
			map.put("timecreate2", timeCreate2);
		}
		if(factNo.equals("nothing")&&(visaSort==null||visaSort.equals(""))
				&&(billNo==null||billNo.equals(""))
				&&(timeCreate==null||timeCreate.equals(""))
				&&(timeCreate2==null||timeCreate2.equals(""))){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" and delMk is null ");
		hql2.append(hql);
		hql.append(" order by id.factNo,timeCreate desc");
		int currentPage = PageBean.countCurrentPage(page);
		
	    Integer rows=(Integer)ActionContext.getContext().getSession().get("allRow");	    
	    if(rows!=null&&rows!=0&&page>0){
	     allRow=rows;	     
	    }else{
		 allRow= super.getAllRowCount2(hql2.toString(),map);
		 ActionContext.getContext().getSession().put("allRow", allRow);
		}
	    
	    //allRow= super.getAllRowCount(hql.toString(),map);
		int totalPage = PageBean.countTotalPage(pageSize,allRow);
		if (currentPage > totalPage){
			currentPage = totalPage;
		}
		final int offset = PageBean.countOffset(pageSize, currentPage);
		final int length = pageSize;
		List<KyzExpectmatm> list = super.queryForPage(hql.toString(), offset,length, map);						
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setAllRow(allRow);
		pageBean.setTotalPage(totalPage);
		pageBean.setList(list);
		pageBean.init();
		return pageBean;
	}

	public void delete(KyzExpectmatmId id,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		KyzExpectmatm kyz=this.findById(id);
		super.delete(kyz,delLog);
		
	}

	public List<KyzExpectmatm> findByFactNo(String factno) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		hql.append("from KyzExpectmatm where 1=1 ");
		if(factno!=null&&!factno.equals("")&&!factno.equals("tw")){
			hql.append("and id.factNo='"+factno+"'");
		}		
		return super.findAll(hql.toString(),null);
	}

	public List<KyzExpectmatm> findById_Print(KyzExpectmatmId id) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		String[]objs={id.getFactNo(),id.getBillNo()};
		List<KyzExpectmatm>list=super.findAll(hql, objs);
		//�ѨM������D
		for(int i=0;i<list.size();i++){
			list.get(i).getKyzExpectmatses().size();
		}
		return super.findAll(hql, objs);
	}
	public List<KyzExpectmatm> findById_Print(String factNo,String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		String[]objs={factNo,billNo};
		List<KyzExpectmatm>list=super.findAll(hql, objs);
		//�ѨM������D
		for(int i=0;i<list.size();i++){
			list.get(i).getKyzExpectmatses().size();
		}
		return super.findAll(hql, objs);
	}

	public KyzExpectmatm findById2(String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		KyzExpectmatm kyz=(KyzExpectmatm)query.uniqueResult();
		if(kyz!=null){
			kyz.getKyzExpectmatses().size();
		}
		return kyz;
	}

	public String findTitleByBillno(String billNo) {
		// TODO Auto-generated method stub
		String hql="select memoSmk from KyzExpectmatm where id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, billNo);
		String title=(String)query.uniqueResult();
		return title;
	}

	public List<Object[]> findTitle(String factNo) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select id.factNo,id.billNo,memoSmk from KyzExpectmatm where 1=1 ");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.factNo=:factno");
			map.put("factno", factNo);
		}
		List<Object[]>list=super.getAllWithNoPage(hql.toString(), map);
		return list;
	}

	public KyzExpectmatm findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where id.factNo=? and id.billNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, billNo);
		KyzExpectmatm kyz=(KyzExpectmatm)query.uniqueResult();
		return kyz;
	}

	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:兩箇月之前沒有添加刪除標記的函文
	 **/
	public List<KyzExpectmatm> findBefor2Month() {
		// TODO Auto-generated method stub
		String hql="from KyzExpectmatm where timeCreate<add_months(sysdate,-2) and delMk is null order by timeCreate";		
		return super.findAll(hql, null);
	}


	/**
	 * 
	 * 日期:2016/2/17
	 * 描述:大批量添加
	 */
	public void addLarge(List<KyzExpectmatm> list) {
		// TODO Auto-generated method stub
		//Transaction tx=null;
		try{
			//tx=getSession().beginTransaction();
			for(int i=0;i<list.size();i++){
				list.get(i).setDelMk("1");
				getSession().merge(list.get(i));
				if(i%10==0){
					getSession().flush();
					getSession().clear();
				}
			}
		}catch(Exception e){
			System.out.println("dao****************************"+e+"*****************************dao");
			//tx.rollback();
		}
		//getSession().close();
	}
}
