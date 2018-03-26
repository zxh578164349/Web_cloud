package services;

import java.util.List;

import entity.WebCc;
import entity.WebEmail;
import entity.WebEmailAll;

public interface IWebEmailService {
	public List<WebEmail> getEmail(String sendif);

	public List<WebCc> getCC(String sendif);
	public List<WebEmailAll>findEmail(String emailType);
	public List<WebEmailAll>findCC(String emailType);
}
