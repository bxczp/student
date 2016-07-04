package Interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

/**
 *@date 2016年3月13日
 * LoginInterceptor.java
 *@author CZP
 *@parameter
 */
public class LoginInterceptor implements Interceptor{

	/**
	 *要在struts.xml文件中配置拦截器
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//可以通过invocation获取上下文
		ActionContext actionContext=invocation.getInvocationContext();
		//获取的session是Map类型的
		Map<String,Object> session=actionContext.getSession();
		Object o=session.get("currentUser");
		if(o!=null){
			//用户已登录，请求继续执行
			return invocation.invoke();
		}else{
			return "login";
		}
	}

}
