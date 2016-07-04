package Web;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import Dao.UserDao;
import Model.User;
import Util.DbUtil;
import Util.NavUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月10日 UserServlet.java
 * @author CZP
 * @parameter
 */

// 实现servletRequestAware接口，为的是取得request请求
public class UserAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;
	private DbUtil dbUtil = new DbUtil();
	private UserDao userDao = new UserDao();
	// 要生成getter/setter方法，然后由struts2的拦截器进行传值
	// 传入的参数名要写成 user.userName user.password
	// 如果传入的参数名为userName password 则要写成并生成setter getter方法
	// 不需要new
	private User user;
	// 验证码（原理同上）
	private String imageCode;
	// 定义传到前台的error字段值
	// 要写成全局变量 并有setter getter 方法
	private String error;
	
	private String password;
	private String userId;
	private String navCode;
	private String mainPage;
	
	public String preSave(){
		navCode=NavUtil.getNav("系统管理", "修改密码");
		mainPage="user/modifyPassword.jsp";
		return SUCCESS;
	}
	
	public String save(){
		Connection conn=null;
		JSONObject jsonObject=new JSONObject();
		try {
			conn=dbUtil.getConn();
			User user=new User();
			user.setPassword(password);
			user.setUserId(Integer.parseInt(userId));
			int num=userDao.modifyPassword(conn, user);
			if(num>0){
				jsonObject.put("success", true);
			}else{
				jsonObject.put("error", "修改失败");
			}
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String logout(){
		HttpSession session=req.getSession();
		session.removeAttribute("currentUser");
		//清除session
		session.invalidate();
		return "logout";
	}
	
	// 获取request请求
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// 将request赋值给req
		this.req = request;
	}

	public String login() {
		HttpSession session = req.getSession();
		String sRand = (String) session.getAttribute("sRand");
		Connection conn = null;
		try {
			// 判断用户名密码
			if (StringUtil.isEmpty(user.getUserName()) || StringUtil.isEmpty(user.getPassword())) {
				error = "用户名或密码不能为空";
				return ERROR;
			}
			// 判断验证码
			if (StringUtil.isEmpty(imageCode)) {
				error = "验证码不能为空";
				return ERROR;
			}
			if (StringUtil.isNotEmpty(imageCode)) {
				if (!imageCode.equals(sRand)) {
					error = "验证码错误";
					return ERROR;
				}
			}
			conn = dbUtil.getConn();
			User currentUser = userDao.login(conn, user);
			if (currentUser == null) {
				error = "用户不存在";
				return ERROR;
			} else if (currentUser != null) {
				session.setAttribute("currentUser", currentUser);
				return SUCCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getImageCode() {
		return imageCode;
	}

	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
