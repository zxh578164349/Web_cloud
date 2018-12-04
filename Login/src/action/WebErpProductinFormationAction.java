/**
 * 
 */
package action;

import java.util.List;

import entity.VWebErpProductinFormation;

import net.sf.json.JSONArray;
import services.IWebErpProductinFormationServices;

/**   
 *    
 * 项目名称：Login   
 * 类名称：WebErpProductinFormationAction   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2016/11/1 下午3:10:42   
 * 修改人：Administrator   
 * 修改时间：2016/11/1 下午3:10:42   
 * 修改备注：   
 * @version    
 *    
 **/
public class WebErpProductinFormationAction{
	private String selfchar1;
	private JSONArray jsons;
	private List<String> selfchar1s;
	private String itemcategory;
	
	

	public String getItemcategory(){
		return itemcategory;
	}

	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	public List<String> getSelfchar1s(){
		return selfchar1s;
	}

	public void setSelfchar1s(List<String> selfchar1s){
		this.selfchar1s=selfchar1s;
	}

	public JSONArray getJsons(){
		return jsons;
	}

	public void setJsons(JSONArray jsons){
		this.jsons=jsons;
	}


	public String getSelfchar1() {
		return selfchar1;
	}

	public void setSelfchar1(String selfchar1) {
		this.selfchar1 = selfchar1;
	}



	private IWebErpProductinFormationServices weberppfser;

	public void setWeberppfser(IWebErpProductinFormationServices weberppfser){
		this.weberppfser=weberppfser;
	}
		
	public String findNamece(){
		List<Object[]>list=weberppfser.findNamece(selfchar1);
		jsons=JSONArray.fromObject(list);
		return "findNamece";
	}
	
	public String findTypeNo(){
		List<VWebErpProductinFormation>list=weberppfser.findTypeNo();
		jsons=JSONArray.fromObject(list);
		return "findTypeNo";
	}
	
	public String findNameces(){
		List<Object[]>list=weberppfser.findNamece(selfchar1s);		
		jsons=JSONArray.fromObject(list);			
		return "findNameces";
	}
	
	public String findItemcategory(){
		List<Object[]>list=weberppfser.findItemcategory();
		jsons=JSONArray.fromObject(list);
		return "findItemcategory";
	}
	
	public String findNamece2(){
		List<Object[]>list=weberppfser.findNamece2(itemcategory);
		jsons=JSONArray.fromObject(list);
		return "findNamece2";
	}
	
	
	
	
	
	

}
