package services;

import java.util.List;

import entity.KyzExpectmatmLog;
import entity.WebTabpomfile;

public interface IWebTabpomfileServices {
	public WebTabpomfile findById(String pomNo,String fileName);
	public void delete(String pomNo,String fileName,KyzExpectmatmLog log);
	public List<Object[]>findByPomNo(String pomNo);
	public void add(WebTabpomfile file);

}
