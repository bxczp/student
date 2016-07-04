package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Model.PageBean;
import Model.Student;
import Util.DateUtil;
import Util.StringUtil;
import Util.UUIDUtil;
import sun.awt.dnd.SunDragSourceContextPeer;

/**
 * @date 2016年3月12日 StudentDao.java
 * @author CZP
 * @parameter
 */
public class StudentDao {

	public boolean existStudentByClassId(Connection conn, String classId) throws Exception {
		String sql = "select * from t_student where classId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setInt(1, Integer.parseInt(classId));
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return true;
		} else {
			return false;
		}
	}

	public List<Student> getStudentList(Connection conn, Student student, PageBean pageBean) throws Exception {
		List<Student> studentList = new ArrayList<>();
		StringBuffer sql = new StringBuffer(
				"select * from t_student t1,t_class t2,t_grade t3 where t1.classId=t2.classId and t2.gradeId=t3.gradeId ");
		if (student.getClassId() != -1) {
			sql.append(" and t2.classId=" + student.getClassId());
		}
		if (student.getGradeId() != -1) {
			sql.append(" and t3.gradeId = " + student.getGradeId());
		}
		if (!"-1".equals(student.getStuSex())) {
			sql.append(" and t1.stuSex = '" + student.getStuSex() + "'");
		}
		if (!"-1".equals(student.getStuZzmm())) {
			sql.append(" and stuZzmm like '%" + student.getStuZzmm() + "%'");
		}
		if (!"-1".equals(student.getStuNation())) {
			sql.append(" and stuNation like '%" + student.getStuNation() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getStuName())) {
			sql.append(" and stuName like '%" + student.getStuName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getStuNo())) {
			sql.append(" and stuNo like '%" + student.getStuNo() + "%'");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getStartBirthday(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuBirthday) >= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getStartBirthday(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getEndBirthday(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuBirthday) <= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getEndBirthday(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getStartRxsj(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuRxsj) >= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getStartRxsj(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getEndRxsj(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuRxsj) <= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getEndRxsj(), "yyyy-MM-dd") + "')");
		}
		if (pageBean != null) {
			sql.append(" limit " + pageBean.getStart() + "," + pageBean.getPageSize() + "");
		}
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		while (rs.next()) {
			Student s = new Student();
			s.setClassId(rs.getInt("classId"));
			s.setClassName(rs.getString("className"));
			s.setGradeId(rs.getInt("gradeId"));
			s.setGradeName(rs.getString("gradeName"));
			s.setStuBirthday(DateUtil.formatStringToDate(rs.getString("stuBirthday"), "yyyy-MM-dd"));
			s.setStudentId(rs.getString("studentId"));
			s.setStuDesc(rs.getString("stuDesc"));
			s.setStuName(rs.getString("stuName"));
			s.setStuNation(rs.getString("stuNation"));
			s.setStuNo(rs.getString("stuNo"));
			s.setStuPic(rs.getString("stuPic"));
			s.setStuRxsj(DateUtil.formatStringToDate(rs.getString("stuRxsj"), "yyyy-MM-dd"));
			s.setStuSex(rs.getString("stuSex"));
			s.setStuZzmm(rs.getString("stuZzmm"));
			s.setStudentId(rs.getString("studentId"));
			studentList.add(s);
		}
		return studentList;
	}

	public int studentListCount(Connection conn, Student student) throws Exception {
		StringBuffer sql = new StringBuffer(
				"select count(*) as total from t_student t1,t_class t2,t_grade t3 where t1.classId=t2.classId and t2.gradeId=t3.gradeId ");
		if (student.getClassId() != -1) {
			sql.append(" and t2.classId=" + student.getClassId());
		}
		if (student.getGradeId() != -1) {
			sql.append(" and t3.gradeId = " + student.getGradeId());
		}
		if (!"-1".equals(student.getStuSex())) {
			sql.append(" and t1.stuSex = '" + student.getStuSex() + "'");
		}
		if (!"-1".equals(student.getStuZzmm())) {
			sql.append(" and stuZzmm like '%" + student.getStuZzmm() + "%'");
		}
		if (!"-1".equals(student.getStuNation())) {
			sql.append(" and stuNation like '%" + student.getStuNation() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getStuName())) {
			sql.append(" and stuName like '%" + student.getStuName() + "%'");
		}
		if (StringUtil.isNotEmpty(student.getStuNo())) {
			sql.append(" and stuNo like '%" + student.getStuNo() + "%'");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getStartBirthday(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuBirthday) >= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getStartBirthday(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getEndBirthday(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuBirthday) <= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getEndBirthday(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getStartRxsj(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuRxsj) >= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getStartRxsj(), "yyyy-MM-dd") + "')");
		}
		if (StringUtil.isNotEmpty(DateUtil.formatDateToString(student.getEndRxsj(), "yyyy-MM-dd"))) {
			sql.append(" and TO_DAYS(t1.stuRxsj) <= TO_DAYS('"
					+ DateUtil.formatDateToString(student.getEndRxsj(), "yyyy-MM-dd") + "')");
		}
		PreparedStatement preparedStatement = conn.prepareStatement(sql.toString());
		ResultSet rs = preparedStatement.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	public Student getStudentById(Connection conn, String studentId) throws Exception {
		String sql = "select *  from t_student t1,t_class t2,t_grade t3 where t1.classId=t2.classId and t2.gradeId=t3.gradeId and studentId=?";
		PreparedStatement preparedStatement = conn.prepareStatement(sql);
		preparedStatement.setString(1, studentId);
		ResultSet rs = preparedStatement.executeQuery();
		Student s = new Student();
		while (rs.next()) {
			s.setClassId(rs.getInt("classId"));
			s.setClassName(rs.getString("className"));
			s.setGradeId(rs.getInt("gradeId"));
			s.setGradeName(rs.getString("gradeName"));
			s.setStuBirthday(DateUtil.formatStringToDate(rs.getString("stuBirthday"), "yyyy-MM-dd"));
			s.setStudentId(rs.getString("studentId"));
			s.setStuDesc(rs.getString("stuDesc"));
			s.setStuName(rs.getString("stuName"));
			s.setStuNation(rs.getString("stuNation"));
			s.setStuNo(rs.getString("stuNo"));
			s.setStuPic(rs.getString("stuPic"));
			s.setStuRxsj(DateUtil.formatStringToDate(rs.getString("stuRxsj"), "yyyy-MM-dd"));
			s.setStuSex(rs.getString("stuSex"));
			s.setStuZzmm(rs.getString("stuZzmm"));
			s.setStudentId(rs.getString("studentId"));
		}
		return s;
	}

	public int studentAdd(Connection conn, Student student) throws Exception {
		String sql = "insert into t_student values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatementconn = conn.prepareStatement(sql);
		// 这次主键也要插入 UUID
		preparedStatementconn.setString(1, UUIDUtil.getUUID());
		preparedStatementconn.setString(2, student.getStuNo());
		preparedStatementconn.setString(3, student.getStuName());
		preparedStatementconn.setString(4, student.getStuSex());
		preparedStatementconn.setString(5, DateUtil.formatDateToString(student.getStuBirthday(), "yyyy-MM-dd"));
		preparedStatementconn.setString(6, DateUtil.formatDateToString(student.getStuRxsj(), "yyyy-MM-dd"));
		preparedStatementconn.setString(7, student.getStuNation());
		preparedStatementconn.setString(8, student.getStuZzmm());
		preparedStatementconn.setInt(9, student.getClassId());
		preparedStatementconn.setString(10, student.getStuDesc());
		preparedStatementconn.setString(11, student.getStuPic());
		return preparedStatementconn.executeUpdate();
	}

	public int studentUpdate(Connection conn, Student student) throws Exception {
		String sql = "update t_student set stuNo=?,stuName=?,stuSex=?,stuBirthday=?,stuRxsj=?,stuNation=?,stuZzmm=?,classId=?,stuDesc=?,stuPic=? where studentId=?";
		PreparedStatement preparedStatementconn = conn.prepareStatement(sql);
		preparedStatementconn.setString(1, student.getStuNo());
		preparedStatementconn.setString(2, student.getStuName());
		preparedStatementconn.setString(3, student.getStuSex());
		preparedStatementconn.setString(4, DateUtil.formatDateToString(student.getStuBirthday(), "yyyy-MM-dd"));
		preparedStatementconn.setString(5, DateUtil.formatDateToString(student.getStuRxsj(), "yyyy-MM-dd"));
		preparedStatementconn.setString(6, student.getStuNation());
		preparedStatementconn.setString(7, student.getStuZzmm());
		preparedStatementconn.setInt(8, student.getClassId());
		preparedStatementconn.setString(9, student.getStuDesc());
		preparedStatementconn.setString(10, student.getStuPic());
		preparedStatementconn.setString(11, student.getStudentId());
		return preparedStatementconn.executeUpdate();
	}

	public int studentDelete(Connection conn, String studentId) throws Exception {
		String sql = "delete from t_student where studentId=?";
		PreparedStatement preparedStatementconn = conn.prepareStatement(sql);
		preparedStatementconn.setString(1, studentId);
		return preparedStatementconn.executeUpdate();
	}

}
