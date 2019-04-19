package dao;

import entity.WebMonths;

public interface IWebmonthsDao {
	public void addWebmonths(WebMonths obj);
	public WebMonths findWebmonths(String yymm,String objType);

}
