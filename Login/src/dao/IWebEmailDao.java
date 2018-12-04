package dao;

import java.util.List;

import util.PageBean;

import entity.KyzExpectmatmLog;
import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;
import entity.WebEmailType;

public interface IWebEmailDao {
	//public List<WebEmail> getEmail(String sendif);

	//public List<WebCc> getCC(String sendif);
	
	public List<WebEmailAll>findEmail(int eid,String emailOrCc);
	//public List<WebEmailAll>findCC(String emailType);
	public List<WebEmailType>findAllEmailTypes();
	
	public void add(WebEmailAll obj);
	public PageBean findPageBean(int page,int pageSize,int eid,String email,String uname,String emailOrcc,String emailMk);
	public WebEmailAll findById(int eid);
	public void delete(int eid,KyzExpectmatmLog log);
	public WebEmailAll findByEmailAndEtypeAndEmailOrCc(String email,int type,String emailOrCc);
}
