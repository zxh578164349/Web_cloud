package services.impl;

import dao.IWebBussinessletterDao;
import entity.WebBussinessletter;
import services.IWebBussinessletterServices;
import util.PageBean;

public class WebBussinessletterServicesImpl implements IWebBussinessletterServices{
	private IWebBussinessletterDao webbussletterdao;
	

	public void setWebbussletterdao(IWebBussinessletterDao webbussletterdao) {
		this.webbussletterdao = webbussletterdao;
	}

	public void add(WebBussinessletter buss) {
		// TODO Auto-generated method stub
		webbussletterdao.add(buss);
	}

	public PageBean findPageBean(int pageSize, int page, String billNo,
			String factNo) {
		// TODO Auto-generated method stub
		return webbussletterdao.findPageBean(pageSize, page, billNo, factNo);
	}

	/**
	 * 出差函文billNO:BM+廠別代號+日期前4位+2位序號
	 */
	public String makeBillNo(String factNo, String createDate) {
		// TODO Auto-generated method stub
		StringBuffer maxbillno=new StringBuffer();
		maxbillno.append("BM"+factNo+createDate.substring(2));
		String billno=webbussletterdao.findMaxBillNo(factNo, createDate);
		if(billno==null||billno.equals("")){
			maxbillno.append("01");
		}else{
			int temp=Integer.parseInt(billno.substring(billno.length()-2))+1;
			if(temp<10){
				maxbillno.append("0"+temp);
			}else{
				maxbillno.append(temp);
			}
		}
		return maxbillno.toString();
	}

}
