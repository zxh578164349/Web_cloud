package action;

import java.util.List;

import services.IViewCostAndEstProServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ViewCostAndEstProAction extends ActionSupport {
	private IViewCostAndEstProServices viewCostSer;

	public void setViewCostSer(IViewCostAndEstProServices viewCostSer) {
		this.viewCostSer = viewCostSer;
	}

	public String findAll() {
		List list = viewCostSer.findAll();
		ActionContext.getContext().getSession().put("viewlist", list);
		return "findAll";
	}

}
