package services;

import java.util.List;

import util.PageBean;
import entity.KyzExpectmatmLog;
import entity.WebType;

public interface IWebTypeServices {
	public void add(WebType type);
	public PageBean findPageBean(int page,int pageSize,String factNo);
	public WebType findById(String factNo,String typeNo);
	public void delete(String factNo,String typeNo,KyzExpectmatmLog delLog);
	public List<WebType>findByFactNo(String factNo);//無過濾出差類
	public List<WebType>findByFactNo3(String factNo);//有過濾出差類20160203
	
	public String findTypeNameById(String factNo,String typeNo);
	public List<WebType>findByFactNo2(String factNo);
	public void addToUpdate(WebType type);
	public List<Object[]>findTypes(String factNo);
	
	/**
	 * 
	 * @Title: findPF
	 * @Description: 查找配方簽核類
	 * @param @param factNo
	 * @param @return
	 * @return String
	 * @throws
	 * @author web
	 * @date 2016/11/16
	 */
	public String findPF(String factNo);//查找配方簽核類

}
