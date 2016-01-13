package services;

import java.util.List;

import entity.WebTabpomfile;

public interface IWebTabpomfileServices {
	public WebTabpomfile findById(String pomNo,String fileName);
	public void delete(String pomNo,String fileName);
	public List<WebTabpomfile>findByPomNo(String pomNo);

}
