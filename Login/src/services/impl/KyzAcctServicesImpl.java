package services.impl;

import java.util.List;

import dao.IKyzacctDao;
import entity.KyzAcct;
import entity.KyzExpectmatmLog;
import services.IKyzAcctServices;
import util.PageBean;

public class KyzAcctServicesImpl implements IKyzAcctServices{
	private IKyzacctDao kyzacctdao;

	public void setKyzacctdao(IKyzacctDao kyzacctdao) {
		this.kyzacctdao = kyzacctdao;
	}

	public void add(KyzAcct kyzacct) {
		// TODO Auto-generated method stub
		kyzacctdao.add(kyzacct);
	}

	public PageBean findPageBean(int pageSize, int page, String acctNo,String acctName) {
		// TODO Auto-generated method stub
		return kyzacctdao.findPageBean(pageSize, page, acctNo,acctName);
	}

	public KyzAcct findById(String acctNo) {
		// TODO Auto-generated method stub
		return kyzacctdao.findById(acctNo);
	}

	public void delete(String acctNo,KyzExpectmatmLog delLog) {
		// TODO Auto-generated method stub
		kyzacctdao.delete(acctNo,delLog);
	}

	public List<KyzAcct> findAll() {
		// TODO Auto-generated method stub
		return kyzacctdao.findAll();
	}
	public boolean checkAcctNo(String acctNo){
		Boolean result=true;
		List<KyzAcct>list=this.findAll();
		for(int i=0;i<list.size();i++){
			String temp=list.get(i).getAcctNo();
			if(acctNo.equals(temp)){
				result=false;
				break;				
			}
		}
		return result;
	}

}
