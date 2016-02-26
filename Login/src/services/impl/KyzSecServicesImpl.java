package services.impl;

import java.util.ArrayList;
import java.util.List;

import dao.IKyzSecDao;
import entity.KyzExpectmatmLog;
import entity.KyzSec;
import services.IKyzSecServices;
import util.PageBean;

public class KyzSecServicesImpl implements IKyzSecServices{
	private IKyzSecDao kyzsecdao;

	public void setKyzsecdao(IKyzSecDao kyzsecdao) {
		this.kyzsecdao = kyzsecdao;
	}

	public void add(KyzSec kyzsec) {
		// TODO Auto-generated method stub
		kyzsecdao.add(kyzsec);
	}

	public PageBean findPageBean(int pageSize, int page, String factNo,
			String secNo) {
		// TODO Auto-generated method stub
		return kyzsecdao.findPageBean(pageSize, page, factNo, secNo);
	}

	public List<KyzSec> findByFactno(String factNo) {
		// TODO Auto-generated method stub
		return kyzsecdao.findByFactno(factNo);
	}
	public String makeSecNo(String factNo){
		List<KyzSec>list=this.findByFactno(factNo);
		List<String>list_str=new ArrayList();
		int numMax=0;
		String result="";
		for(int i=0;i<list.size();i++){
			String secno=list.get(i).getId().getSecNo().trim();
			if(secno.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")&&secno.length()==4){
				list_str.add(secno);
			}
		}
		if(list_str.size()>0){//start if
			for(int j=0;j<list_str.size();j++){
				int temp=Integer.parseInt(list_str.get(j));
				if(temp>numMax){
					numMax=temp;
				}
			}
			if((numMax+1)<10){
				result="000"+(numMax+1);
			}else if((numMax+1)<100){
				result="00"+(numMax+1);
			}else if((numMax+1)<1000){
				result="0"+(numMax+1);
			}else{
				result=(numMax+1)+"";
			}
		}//end if
		if(list_str.size()==0){
			result="0000";
		}
		return result;
	}

	public KyzSec findById(String factNo, String secNo) {
		// TODO Auto-generated method stub
		return kyzsecdao.findById(factNo, secNo);
	}

	public void delete(String factNo, String secNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		kyzsecdao.delete(factNo, secNo,delLog);
	}

}
