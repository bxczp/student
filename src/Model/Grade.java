package Model;
/**
 *@date 2016年3月11日
 * Grade.java
 *@author CZP
 *@parameter
 */
public class Grade {
	private String gradeDesc;
	//这里 用 int 会出现 0为 默认值的情况，而用Integer 默认值是null
	private Integer gradeId;
	private String gradeName;

	
	public String getGradeDesc() {
		return gradeDesc;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeDesc(String gradeDesc) {
		this.gradeDesc = gradeDesc;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	
}
