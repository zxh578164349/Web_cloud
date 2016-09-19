package util;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import entity.WebUser;

import services.IWebUserService;

public class SessionListener implements HttpSessionListener {
	private static String names;
	private static String factNos;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getFactNos() {
		return factNos;
	}

	public void setFactNos(String factNos) {
		this.factNos = factNos;
	}

	private static HashMap hUserName = new HashMap();// 保存sessionID和username的映射

	int i = 0;

	/** 以下是实现HttpSessionListener中的方法 **/
	public void sessionCreated(HttpSessionEvent arg0) {
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		hUserName.remove(se.getSession().getId());

	}

	/*
	 * 
	 * isAlreadyEnter-用于判断用户是否已经登录以及相应的处理方法
	 * 
	 * @param sUserName String-登录的用户名称
	 * 
	 * @return boolean-该用户是否已经登录过的标志
	 */
	public static boolean isAlreadyEnter(HttpSession session, String sUserName,
			String factNo) {

		boolean flag = false;

		if (hUserName.containsValue(sUserName + factNo)) {// 如果该用户已经登录过，则使上次登录的用户掉线(依据使用户名是否在hUserName中)

			flag = true;

			// 遍历原来的hUserName，删除原用户名对应的sessionID(即删除原来的sessionID和username)

			Iterator iter = hUserName.entrySet().iterator();

			while (iter.hasNext()) {

				Map.Entry entry = (Map.Entry) iter.next();

				Object key = entry.getKey();

				Object val = entry.getValue();

				if (((String) val).equals(sUserName + factNo)) {

					hUserName.remove(key);
					break;
				}
			}
			hUserName.put(session.getId(), sUserName + factNo);// 添加现在的sessionID和username
		} else {// 如果该用户没登录过，直接添加现在的sessionID和username

			flag = false;

			hUserName.put(session.getId(), sUserName + factNo);
		}
		return flag;

	}

	/*
	 * isOnline-用于判断用户是否在线
	 * 
	 * @param session HttpSession-登录的用户名称
	 * 
	 * @return boolean-该用户是否在线的标志
	 */

	public static void isOnline() throws InterruptedException {
		String flag = null;
		Map<String, Integer> sfdlg = (Map<String, Integer>) ActionContext
				.getContext().getApplication().get("loginCount");
		//重複出現那種情況刪除以下
	/*	if (sfdlg != null && sfdlg.size() == 0 || sfdlg != null
				&& sfdlg.get(names + factNos) == null) {
			Thread.sleep(2000);
			flag = "你嘗試一個瀏覽器同時登陸兩個賬戶,已退出登陸!";
		} else {*/
			//到這里
			int i = 0;
			Map<String, Integer> loginCount = (Map<String, Integer>) ActionContext
					.getContext().getApplication().get("loginCount");
			if (loginCount != null && !loginCount.isEmpty()) {
				for (String key : loginCount.keySet()) {
					if (key.equals(names + factNos)) {
						i = loginCount.get(names + factNos);
					}
				}
			}
			if (i == 2) {
				if (hUserName.containsValue(names + factNos)) {
					loginCount.put(names + factNos, 1);
					ActionContext.getContext().getApplication()
							.put("loginCount", loginCount);
					flag = "你好,賬戶在其他地方登陸,你將退出登陸!";
				}
			}

		//}
		if (flag != null) {
			Json json = new Json();
			try {
				json.writeJson(flag);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}