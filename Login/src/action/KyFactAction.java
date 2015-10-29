package action;

import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import services.*;

public class KyFactAction extends ActionSupport {
	private IKyFactServices factSer;

	public void setFactSer(IKyFactServices factSer) {
		this.factSer = factSer;
	}

	/**
	 * 在index頁面上顯示所有廠別
	 * 
	 * @return
	 */
	public String findAllfact() {
		List list = factSer.findAllKyFact();
		ActionContext.getContext().getSession().put("facts", list);
		return "findAllfact";
	}

	/**
	 * 在login頁面上顯示所有廠別
	 * 
	 * @return
	 */
	public String findAllfact2() {
		List list = factSer.findAllKyFact();
		ActionContext.getContext().getSession().put("facts", list);
		return "findAllfact2";
	}

}
