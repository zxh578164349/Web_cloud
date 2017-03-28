package services.impl;

import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import dao.IKyVisaBillsDao;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.KyzExpectmatmLog;
import entity.WebUser;
import services.IKyVisaBillsServices;
import util.PageBean;

public class KyVisaBillsServicesImpl implements IKyVisaBillsServices{
	private IKyVisaBillsDao visabillDao;

	public void setVisabillDao(IKyVisaBillsDao visabillDao) {
		this.visabillDao = visabillDao;
	}
	public List<KyVisabills> findVisaBills(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillDao.findVisaBills(visaSort, billNo);
	}
	public void add(KyVisabills vbs) {
		// TODO Auto-generated method stub
		visabillDao.add(vbs);
	}
	public KyVisabills findById(String factNo,String visaSort,String billNo,String itemNo) {
		// TODO Auto-generated method stub
		return visabillDao.findById(factNo,visaSort,billNo,itemNo);
	}
	public void delete(String factNo, String visaSort, String billNo,
			String itemNo) {
		// TODO Auto-generated method stub
		visabillDao.delete(factNo, visaSort, billNo, itemNo);
	}
	public PageBean findPageBean(int pageSize, int page,
			String visaMk, String factNo, String billNo,String visaSort,String createDate,String createDate2,WebUser user,String title,String bigType) {
		// TODO Auto-generated method stub
		return visabillDao.findPageBean(pageSize, page, visaMk, factNo, billNo,visaSort,createDate,createDate2,user,title,bigType);
	}
	public List<KyVisabills> findByFNN(String factNo, String userName) {
		// TODO Auto-generated method stub
		return visabillDao.findByFNN(factNo, userName);
	}
	public PageBean findPageBean_tw(int pageSize, int page,
			String visaMk, String factNo, String billNo, String visaSort,
			String createDate, String createDate2,WebUser user,String title,String bigType) {
		// TODO Auto-generated method stub
		return visabillDao.findPageBean_tw(pageSize, page, visaMk, factNo, billNo, visaSort, createDate, createDate2,user,title,bigType);
	}
	public int findKyVisaBills_Int() {
		// TODO Auto-generated method stub
		int result=0;
		WebUser user=(WebUser)ActionContext.getContext().getSession().get("loginUser");
		String factNo=user.getFactno();
		String email=user.getEmail()==null?"null":user.getEmail();		
		result=visabillDao.findKyVisaBills_Int(factNo, email);		
		return result;		
	}
	public int findBillsWithNo(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillDao.findBillsWithNo(visaSort, billNo);
	}
	public void delete(KyVisabills bils) {
		// TODO Auto-generated method stub
		visabillDao.delete(bils);
	}
	public int findBillsWithNo2(String visaSort, String billNo) {
		// TODO Auto-generated method stub
		return visabillDao.findBillsWithNo2(visaSort, billNo).size();
	}
	/**
	 * 日期:2016/10/28
	 * 描述:
	 */
	
	
	public List<KyVisabills> findtoprint(String visaMk,String factNo,String billNo,String visaSort,String createDate,String createDate2,WebUser user,String title,String bigType){
		// TODO Auto-generated method stub
		return visabillDao.findtoprint(visaMk,factNo,billNo,visaSort,createDate,createDate2,user,title,bigType);
	}
	

}
