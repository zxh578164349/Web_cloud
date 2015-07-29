package services;

import java.util.List;

import util.PageBean;
import entity.KyzAcct;

public interface IKyzAcctServices {
	public void add(KyzAcct kyzacct);
	public PageBean findPageBean(int pageSize,int page,String acctNo,String acctName);
	public KyzAcct findById(String acctNo);
	public void delete(String acctNo);
	public List<KyzAcct> findAll();
	public boolean checkAcctNo(String acctNo);

}
