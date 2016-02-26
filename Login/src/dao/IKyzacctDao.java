package dao;

import java.util.List;

import util.PageBean;
import entity.KyzAcct;
import entity.KyzExpectmatmLog;

public interface IKyzacctDao {
	public void add(KyzAcct kyzacct);
	public PageBean findPageBean(int pageSize,int page,String acctNo,String acctName);
	public KyzAcct findById(String acctNo);
	public void delete(String acctNo,KyzExpectmatmLog delLog);
	public List<KyzAcct> findAll();

}
