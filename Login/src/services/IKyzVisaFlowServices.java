package services;

import java.util.List;

import util.PageBean;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public interface IKyzVisaFlowServices {
	public void add(KyzVisaflow flow);
	public PageBean findPageBean(int pageSize, int page, String factNo,String visaSort);
	public KyzVisaflow findById(KyzVisaflowId id);
	public void delete(KyzVisaflowId id);
	public List<KyzVisaflow> findByFactNo(String factno);
	public List<KyzVisaflow> findByType(String factNo,String visaSort);
	public int findByType_Dwr(String factNo,String visaSort);
	public String findVisaSort_dwr(String factNo, String visaSort, String email);
	
	public List<String> findVisaSort_C(String factNo,String mainSort);

}
