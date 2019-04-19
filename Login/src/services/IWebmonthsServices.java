package services;

import entity.WebMonths;

public interface IWebmonthsServices {
	public void addWebmonths(WebMonths obj);
	public WebMonths findWebmonths(String yymm,String objType);

}
