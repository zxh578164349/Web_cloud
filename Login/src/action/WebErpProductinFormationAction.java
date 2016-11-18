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
	private String itemcategory;
	private JSONArray jsons;
	private List<String> selfchar1s;
	
	
	

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

	public String getItemcategory(){
		return itemcategory;
	}

	public void setItemcategory(String itemcategory){
		this.itemcategory=itemcategory;
	}

	private IWebErpProductinFormationServices weberppfser;

	public void setWeberppfser(IWebErpProductinFormationServices weberppfser){
		this.weberppfser=weberppfser;
	}
		
	public String findNamece(){
		List<Object[]>list=weberppfser.findNamece(itemcategory);
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
	
	
	
	
	
	

}
