package services;

import java.util.List;

import entity.WebSubmenu;

public interface IWebSubmenuService {
	public WebSubmenu selById(String subName);

	public List<WebSubmenu> selByjurId(int jurid);

	public boolean delSubmenu(int jurid);

	public boolean addSubmenu(WebSubmenu submenu);
	
	public List<WebSubmenu>findByFactno(String factNo);
}
