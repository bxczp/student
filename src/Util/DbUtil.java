package Util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @date 2016年3月10日 DbUtil.java
 * @author CZP
 * @parameter
 */
public class DbUtil {
	private String jdbcName = PropertiesUtil.getValue("jdbcName");
	private String url = PropertiesUtil.getValue("url");
	private String userName = PropertiesUtil.getValue("userName");
	private String password = PropertiesUtil.getValue("password");

	public Connection getConn() throws Exception {
		Class.forName(jdbcName);
		Connection conn = DriverManager.getConnection(url, userName, password);
		return conn;
	}

	public void closeConn(Connection conn) throws Exception {
		if (conn != null) {
			conn.close();
		}
	}

	public static void main(String[] args) {
		DbUtil dbUtil = new DbUtil();
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("success");
	}
}
