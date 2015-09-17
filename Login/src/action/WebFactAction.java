package action;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import services.IKyTypeServices;
import services.IWebFactServices;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import entity.*;

public class WebFactAction extends ActionSupport {
	private IWebFactServices webFactSer;
	private IKyTypeServices kytypeSer;

	public void setWebFactSer(IWebFactServices webFactSer) {
		this.webFactSer = webFactSer;
	}
	

	public void setKytypeSer(IKyTypeServices kytypeSer) {
		this.kytypeSer = kytypeSer;
	}


	/**
	 * �bindex�����W��ܩҦ��t�O
	 * 
	 * @return
	 */
	public String findAllfact() {
		//�h�X�ɡA�M��session���Ҧ����e�]�t�O�A�Τ�W�^�A�]���h�X�ɫ��V�Fjedge.jsp�A�ӳo�ӭ����S���V����k
		ActionContext.getContext().getSession().clear();
		List list = webFactSer.findAllFact();
		//List factCodes = webFactSer.findAllFactCode();// tw�n���ɩҦ����t�O���A
		List factCodes=webFactSer.findAllFactCode_show();
		//List<KyType>listkytype=kytypeSer.findByTypeNo_action("VV");//������O
		ActionContext.getContext().getSession().put("factcodes", factCodes);
		ActionContext.getContext().getSession().put("facts", list);
		//ActionContext.getContext().getSession().put("listkytype", listkytype);
		this.findAllFact_code_no();		
		return "findAllfact";
	}

	/**
	 * �blogin�����W��ܩҦ��t�O
	 * 
	 * @return
	 */
	public String findAllfact2() {
		//�h�X�ɡA�M��session���Ҧ����e�]�t�O�A�Τ�W�^�A�]���h�X�ɫ��V�Fjedge.jsp�A�ӳo�ӭ����S���V����k
		ActionContext.getContext().getSession().clear();
		List list = webFactSer.findAllFact();
		//List factCodes = webFactSer.findAllFactCode();// tw�n���ɩҦ��t�O���A
		List factCodes=webFactSer.findAllFactCode_show();
		//List<KyType>listkytype=kytypeSer.findByTypeNo_action("VV");//������O
		ActionContext.getContext().getSession().put("factcodes", factCodes);
		ActionContext.getContext().getSession().put("facts", list);
		//ActionContext.getContext().getSession().put("listkytype", listkytype);
		this.findAllFact_code_no();
		return "findAllfact2";
	}
	/**
	 * �Τ_"���κA�l�q��"���t�O�P�t�O���A�����
	 */
	public void findAllFact_code_no(){
		Map<String,List<WebFact>>map=new LinkedHashMap<String,List<WebFact>>();
		List<Object[]>list_objs=webFactSer.findAllFactCode2_showA();
		for(int i=0;i<list_objs.size();i++){			
			Object[]obj=list_objs.get(i);
			String factCode=obj[0].toString();
			List<WebFact>list_fact=webFactSer.findFactByFactCode(factCode);						
			map.put(factCode, list_fact);
			
		}
		ActionContext.getContext().getSession().put("map", map);
		//System.out.println(map);
	}
	
	

}
