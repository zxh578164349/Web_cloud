package services;

import java.util.List;
import util.PageBean;
import entity.*;

public interface IWebMachineServices {
	// 穝糤
	public void addMachine(WebMachin machine);

	// 沮兵ン琩高计沮
	public List<WebMachin> selMachin(String factNo, String yymm, int page,
			int rows);

	// 琩高场计沮羆计
	public int totlePage(String factNo, String yymm);

	public WebMachin findById(WebMachinId id);

	public List<WebMachin> selByYymm(String yymm, String factNo);

	public void delete(WebMachinId id);
	public PageBean findPageBean(int pageSize,int page,String factNo,String yymm);

}
