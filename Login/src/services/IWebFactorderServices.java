package services;

import java.util.List;

import entity.WebFactorder;

public interface IWebFactorderServices {
	public void add(WebFactorder order);
	public void addLarge(List<String>list);//大批量添加

}
