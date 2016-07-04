package Util;

/**
 * @date 2016年3月11日 StringUtil.java
 * @author CZP
 * @parameter
 */
public class StringUtil {

	/**
	 * 判断str是否不空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断str是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		if ("".equals(str) || str == null) {
			return true;
		} else {
			return false;
		}
	}

}
