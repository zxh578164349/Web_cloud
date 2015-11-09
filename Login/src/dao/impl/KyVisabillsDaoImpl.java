package dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;

import util.PageBean;

import com.opensymphony.xwork2.ActionContext;

import dao.Basedao;
import dao.IKyVisaBillsDao;
import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyVisabillsId;

public class KyVisabillsDaoImpl extends Basedao implements IKyVisaBillsDao{

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
	public PageBean findPageBean(int pageSize, int page, String userName,
			String visaMk, String factNo, String billNo, String visaSort,
			String createDate, String createDate2,String email) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from KyVisabills where 1=1");
		hql2.append("select count(id.itemNo) ");
		/*if(userName!=null&&!userName.equals("")&&!userName.contains("admin")){
			hql.append(" and lower(visaSigner)=:visaSigner");
			map.put("visaSigner", email.toLowerCase());
		}*/
		if(visaMk!=null&&!visaMk.equals("")){
			hql.append(" and id.kyVisabillm.visaMk=:visamk");
			//hql.append(" and visaMk=:visamk");
			map.put("visamk", visaMk);
		}
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
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
		}/*else{
			hql.append(" and (id.kyVisabillm.id.visaSort='F' or id.kyVisabillm.id.visaSort='W' or id.kyVisabillm.id.visaSort='G' or id.kyVisabillm.id.visaSort='I'"+
					" or id.kyVisabillm.id.visaSort='L' or id.kyVisabillm.id.visaSort='P' or id.kyVisabillm.id.visaSort='Q'" +
					" or id.kyVisabillm.id.visaSort='B' or id.kyVisabillm.id.visaSort='O'"+		
					" or id.kyVisabillm.id.visaSort='S' or id.kyVisabillm.id.visaSort='T' or id.kyVisabillm.id.visaSort='Y' or id.kyVisabillm.id.visaSort='Z'" +
					" or id.kyVisabillm.id.visaSort like 'C1%' or id.kyVisabillm.id.visaSort like 'C2%'" +
					" or id.kyVisabillm.id.visaSort like 'C3%' or id.kyVisabillm.id.visaSort like 'C4%')");
			hql.append(" and substr(id.kyVisabillm.id.visaSort,0,2) in('F','W','G','I','L','P','Q','B','O','S','T','Y','Z','C1','C2','C3','C4','C5','C6')");
		}*/
		if(createDate!=null&&!createDate.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate>=:createdate");
			map.put("createdate", createDate);
		}
		if(createDate!=null&&!createDate.equals("")&&(createDate2!=null&&!createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.dateCreate between :createdate and :createdate2");
			map.put("createdate", createDate);
			map.put("createdate2", createDate2);
		}
		if(createDate2!=null&&!createDate2.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate<=:createdate2");
			map.put("createdate2", createDate2);
		}
		if(factNo.equals("nothing")&&(visaMk==null||visaMk.equals(""))&&(billNo==null||billNo.equals(""))
				&&(visaSort.equals("nothing")||visaSort==null||visaSort.equals(""))
				&&(createDate==null||createDate.equals(""))
				&&(createDate2==null||createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		/*if(userName.contains("admin")){
			hql.append(" and id.itemNo='01'");
		}*/
		hql.append(" and id.itemNo='01'");
		/*if(factNo.equals("tw")||userName.contains("�޲z��")){
			hql.append(" and id.itemNo='01'");
		}*/
		
		//hql.append(" and flowMk='Y'");
		hql.append(" and substr(id.kyVisabillm.id.billNo,0,2) in ('CM','EM')");
		hql2.append(hql);
		hql.append(" order by id.kyVisabillm.id.factNo desc,id.kyVisabillm.dateCreate desc");
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
		
		//�H�U���ѨMHibernate������D
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				list.get(i).getId().getKyVisabillm().getKyVisabillses().size();				
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

	
	public PageBean findPageBean_tw(int pageSize, int page,
			String userName, String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,String email) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer();
		StringBuffer hql2=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		int allrow=0;
		Integer rows=(Integer)ActionContext.getContext().getSession().get("rows");
		hql.append("from KyVisabills where 1=1");
		hql2.append("select count(id.itemNo )");
		//�p�G�O�D�޲z��N�|��email�]�]�N�OvisaSigner�^����
		if(userName!=null&&!userName.equals("")&&!userName.contains("admin")){
			hql.append(" and lower(visaSigner)=:visaSigner");
			map.put("visaSigner", email.toLowerCase());
		}	
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")&&!factNo.equals("nothing")){
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
		}/*else{
			hql.append(" and (id.kyVisabillm.id.visaSort='F' or id.kyVisabillm.id.visaSort='W' or id.kyVisabillm.id.visaSort='G' or id.kyVisabillm.id.visaSort='I'"+
					" or id.kyVisabillm.id.visaSort='L' or id.kyVisabillm.id.visaSort='P' or id.kyVisabillm.id.visaSort='Q'" +
					" or id.kyVisabillm.id.visaSort='B' or id.kyVisabillm.id.visaSort='O'"+		
					" or id.kyVisabillm.id.visaSort='S' or id.kyVisabillm.id.visaSort='T' or id.kyVisabillm.id.visaSort='Y' or id.kyVisabillm.id.visaSort='Z'" +
					" or id.kyVisabillm.id.visaSort like 'C1%' or id.kyVisabillm.id.visaSort like 'C2%'" +
					" or id.kyVisabillm.id.visaSort like 'C3%' or id.kyVisabillm.id.visaSort like 'C4%')");
			hql.append(" and substr(id.kyVisabillm.id.visaSort,0,2) in('F','W','G','I','L','P','Q','B','O','S','T','Y','Z','C1','C2','C3','C4','C5','C6')");
		}*/
		if(createDate!=null&&!createDate.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate>=:createdate");
			map.put("createdate", createDate);
		}
		if(createDate!=null&&!createDate.equals("")&&(createDate2!=null&&!createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.dateCreate between :createdate and :createdate2");
			map.put("createdate", createDate);
			map.put("createdate2", createDate2);
		}
		if(createDate2!=null&&!createDate2.equals("")){
			hql.append(" and id.kyVisabillm.dateCreate<=:createdate2");
			map.put("createdate2", createDate2);
		}
		if(factNo.equals("nothing")&&(visaMk==null||visaMk.equals(""))&&(billNo==null||billNo.equals(""))
				&&(visaSort.equals("nothing")||visaSort==null||visaSort.equals(""))
				&&(createDate==null||createDate.equals(""))
				&&(createDate2==null||createDate2.equals(""))){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		/**
		 * �p�G�Oadmin�N�i�H�ݨ����
		 * �_�h�A�f�֪��A�өw
		 * �]1�^visaMk���Ů�,�h��ܤU�@�̼f�֤H����e�Τ᪺���
		 * �]2�^visaMk='T',�N�ΥD�?visaMk�A��l�Τl��visaMk
		 */
		if(userName.contains("admin")){//admin�N�i�H�ݨ����
			if(visaMk!=null&&!visaMk.equals("")){
				hql.append(" and id.kyVisabillm.visaMk=:visamk");
				map.put("visamk", visaMk);
			}
			hql.append(" and id.itemNo='01'");
		}else{//��L�Τ��A�өw
			if(visaMk!=null&&!visaMk.equals("")){				
				if(!visaMk.equals("T")){//�?��T�ɡ]�]�N��N��Y�^�A�N�Τl�?visaMk 
					if(visaMk.equals("N")){//�?N�ɡA�l�?visaMk��N,�D�?visaMk�]�n��N�A�@�Ǭ�T����ܥX�ӡA�ҥH�n�ư�
						//hql.append(" and visaMk=:visamk and id.kyVisabillm.visaMk='N'");
						hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk=:visamk");
						map.put("visamk", visaMk);
					}else{//�?Y�ɡA�Τl�?visaMk
						hql.append(" and visaMk=:visamk");
						map.put("visamk", visaMk);
					}					
				}else{//�?T, �N�ΥD��kyVisabillm��visaMk                                               
					hql.append(" and id.kyVisabillm.visaMk=:visamk");
					map.put("visamk", visaMk);
				}
			}else{//visaMk���Ů�,�h��ܤU�@�̼f�֤H����e�Τ᪺���(�u���visaMk='N',�]��visMk='T'�]���i�����)
				hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk='N'");
			}			
		}
		
		hql.append(" and substr(id.kyVisabillm.id.billNo,0,2) in ('CM','EM')");	
		hql2.append(hql);
		hql.append(" order by id.kyVisabillm.id.factNo desc,id.kyVisabillm.dateCreate desc");
		
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
		
		//�H�U���ѨMHibernate������D
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				KyVisabillm billm=list.get(i).getId().getKyVisabillm();
				billm.getSignerNext();
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
		StringBuffer hql=new StringBuffer();
		Map<String,Object>map=new HashMap<String,Object>();
		hql.append("from KyVisabills where 1=1");
		if(factNo!=null&&!factNo.equals("")&&!factNo.equals("tw")){
			hql.append(" and id.kyVisabillm.id.factNo=:factno");
			map.put("factno", factNo);
		}
		hql.append(" and lower(visaSigner)=:visaSigner");
		map.put("visaSigner", email.toLowerCase());
		hql.append(" and id.itemNo=id.kyVisabillm.itemNext and visaMk='N'") ;
				/*" and (id.kyVisabillm.id.visaSort='F' or id.kyVisabillm.id.visaSort='W' or id.kyVisabillm.id.visaSort='G' or id.kyVisabillm.id.visaSort='I'" +
				" or id.kyVisabillm.id.visaSort='L' or id.kyVisabillm.id.visaSort='P' or id.kyVisabillm.id.visaSort='Q'" +
				" or id.kyVisabillm.id.visaSort='B' or id.kyVisabillm.id.visaSort='O'" +
				" or id.kyVisabillm.id.visaSort='S' or id.kyVisabillm.id.visaSort='T' or id.kyVisabillm.id.visaSort='Y' or id.kyVisabillm.id.visaSort='Z'" +
				" or id.kyVisabillm.id.visaSort like 'C1%' or id.kyVisabillm.id.visaSort like 'C2%'" +
				" or id.kyVisabillm.id.visaSort like 'C3%' or id.kyVisabillm.id.visaSort like 'C4%')");*/	
		//hql.append(" and substr(id.kyVisabillm.id.visaSort,0,2) in('F','W','G','I','L','P','Q','B','O','S','T','Y','Z','C1','C2','C3','C4','C5','C6')");
		hql.append(" and substr(id.kyVisabillm.id.billNo,0,2) in ('CM','EM')");
		int result=super.getAllRowCount(hql.toString(), map);
		return result;
	}

	public int findBillsWithNo(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		String hql="from KyVisabills where id.kyVisabillm.id.visaSort=? and id.kyVisabillm.id.billNo=? and flowMk='N' order by id.itemNo";
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

}
