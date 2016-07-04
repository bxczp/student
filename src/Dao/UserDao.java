package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Model.User;

/**
 * @date 2016年3月11日 UserDao.java
 * @author CZP
 * @parameter
 */
public class UserDao {
	public int modifyPassword(Connection conn, User user) throws Exception {
		String sql = "update t_user set password=? where userId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, user.getPassword());
		preparedStatement.setInt(2, user.getUserId());
		return preparedStatement.executeUpdate();
	}

	public User login(Connection conn, User user) throws Exception {
		String sql = "select * from t_user where userName=? and password=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, user.getUserName());
		preparedStatement.setString(2, user.getPassword());
		ResultSet rs = preparedStatement.executeQuery();
		// 不要这么写 要在rs有值的情况下进行new
		// User currentUser=new User();
		User currentUser = null;
		while (rs.next()) {
			currentUser = new User();
			currentUser.setPassword(rs.getString("password"));
			currentUser.setUserId(rs.getInt("userId"));
			currentUser.setUserName(rs.getString("userName"));
		}
		return currentUser;
	}

}
