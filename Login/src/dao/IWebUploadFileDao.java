package dao;

import java.util.List;

import entity.KyzExpectmatmLog;
import entity.WebUploadfiles;

public interface IWebUploadFileDao {
	public void add(WebUploadfiles file);
	public List<WebUploadfiles> findByName(String userName,String factNo);
	public boolean delete(int id,KyzExpectmatmLog delLog);
	public WebUploadfiles findById(int id);

}
