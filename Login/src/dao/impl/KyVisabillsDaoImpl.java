package dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.GlobalMethod;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IKyVisaBillsDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.KyzExpectmatmLog;
import entity.WebUser;

public class KyVisabillsDaoImpl extends Basedao implements IKyVisaBillsDao{
	private final static String STR=" and id.kyVisabillm.dateCreate>'20150701' and id.kyVisabillm.delMk is null ";

	public List<KyVisabills> findVisaBills(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabills where id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? order by id.itemNo";
		String[]objs={visaSort,billNo};
		return super.findAll(hql, objs);
	}

	public void add(KyVisabills vbs) {
		// TODO Auto-generated method stub
		super.merge(vbs);
	}
	
	/**
	 * 函文審核狀況
	 * 日期:2016/5/20
	 * 描述:
	 */
	public PageBean findPageBean(int pageSize, int page,
			String visaMk, String factNo, String billNo, String visaSort,
			String createDate, String createDate2,WebUser user,String title,String bigType) {
		// TODO Auto-generated method stub
		if(factNo==null||"".equals(factNo)||"nothing".equals(factNo)){
			factNo=user.getFactno();
		}
		if(createDate==null||"".equals(createDate)){
			createDate=this.twoMonths();
		}
		String adminMk=user.getAdminMk();
		String email=user.getEmail();		
		if(email==null||email.equals("")){
			email="no";
		}
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from KyVisabills where 1=1");
		hql2.append("select count(id.itemNo) ");
		if(adminMk==null||adminMk.equals("")||adminMk.equals("N")){//非管理員
			//hql.append(" and lower(visaSigner)=:visaSigner");
			hql.append(" and (lower(visaSigner)=:visaSigner or id.kyVisabillm.userId.email=:visaSigner)");
			map.put("visaSigner", email.toLowerCase());
		}else{
			hql.append(" and id.itemNo='01'");
		}			
		if(visaMk!=null&&!visaMk.equals("")){
			hql.append(" and id.kyVisabillm.visaMk=:visamk");
			map.put("visamk", visaMk);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.kyVisabillm.id.billNo=:billno");
			map.put("billno", billNo);
		}
		if(visaSort!=null&&!visaSort.equals("")&&!visaSort.equals("nothing")){
			hql.append(" and id.kyVisabillm.id.visaSort like:visasort");
			map.put("visasort", visaSort+"%");
		}
		if(createDate!=null&&!createDate.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate>=:createdate");
			map.put("createdate", createDate);
		}
		
		if(createDate2!=null&&!createDate2.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate<=:createdate2");
			map.put("createdate2", createDate2);
		}
		/*if(factNo.equals("nothing")&&(visaMk==null||visaMk.equals(""))&&(billNo==null||billNo.equals(""))
				&&(visaSort.equals("nothing")||visaSort==null||visaSort.equals(""))
				&&(createDate==null||createDate.equals(""))
				&&(createDate2==null||createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}*/	
		if(title!=null&&!"".equals(title)){
			if("EM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzexp.memoSmk like:title");
			}
			if("CM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzletter.title like:title");
			}
			map.put("title","%"+title+"%");
		}
		hql.append(STR);
		hql2.append(hql);
		hql.append(" order by id.kyVisabillm.id.factNo desc,id.kyVisabillm.dateCreate desc,id.kyVisabillm.id.billNo desc");
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("rows", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<KyVisabills>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		//解決hibernate延遲問題
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getId().getKyVisabillm().getKyVisabillses().size();				
				GlobalMethod.vbmCotentsType(list.get(i).getId().getKyVisabillm());
			}
		}
		
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}
	
	public List<KyVisabills> findtoprint(String visaMk, String factNo, String billNo, String visaSort,
			String createDate, String createDate2,WebUser user,String title,String bigType){
		if(factNo==null||"".equals(factNo)||"nothing".equals(factNo)){
			factNo=user.getFactno();
		}
		if(createDate==null||"".equals(createDate)){
			createDate=this.twoMonths();
		}
		String adminMk=user.getAdminMk();
		String email=user.getEmail();		
		if(email==null||email.equals("")){
			email="no";
		}
		
		String date_temp;                  //20161028
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		
		hql.append("from KyVisabills where 1=1");
		hql2.append("select count(id.itemNo) ");
		if(adminMk==null||adminMk.equals("")||adminMk.equals("N")){//非管理員
			hql.append(" and lower(visaSigner)=:visaSigner");
			map.put("visaSigner", email.toLowerCase());
			date_temp=" and dateVisa";          //20161028
		}else{
			hql.append(" and id.itemNo='01'");
			date_temp=" and id.kyVisabillm.dateCreate";             //20161028
		}			
		if(visaMk!=null&&!visaMk.equals("")){
			hql.append(" and id.kyVisabillm.visaMk=:visamk");
			map.put("visamk", visaMk);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.kyVisabillm.id.billNo=:billno");
			map.put("billno", billNo);
		}
		if(visaSort!=null&&!visaSort.equals("")&&!visaSort.equals("nothing")){
			hql.append(" and id.kyVisabillm.id.visaSort like:visasort");
			map.put("visasort", visaSort+"%");
		}
		if(createDate!=null&&!createDate.equals("")){
			hql.append(date_temp+">=:createdate");
			map.put("createdate", createDate);
		}		
		if(createDate2!=null&&!createDate2.equals("")){
			hql.append(date_temp+"<=:createdate2");
			map.put("createdate2", createDate2);
		}
		/*if(factNo.equals("nothing")&&(visaMk==null||visaMk.equals(""))&&(billNo==null||billNo.equals(""))
				&&(visaSort.equals("nothing")||visaSort==null||visaSort.equals(""))
				&&(createDate==null||createDate.equals(""))
				&&(createDate2==null||createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}*/	
		if(title!=null&&!"".equals(title)){
			if("EM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzexp.memoSmk like:title");
			}
			if("CM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzletter.title like:title");
			}
			map.put("title","%"+title+"%");
		}
		hql.append(STR);
		hql2.append(hql);
		hql.append(" order by id.kyVisabillm.id.factNo desc,id.kyVisabillm.dateCreate desc");		
		List<KyVisabills>list=super.getAllWithNoPage(hql.toString(),map);
		
		//解決hibernate延遲問題
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getId().getKyVisabillm().getKyVisabillses().size();
				/***************************要分類查找，否則報No row with the given identifier exis錯誤***************************/
				/*if(list.get(i).getId().getKyVisabillm().getId().getBillNo().substring(0,2).equals("EM")){
					list.get(i).getId().getKyVisabillm().getKyzexp().getMemoSmk();
				}
				if(list.get(i).getId().getKyVisabillm().getId().getBillNo().substring(0,2).equals("CM")){
					list.get(i).getId().getKyVisabillm().getKyzletter().getTitle();
				}*/
				
				/*******************出差函文暫不要查標題，因爲沒有標題*****************/
				
			}
		}
		return list;
		
	}

	public KyVisabills findById(String factNo,String visaSort,String billNo,String itemNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabills where id.kyVisabillm.id.factNo=? and id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? and id.itemNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort);
		query.setString(2, billNo);
		query.setString(3, itemNo);
		KyVisabills vbs=(KyVisabills)query.uniqueResult();
		if(vbs!=null){
			vbs.getId().getKyVisabillm().getKyVisabillses().size();
		}
		return vbs;
	}

	public void delete(String factNo, String visaSort, String billNo,
			String itemNo) {
		// TODO Auto-generated method stub
	   //KyVisabills bills=this.findById(factNo, visaSort, billNo, itemNo);
		String hql="from KyVisabills where id.kyVisabillm.id.factNo=? and id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? and id.itemNo=?";
		Query query=getSession().createQuery(hql);
		query.setString(0, factNo);
		query.setString(1, visaSort);
		query.setString(2, billNo);
		query.setString(3, itemNo);
		KyVisabills bills=(KyVisabills)query.uniqueResult();
	   super.delete(bills);
		
	}

	

	public List<KyVisabills> findByFNN(String factNo, String userName) {
		// TODO Auto-generated method stub
		String hql="from KyVisabills where id.kyVisabillm.id.factNo=? and visaRank=? and visaMk='N'";
		String[]objs={factNo,userName};
		return super.findAll(hql, objs);
	}

	/**
	 * 函文審核
	 * 日期:2016/5/20
	 * 描述:
	 */
	public PageBean findPageBean_tw(int pageSize, int page,
			 String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,WebUser user,String title,String bigType) {
		// TODO Auto-generated method stub
		if(factNo==null||"".equals(factNo)||"nothing".equals(factNo)){
			factNo=user.getFactno();
		}
		if(createDate==null||"".equals(createDate)){
			createDate=this.twoMonths();
		}
		String adminMk=user.getAdminMk();
		String email=user.getEmail();		
		if(email==null||email.equals("")){
			email="no";
		}
		String date_temp;                  //20161028
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from KyVisabills where 1=1");
		hql2.append("select count(id.itemNo )");
		if(adminMk==null||adminMk.equals("")||adminMk.equals("N")){//非管理員
			hql.append(" and lower(visaSigner)=:visaSigner");
			map.put("visaSigner", email.toLowerCase());			
			if(visaMk!=null&&!visaMk.equals("")){				
				if(!visaMk.equals("T")){
					if(visaMk.equals("N")){
						hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk=:visamk");
						map.put("visamk", visaMk);
					}else{
						hql.append(" and visaMk=:visamk");
						map.put("visamk", visaMk);
					}					
				}else{                                      
					hql.append(" and id.kyVisabillm.visaMk=:visamk");
					map.put("visamk", visaMk);
				}
			}else{
				hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk='N'");
			}
			date_temp=" and dateVisa";          //20161028
		}else{//管理員
			if(visaMk!=null&&!visaMk.equals("")){
				hql.append(" and id.kyVisabillm.visaMk=:visamk");
				map.put("visamk", visaMk);
			}
			hql.append(" and id.itemNo='01'");
			date_temp=" and id.kyVisabillm.dateCreate";             //20161028
		}	
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
			
		}
		
		if(billNo!=null&&!billNo.equals("")){
			hql.append(" and id.kyVisabillm.id.billNo=:billno");
			map.put("billno", billNo);
		}
		if(visaSort!=null&&!visaSort.equals("")&&!visaSort.equals("nothing")){
			hql.append(" and id.kyVisabillm.id.visaSort like:visasort");
			map.put("visasort", visaSort+"%");
		}
		if(createDate!=null&&!createDate.equals("")){
			hql.append(date_temp+">=:createdate");
			map.put("createdate", createDate);
		}		
		if(createDate2!=null&&!createDate2.equals("")){
			hql.append(date_temp+"<=:createdate2");
			map.put("createdate2", createDate2);
		}
		/*if(factNo.equals("nothing")&&(visaMk==null||visaMk.equals(""))&&(billNo==null||billNo.equals(""))
				&&(visaSort.equals("nothing")||visaSort==null||visaSort.equals(""))
				&&(createDate==null||createDate.equals(""))
				&&(createDate2==null||createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}*/	
		if(title!=null&&!"".equals(title)){
			if("EM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzexp.memoSmk like:title ");
			}
			if("CM".equals(bigType)){
				hql.append(" and id.kyVisabillm.kyzletter.title like:title ");
			}
			map.put("title","%"+title+"%");
		}
		hql.append(STR);
		hql2.append(hql);
		hql.append(" order by id.kyVisabillm.id.factNo desc,id.kyVisabillm.dateCreate desc,id.kyVisabillm.id.billNo desc");
		
		if(rows!=null&&page>0){
			allrow=rows;
		}else{
			//allrow=super.getAllRowCount(hql.toString(), map);
			allrow=super.getAllRowCount2(hql2.toString(), map);
			ActionContext.getContext().getSession().put("rows", allrow);
		}
		int currentPage=PageBean.countCurrentPage(page);
		int totalPage=PageBean.countTotalPage(pageSize, allrow);
		if(currentPage>totalPage){
			currentPage=totalPage;
		}
		int offset=PageBean.countOffset(pageSize, currentPage);
		List<KyVisabills>list=super.queryForPage(hql.toString(), offset, pageSize, map);
		
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				KyVisabillm billm=list.get(i).getId().getKyVisabillm();
				billm.getSignerNext();
				//billm.getUserId().getEmail();
				GlobalMethod.vbmCotentsType(billm);
			}
		}
		
		PageBean bean=new PageBean();
		bean.setAllRow(allrow);
		bean.setCurrentPage(currentPage);
		bean.setList(list);
		bean.setPageSize(pageSize);
		bean.setTotalPage(totalPage);
		return bean;
	}

	public int findKyVisaBills_Int(String factNo,String email) {
		// TODO Auto-generated method stub
		
		String yymm=this.twoMonths();
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("select count(id.itemNo) from KyVisabills where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" and lower(visaSigner)=:visaSigner");
		map.put("visaSigner", email.toLowerCase());
		hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk='N' and flowMk='Y'") ;		
		hql.append(" and id.kyVisabillm.delMk is null ");
		hql.append(" and id.kyVisabillm.dateCreate>'"+yymm+"'");
		int result=super.getAllRowCount2(hql.toString(), map);
		return result;
		
		/*String hql="select id.itemNo from KyVisabills where id.kyVisabillm.id.factNo=? and  lower(visaSigner)=? and " +
				"id.itemNo=id.kyVisabillm.itemNext and visaMk='N' and flowMk='Y' and id.kyVisabillm.delMk is null and " +
				"id.kyVisabillm.dateCreate>'"+yymm+"'";
		Query query=getSession().createQuery(hql);
		query.setString(0,factNo);
		query.setString(1,email);
		List<KyVisabills>list=query.list();
		return list.size();*/
	}

	public int findBillsWithNo(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="select id.kyVisabillm.id.billNo from KyVisabills where id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? and flowMk='N' order by id.itemNo";		
		String[]objs={visaSort,billNo};
		return super.findAll(hql, objs).size();
	}

	public void delete(KyVisabills bils) {
		// TODO Auto-generated method stub
		super.delete(bils);
	}

	public List<KyVisabills> findBillsWithNo2(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabills where id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? and visaMk='N' and flowMk='Y' order by id.itemNo";
		String[]objs={visaSort,billNo};
		return super.findAll(hql, objs);
	}

	/**
	 * 日期:2017/5/18
	 * 描述:
	 */
	
	
	public String findById_01(String billNo){
		// TODO Auto-generated method stub
		String hql="select visaSigner from KyVisabills where id.kyVisabillm.id.billNo=? and id.itemNo='01'"; 
		Query query=getSession().createQuery(hql);
		query.setString(0,billNo);
		String str=(String)query.uniqueResult();
		return str;
	}
	
	public String twoMonths(){
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.MONTH,-2);
		String yymm=new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
		return yymm;
	}

}
