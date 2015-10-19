package dao;

import java.util.List;

import util.PageBean;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public interface IKyzVisaFlowDao {
	public void add(KyzVisaflow flow);
	public PageBean findFixWithPage(int pageSize, int page, String factNo,String visaSort);
	public KyzVisaflow findById(KyzVisaflowId id);
	public void delete(KyzVisaflowId id);
	public List<KyzVisaflow> findByFactNo(String factno);
	public List<KyzVisaflow> findByType(String factNo,String visaSort);
	public String findVisaSort_dwr(String factNo,String visaSort,String email);//查找函文申請時的類別
	
	public List<String> findVisaSort_C(String factNo,String maiSort);
	public List<KyzVisaflow>findByFactNoVisaSort(String factNo,String visaSort);
	
	public KyzVisaflow findMaxFlow(String factNo,String visaSort);
	
	public void delete2(KyzVisaflow flow);
	
}
