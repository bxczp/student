package Model;

import java.util.Date;

/**
 *@date 2016年3月12日
 * Student.java
 *@author CZP
 *@parameter
 */
public class Student {
	private String studentId;
	private String stuNo;
	private String stuName;
	private String stuSex="-1";
	private String stuNation="-1";
	private String stuZzmm="-1";
	private Date stuBirthday;
	private Date stuRxsj;
	private int classId=-1;
	private String stuDesc;
	private String stuPic;
	private int gradeId=-1;
	private String className;
	private String gradeName;
	private Date startBirthday;
	private Date endBirthday;
	private Date startRxsj;
	private Date endRxsj;
	
	
	public Date getStartBirthday() {
		return startBirthday;
	}
	public void setStartBirthday(Date startBirthday) {
		this.startBirthday = startBirthday;
	}
	public Date getEndBirthday() {
		return endBirthday;
	}
	public void setEndBirthday(Date endBirthday) {
		this.endBirthday = endBirthday;
	}
	public Date getStartRxsj() {
		return startRxsj;
	}
	public void setStartRxsj(Date startRxsj) {
		this.startRxsj = startRxsj;
	}
	public Date getEndRxsj() {
		return endRxsj;
	}
	public void setEndRxsj(Date endRxsj) {
		this.endRxsj = endRxsj;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuSex() {
		return stuSex;
	}
	public void setStuSex(String stuSex) {
		this.stuSex = stuSex;
	}
	public String getStuNation() {
		return stuNation;
	}
	public void setStuNation(String stuNation) {
		this.stuNation = stuNation;
	}
	public String getStuZzmm() {
		return stuZzmm;
	}
	public void setStuZzmm(String stuZzmm) {
		this.stuZzmm = stuZzmm;
	}
	public Date getStuBirthday() {
		return stuBirthday;
	}
	public void setStuBirthday(Date stuBirthday) {
		this.stuBirthday = stuBirthday;
	}
	public Date getStuRxsj() {
		return stuRxsj;
	}
	public void setStuRxsj(Date stuRxsj) {
		this.stuRxsj = stuRxsj;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getStuDesc() {
		return stuDesc;
	}
	public void setStuDesc(String stuDesc) {
		this.stuDesc = stuDesc;
	}
	public String getStuPic() {
		return stuPic;
	}
	public void setStuPic(String stuPic) {
		this.stuPic = stuPic;
	}
	public int getGradeId() {
		return gradeId;
	}
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
	
}
