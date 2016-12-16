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
	 * �d��Ҧ����W
	 * 
	 * @return
	 */
	public String findAllMenu() {
		List list = menuSer.findAllMenu("0");
		ActionContext.getContext().getSession().put("menus", list);
		return "findAllMenu";
	}

	/**
	 * �ھ�ID�d����W
	 */
	public String findMenuById() {
		menu = menuSer.findMenuById(mid);
		return "findMenuById";
	}

}
