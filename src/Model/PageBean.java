package Model;
/**
 *@date 2016年3月12日
 * PageBean.java
 *@author CZP
 *@parameter
 */
public class PageBean {
	
	private int page;//当前页
	private int pageSize;//每页的记录数
	private int start;//起始记录数
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStart() {
		return (page-1)*pageSize;
	}
	
	
	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}
	
	
	
	
	
}
