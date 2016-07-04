package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Clazz;

/**
 * @date 2016年3月12日 ClazzDao.java
 * @author CZP
 * @parameter
 */
public class ClazzDao {

	public boolean existClassByGradeId(Connection conn, String gradeId) throws Exception {
		String sql = "select * from t_class where gradeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(gradeId));
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public Clazz getClazzByClazzId(Connection conn, String clazzId) throws Exception {
		StringBuffer sql = new StringBuffer(
				"select * from t_class t1,t_grade t2 where t1.gradeId=t2.gradeId and classId=?");
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		preparedStatement.setInt(1, Integer.parseInt(clazzId));
		ResultSet rs = preparedStatement.executeQuery();
		Clazz clazz = new Clazz();
		while (rs.next()) {
			clazz.setClassDesc(rs.getString("classDesc"));
			clazz.setGradeId(rs.getInt("gradeId"));
			clazz.setClassName(rs.getString("className"));
			clazz.setGradeName(rs.getString("gradeName"));
			clazz.setClassId(rs.getInt("classId"));
		}
		return clazz;
	}

	public List<Clazz> getClazzList(Connection conn, Clazz s_clazz) throws Exception {
		List<Clazz> clazzList = new ArrayList<Clazz>();
		StringBuffer sql = new StringBuffer("select * from t_class t1,t_grade t2 where t1.gradeId=t2.gradeId");
		if (s_clazz != null && s_clazz.getGradeId() != -1) {
			sql.append(" and t1.gradeId =" + s_clazz.getGradeId());
		}
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Clazz clazz = new Clazz();
			clazz.setClassDesc(rs.getString("classDesc"));
			clazz.setGradeId(rs.getInt("gradeId"));
			clazz.setClassName(rs.getString("className"));
			clazz.setGradeName(rs.getString("gradeName"));
			clazz.setClassId(rs.getInt("classId"));
			clazzList.add(clazz);
		}
		return clazzList;
	}

	public int clazzAdd(Connection conn, Clazz clazz) throws Exception {
		String sql = "insert into t_class values(null,?,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, clazz.getClassName());
		preparedStatement.setInt(2, clazz.getGradeId());
		preparedStatement.setString(3, clazz.getClassDesc());
		return preparedStatement.executeUpdate();
	}

	public int clazzUpdate(Connection conn, Clazz clazz) throws Exception {
		String sql = "update t_class set className=?,gradeId=?,classDesc=? where classId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, clazz.getClassName());
		preparedStatement.setInt(2, clazz.getGradeId());
		preparedStatement.setString(3, clazz.getClassDesc());
		preparedStatement.setInt(4, clazz.getClassId());
		return preparedStatement.executeUpdate();
	}

	public int clazzDelete(Connection conn, String classId) throws Exception {
		String sql = "delete from t_class where classId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(classId));
		return preparedStatement.executeUpdate();
	}

}
