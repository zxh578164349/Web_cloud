/**
 * 
 */
package entity.custom;

/**   
 *    
 * 项目名称：Login   
 * 类名称：ProjectConfig   
 * 类描述：   
 * 创建人：Administrator   
 * 创建时间：2017/2/24 下午3:49:52   
 * 修改人：Administrator   
 * 修改时间：2017/2/24 下午3:49:52   
 * 修改备注：   
 * @version    
 *    
 **/
public class ProjectConfig{
	private String pName;
	private String pHostLocal;
	private String pHostServer;
	private String pHostLoaclB;
	private String pUrl;
	private String pEmail;
	
	
	
	public String getpEmail() {
		return pEmail;
	}
	public void setpEmail(String pEmail) {
		this.pEmail = pEmail;
	}
	public String getpUrl(){
		return pUrl;
	}
	public void setpUrl(String pUrl){
		this.pUrl=pUrl;
	}
	public String getpName(){
		return pName;
	}
	public void setpName(String pName){
		this.pName=pName;
	}
	public String getpHostLocal(){
		return pHostLocal;
	}
	public void setpHostLocal(String pHostLocal){
		this.pHostLocal=pHostLocal;
	}
	public String getpHostServer(){
		return pHostServer;
	}
	public void setpHostServer(String pHostServer){
		this.pHostServer=pHostServer;
	}
	public String getpHostLoaclB(){
		return pHostLoaclB;
	}
	public void setpHostLoaclB(String pHostLoaclB){
		this.pHostLoaclB=pHostLoaclB;
	}
	

}
