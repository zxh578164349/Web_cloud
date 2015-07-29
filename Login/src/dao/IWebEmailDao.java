package dao;

import java.util.List;

import entity.WebCc;
import entity.WebEmail;

public interface IWebEmailDao {
	public List<WebEmail> getEmail(String sendif);

	public List<WebCc> getCC(String sendif);
}
