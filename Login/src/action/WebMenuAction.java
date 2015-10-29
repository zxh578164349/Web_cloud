package action;

import java.util.List;
import services.IWebMenuServices;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.WebMenu;

public class WebMenuAction extends ActionSupport {
	private IWebMenuServices menuSer;
	private WebMenu menu;
	private Integer mid;

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public WebMenu getMenu() {
		return menu;
	}

	public void setMenu(WebMenu menu) {
		this.menu = menu;
	}

	public void setMenuSer(IWebMenuServices menuSer) {
		this.menuSer = menuSer;
	}

	/**
	 * 查找所有菜單名
	 * 
	 * @return
	 */
	public String findAllMenu() {
		List list = menuSer.findAllMenu();
		ActionContext.getContext().getSession().put("menus", list);
		return "findAllMenu";
	}

	/**
	 * 根據ID查找菜單名
	 */
	public String findMenuById() {
		menu = menuSer.findMenuById(mid);
		return "findMenuById";
	}

}
