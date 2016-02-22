package services.impl;

import java.util.List;

import dao.IKyzPettyDao;
import entity.KyzPetty;
import services.IKyzPettyServices;
import util.PageBean;

public class KyzPettyServicesImpl implements IKyzPettyServices{
	private IKyzPettyDao kyzpettydao;

	public void setKyzpettydao(IKyzPettyDao kyzpettydao) {
		this.kyzpettydao = kyzpettydao;
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String billNo, String createTime) {
		// TODO Auto-generated method stub
		return kyzpettydao.findPageBean(pageSize, page, factNo, billNo, createTime);
	}

	public void add(KyzPetty kyzpetty) {
		// TODO Auto-generated method stub
		kyzpettydao.add(kyzpetty);
	}

	public String makeItemNo(String factNo,String payDate) {
		// TODO Auto-generated method stub
		List<KyzPetty>list=kyzpettydao.findByFactNoAndPayDate(factNo, payDate);
		int maxInt=0;
		String maxStr="0000";
		if(list.size()>0){//start if
			for(int i=0;i<list.size();i++){
				String itemno=list.get(i).getItemNo();
				int itemnoInt=Integer.parseInt(itemno);
				if(itemnoInt>maxInt||itemnoInt==0){
					maxInt=itemnoInt;					
				}
			}
			maxStr=(maxInt+1)+"";
			if(maxStr.length()==1){
				maxStr="000"+maxStr;
			}
			if(maxStr.length()==2){
				maxStr="00"+maxStr;
			}
			if(maxStr.length()==3){
				maxStr="0"+maxStr;
			}			
		}//end if		
		return maxStr;
	}

	public KyzPetty findById(String factNo, String billNo) {
		// TODO Auto-generated method stub
		return kyzpettydao.findById(factNo, billNo);
	}

	public void delete(String factNo, String billNo) {
		// TODO Auto-generated method stub
		kyzpettydao.delete(factNo, billNo);
	}

	public String makeBillNo(String factNo, String payDate) {
		// TODO Auto-generated method stub
		String itemNo="000";
		int itemNoMax=0;
		String datetime=payDate.substring(2, payDate.length());
		String result="PT"+factNo+datetime;
		List<KyzPetty>list=kyzpettydao.findByFactNoAndPayDate(factNo, payDate);
		if(list.size()>0){//start if
			for(int i=0;i<list.size();i++){
				String billno=list.get(i).getId().getBillNo();
				String strNo=billno.substring(billno.length()-3, billno.length());
				int intNo=Integer.parseInt(strNo);
				if(intNo>itemNoMax||intNo==0){
					itemNoMax=intNo;					
				}				
			}
			itemNo=(itemNoMax+1)+"";
			if(itemNo.length()==1){
				itemNo="00"+itemNo;
			}
			if(itemNo.length()==2){
				itemNo="0"+itemNo;
			}
		}//end if
		result=result+itemNo;		
		return result;
	}

	public List<KyzPetty> findByAnyThing(String factNo, String dateTime,String dateTime2,
			String expenseMk, String taxmMk) {
		// TODO Auto-generated method stub
		return kyzpettydao.findByAnyThing(factNo, dateTime,dateTime2, expenseMk, taxmMk);
	}

}
