package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public interface IKyzVisaFlowServices {
	public void add(KyzVisaflow flow);
	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort);
	public KyzVisaflow findById(KyzVisaflowId id);
	public void delete(KyzVisaflowId id,KyzExpectmatmLog log);
	public void delete(KyzVisaflowId id);
	public List<KyzVisaflow> findByFactNo(String factno);
	public List<KyzVisaflow> findByType(String factNo,String visaSort);
	public int findByType_Dwr(String factNo,String visaSort);
	public String findVisaSort_dwr(String factNo, String visaSort, String email);
	
	public List<String> findVisaSort_C(String factNo,String mainSort);
	public List<KyzVisaflow>findByFactNoVisaSort(String factNo,String visaSort);
	
	public KyzVisaflow findMaxFlow(String factNo,String visaSort);
	
	public void delete2(KyzVisaflow flow);
	
	public String findVisaSigner(String factNo,String visaSort);
	public long findWebbuss(String factNo);//找出是否存在出差函文流程（返回的結果>0,則存在）20160203

}
