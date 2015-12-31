package services;

import java.util.List;

import entity.WebFact;

public interface IWebFactServices {
	public List<WebFact> findAllFact();

	public List<WebFact> findFactById(String factNo);
	public List<WebFact> findFactById_showA(String factNo);

	public String selByid(String id);

	public List findAllFactCode();
	public List findAllFactCode_show();

	public List findFactCodeByFactNo(String factNo);
	public List findFactCodeByFactNo_show(String factNo);
	public List findFactCodeByFactNo_show_dw(String factNo);

	public String findByFactNo(String factNo);
	
	public List<WebFact> findAllFact2();
	
	public List<String> findFactNoByFactCode(String factCode);
	
	public List<String> findFactNameByFactCode(String factCode);
	
	public List<WebFact> findFactByFactCode(String factCode);
	public List<WebFact> findFactByFactCode_showA(String factCode);
	
	public List<Object[]>findAllWebFact();
	public List<Object[]>findAllWebFact_showA();
	
	public List<Object[]> findAllFactCode2();
	public List<Object[]> findAllFactCode2_showA();
	public List<String> findAllFactNo();
	
	//20150708 �Ҧ��t�O�U�Ӽt�O���A���ߺD�Ƨ�
	public List<WebFact> findAllFact_2();
	
	public List<WebFact> findFactById_show(String factNo);
	
	/**
	 * 所有的工廠  20151231
	 * @return
	 */
	public List<Object[]> findAllFact_obj();
	
	/**
	 * 所有的的廠別狀態   20151231
	 * @return
	 */
	public List<Object[]> findAllFactarea_obj();
	

}
