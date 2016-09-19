package util;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;

public class Json {
	public void writeJson(String mes) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		org.json.JSONObject json = new org.json.JSONObject();
		json.put("success", mes);
		out.write(json.toString());
		out.flush();
		out.close();
	}
}
