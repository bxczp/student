package Model;
/**
 *@date 2016年3月11日
 * DataDic.java
 *@author CZP
 *@parameter
 */
public class DataDic {
	
	private int ddId;
	private int ddTypeId=-1;
	private String ddTypeName;
	private String ddValue;
	private String ddDesc;
	public String getDdDesc() {
		return ddDesc;
	}
	public int getDdId() {
		return ddId;
	}
	public int getDdTypeId() {
		return ddTypeId;
	}
	public String getDdTypeName() {
		return ddTypeName;
	}
	public String getDdValue() {
		return ddValue;
	}
	public void setDdDesc(String ddDesc) {
		this.ddDesc = ddDesc;
	}
	public void setDdId(int ddId) {
		this.ddId = ddId;
	}
	public void setDdTypeId(int ddTypeId) {
		this.ddTypeId = ddTypeId;
	}
	public void setDdTypeName(String ddTypeName) {
		this.ddTypeName = ddTypeName;
	}
	public void setDdValue(String ddValue) {
		this.ddValue = ddValue;
	}
	
}
