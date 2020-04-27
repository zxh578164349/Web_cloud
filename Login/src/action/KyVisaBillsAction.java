package action;

import java.util.List;

import services.IKyVisaBillsServices;
import services.IKyVisabillmServices;
import util.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.KyVisabillm;
import entity.KyVisabills;
import entity.KyVisabillsId;
import entity.WebUser;

public class KyVisaBillsAction extends ActionSupport{
	private IKyVisaBillsServices visabillSer;
	private IKyVisabillmServices visabillmSer;
	private String visaSort;
	private String billNo;
	private String visa_mk;
	private String factNo;
	private String itemNo;
	private List<KyVisabills>visabills;
	private PageBean bean;
	private int page;
	private String visaMk;
	private String userName;
	private String yymmdd;
	private String yymmdd2;
	
	
	public String getYymmdd() {
		return yymmdd;
	}
	public void setYymmdd(String yymmdd) {
		this.yymmdd = yymmdd;
	}
	public String getYymmdd2() {
		return yymmdd2;
	}
	public void setYymmdd2(String yymmdd2) {
		this.yymmdd2 = yymmdd2;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public PageBean getBean() {
		return bean;
	}
	public void setBean(PageBean bean) {
		this.bean = bean;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getVisaMk() {
		return visaMk;
	}
	public void setVisaMk(String visaMk) {
		this.visaMk = visaMk;
	}
	public List<KyVisabills> getVisabills() {
		return visabills;
	}
	public void setVisabills(List<KyVisabills> visabills) {
		this.visabills = visabills;
	}

	public String getVisaSort() {
		return visaSort;
	}
	public void setVisaSort(String visaSort) {
		this.visaSort = visaSort;
	}
	public String getBillNo() {
		return billNo;
	}
	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}
	public String getVisa_mk() {
		return visa_mk;
	}
	public void setVisa_mk(String visa_mk) {
		this.visa_mk = visa_mk;
	}
	public String getFactNo() {
		return factNo;
	}
	public void setFactNo(String factNo) {
		this.factNo = factNo;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public void setVisabillSer(IKyVisaBillsServices visabillSer) {
		this.visabillSer = visabillSer;
	}
	
	public void setVisabillmSer(IKyVisabillmServices visabillmSer) {
		this.visabillmSer = visabillmSer;
	}
	public void add(){
		/*KyVisabills bill=visabillSer.findById(factNo, visaSort, billNo, itemNo);
		bill.getId().getKyVisabillm().setLastMk(visa_mk);
		KyVisabillm vbm=bill.getId().getKyVisabillm();*/
		
		int num_temp=Integer.parseInt(itemNo);
		int num=Integer.parseInt(itemNo)-1;
		int num_next=num_temp+1;
		String item_next="0"+num_next;
		KyVisabillm vbm=visabillmSer.findById(factNo, visaSort, billNo);
		vbm.setLastMk(visa_mk);
		vbm.setItemNext(item_next);		
		vbm.setItemLast(itemNo);
		vbm.getKyVisabillses().get(num).setVisaMk(visa_mk);
		String last_singer=vbm.getKyVisabillses().get(num).getVisaSigner();
		String next_singer=vbm.getKyVisabillses().get(num+1).getVisaSigner();
		vbm.setSignerLast(last_singer);
		vbm.setSignerNext(next_singer);
		visabillmSer.add(vbm);
		/*bill.getId().setKyVisabillm(vbm);
		bill.setVisaMk(visa_mk);
		visabillSer.add(bill);*/
		//return "add";
	}
	public String findVisaBills(){
		visabills=visabillSer.findVisaBills(visaSort,billNo);
		return "findVisaBills";
		
	}	

}
