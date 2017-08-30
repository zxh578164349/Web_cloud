package dao;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.KyzVisaflow;
import entity.KyzVisaflowId;

public interface IKyzVisaFlowDao {
	public void add(KyzVisaflow flow);
	public PageBean findFixWithPage(int pageSize, int page, String factNo,String visaSort,String trMk);
	public KyzVisaflow findById(KyzVisaflowId id);
	public void delete(KyzVisaflowId id,KyzExpectmatmLog log);
	public void delete(KyzVisaflowId id);
	public List<KyzVisaflow> findByFactNo(String factno);
	public List<KyzVisaflow> findByType(String factNo,String visaSort);
	public String findVisaSort_dwr(String factNo,String visaSort,String email);
	public String findVisaSort_dwr(String factNo,String visaSort,String email,String trMk);
	public List<Object[]> findVisaSort_dwr2(String factNo,String visaSort,String email,String trMk);
	public String findVisaSort_dwr2(String factNo,String visaSort,String email,String trMk,String depId);
	public List<String> findVisaSort_dwr3(String factNo, String visaSort, String email,String trMk);
	public List<String> findVisaSort_C(String factNo,String maiSort);
	public List<KyzVisaflow>findByFactNoVisaSort(String factNo,String visaSort);
	
	public KyzVisaflow findMaxFlow(String factNo,String visaSort);
	
	public void delete2(KyzVisaflow flow);
	
	public String findVisaSigner(String factNo,String visaSort);//根據廠別，類別找出申請人
	
	public long findWebbuss(String factNo);//找出是否存在出差函文流程（返回的結果>0,則存在）20160203
	
	public Long findNums(String factNo,String visaSort);
	
	public List<KyzVisaflow>findTR(String factNo);//出差流程
	public List<KyzVisaflow>findPF(String factNo);//配方流程
	
	/**
	 * 
	 * @Title: add_d
	 * @Description: 可以修改主鍵
	 * @param @param f1
	 * @param @param f2
	 * @return void
	 * @throws
	 * @author web
	 * @date 2017/5/10
	 */
	public void add_d(KyzVisaflow f1,KyzVisaflow f2);
	
}
