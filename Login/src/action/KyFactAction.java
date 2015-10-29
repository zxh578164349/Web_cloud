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
	 * �bindex�����W��ܩҦ��t�O
	 * 
	 * @return
	 */
	public String findAllfact() {
		List list = factSer.findAllKyFact();
		ActionContext.getContext().getSession().put("facts", list);
		return "findAllfact";
	}

	/**
	 * �blogin�����W��ܩҦ��t�O
	 * 
	 * @return
	 */
	public String findAllfact2() {
		List list = factSer.findAllKyFact();
		ActionContext.getContext().getSession().put("facts", list);
		return "findAllfact2";
	}

}
