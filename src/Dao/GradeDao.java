package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.Grade;

/**
 * @date 2016年3月12日 GradeDao.java
 * @author CZP
 * @parameter
 */
public class GradeDao {

	public List<Grade> getGradeList(Connection conn) throws Exception {
		String sql = "select * from t_grade";
		List<Grade> gradeList = new ArrayList<Grade>();
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Grade grade = new Grade();
			grade.setGradeId(rs.getInt("gradeId"));
			grade.setGradeDesc(rs.getString("gradeDesc"));
			grade.setGradeName(rs.getString("gradeName"));
			gradeList.add(grade);
		}
		return gradeList;
	}

	public int gradeAdd(Connection conn, Grade grade) throws Exception {
		String sql = "insert into t_grade values(null,?,?)";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, grade.getGradeName());
		preparedStatement.setString(2, grade.getGradeDesc());
		return preparedStatement.executeUpdate();
	}

	public int gradeUpade(Connection conn, Grade grade) throws Exception {
		String sql = "update t_grade set gradeName=?,gradeDesc=? where gradeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, grade.getGradeName());
		preparedStatement.setString(2, grade.getGradeDesc());
		preparedStatement.setInt(3, grade.getGradeId());
		return preparedStatement.executeUpdate();
	}

	public Grade getGradeById(Connection conn, String gradeId) throws Exception {
		String sql = "select * from t_grade where gradeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		Grade grade = new Grade();
		preparedStatement.setInt(1, Integer.parseInt(gradeId));
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			grade.setGradeDesc(rs.getString("gradeDesc"));
			grade.setGradeId(rs.getInt("gradeId"));
			grade.setGradeName(rs.getString("gradeName"));
		}
		return grade;
	}

	public int gradeDelete(Connection conn, String gradeId) throws Exception {
		String sql = "delete from t_grade where gradeId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(gradeId));
		return preparedStatement.executeUpdate();
	}

}
