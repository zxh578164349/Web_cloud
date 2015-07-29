package dao;

import java.util.List;

import entity.WebFact;

public interface IWebFactDao {
	public List<WebFact> findAllFact();

	public List<WebFact> findFactById(String factNo);
	public List<WebFact> findFactById_showA(String factNo);

	public String selByid(String id);

	public List findAllFactCode();
	public List findAllFactCode_show();

	public List findFactCodeByFactNo(String factNo);
	public List findFactCodeByFactNo_show(String factNo);

	public String findByFactNo(String factNo);
	
	public List<String> findFactNoByFactCode(String factCode);
	public List<String> findFactNameByFactCode(String factCode);
	public List<WebFact> findFactByFactCode(String factCode);
	public List<WebFact> findFactByFactCode_showA(String factCode);
	
	public List<Object[]>findAllWebFact();
	public List<Object[]>findAllWebFact_showA();
	
	public List<Object[]>findAllFactCode2();
	public List<Object[]>findAllFactCode2_showA();
	public List<String> findAllFactNo();
	
	//20150708 所有廠別各個廠別狀態的習慣排序
	public List<WebFact> findAllFact_2();

}
