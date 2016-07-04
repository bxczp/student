package Util;

/**
 * @date 2016年3月11日 NavUtil.java
 * @author CZP
 * @parameter
 */
public class NavUtil {
	public static String getNav(String mainMenu, String subMenu) {
		StringBuffer sb = new StringBuffer();
		sb.append("当前位置：&nbsp;&nbsp;");
		sb.append("&nbsp;&nbsp;<a href='main.jsp'>主页</a>&nbsp;&nbsp;>>");
		sb.append("&nbsp;&nbsp;<a href='#'>" + mainMenu + "</a>&nbsp;&nbsp;>>");
		sb.append("&nbsp;&nbsp;<a href='#'>" + subMenu + "</a>");
		return sb.toString();
	}
}
