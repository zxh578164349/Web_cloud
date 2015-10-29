package dao;

import java.util.List;

import entity.WebSubmenu;

public interface IWebSubmenuDao {
	public WebSubmenu selById(String subName);

	public List<WebSubmenu> selByjurId(int jurid);

	public void delSubmenu(int jurid);

	public void addSubmenu(WebSubmenu submenu);
	
	public List<WebSubmenu>findByFactno(String factNo);
}
