package dao;

import java.util.List;

import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;

public interface IWebEmailDao {
	public List<WebEmail> getEmail(String sendif);

	public List<WebCc> getCC(String sendif);
	
	public List<WebEmailAll>findEmail();
	public List<WebEmailAll>findCC();
}
