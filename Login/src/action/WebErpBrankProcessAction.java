/**
 * 
 */
package action;

import java.util.List;

import net.sf.json.JSONArray;
import services.IWebErpBrankProcessServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpBrankProcessAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午3:07:11   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午3:07:11   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpBrankProcessAction{
	private IWebErpBrankProcessServices weberpbpser;
	private JSONArray jsons;
	private final static String OP1="OP-1";//所有的廠別狀態（製程代號）
	private final static String OP2="OP-2";//所有的品牌
	

	public JSONArray getJsons(){
		return jsons;
	}

	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}

	public void setWeberpbpser(IWebErpBrankProcessServices weberpbpser){
		this.weberpbpser=weberpbpser;
	}
	
	public String findObjOp1(){
		List<Object[]>list=weberpbpser.findObj(OP1);
		jsons=JSONArray.fromObject(list);
		return "findObjOp1";
	}
	public String findObjOp2(){
		List<Object[]>list=weberpbpser.findObj(OP2);
		jsons=JSONArray.fromObject(list);
		return "findObjOp2";
	}
	

}
