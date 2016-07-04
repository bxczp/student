package Web;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import Dao.ClazzDao;
import Dao.DataDicDao;
import Dao.DataDicTypeDao;
import Model.DataDic;
import Model.DataDicType;
import Model.PageBean;
import Util.DbUtil;
import Util.NavUtil;
import Util.PageUtil;
import Util.PropertiesUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月12日 DataDicAction.java
 * @author CZP
 * @parameter
 */
public class DataDicAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;
	private DbUtil dbUtil = new DbUtil();
	private DataDicDao dataDicDao = new DataDicDao();
	private DataDicTypeDao dataDicTypeDao = new DataDicTypeDao();
	private List<DataDicType> dataDicTypeList = new ArrayList<>();
	
	

	// 要传到 前台的值 要new
	private List<DataDic> dataDicList = new ArrayList<>();
	private String mainPage;
	private String navCode;
	private String pageCode;

	// 前台传入的字段 不要new 但要注意name值
	// 一定要有setter getter 方法！！！！不然值不能传入！！
	private String s_ddTypeName;
	private DataDic dataDic;
	// 当前页
	private String page;
	private String ddId;

	// 总记录数
	private int total;

	public String preSave() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			// 获取数据字典的所有类别
			dataDicTypeList = dataDicTypeDao.dataDicTypeList(conn);

			if (StringUtil.isNotEmpty(ddId)) {// 更新操作
				dataDic = dataDicDao.getDataDicByDdId(conn, ddId);
				navCode = NavUtil.getNav("系统管理", "数据字典更新");

			} else {// 添加操作
				navCode = NavUtil.getNav("系统管理", "数据字典添加");

			}
			mainPage = "dataDic/dataDicSave.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "success";
	}

	public String dataSave() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(ddId)) {
				dataDic.setDdId(Integer.parseInt(ddId));
				dataDicDao.dataDicUpdate(conn, dataDic);
			} else {
				dataDicDao.dataDicAdd(conn, dataDic);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "dataSave";
	}

	public String delete() {
		Connection conn = null;
		JSONObject jsonObject = new JSONObject();
		int delNum = 0;
		try {
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(ddId)) {
				delNum = dataDicDao.dataDicDelete(conn, ddId);
				if (delNum > 0) {
					jsonObject.put("success", true);
				} else {
					jsonObject.put("error", "删除失败");
				}
			}
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public List<DataDic> getDataDicList() {
		return dataDicList;
	}

	public String getDdId() {
		return ddId;
	}

	public String getMainPage() {
		return mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public String getPage() {
		return page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public String getS_ddTypeName() {
		return s_ddTypeName;
	}

	public int getTotal() {
		return total;
	}

	public String list() {
		Connection conn = null;
		DataDic s_dataDic = new DataDic();
		PageBean pageBean;
		try {
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(s_ddTypeName)) {
				s_dataDic.setDdTypeName(s_ddTypeName);
			}
			if (StringUtil.isEmpty(page)) {
				// 第一次查询page为空，设初值
				page = "1";
			}
			total = dataDicDao.countDataDicList(conn, s_dataDic);
			pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			dataDicList = dataDicDao.dataDicList(conn, s_dataDic, pageBean);
			pageCode = PageUtil.genPagation(req.getContextPath() + "/dataDic!list?s_ddTypeName="+s_ddTypeName, total, Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			navCode = NavUtil.getNav("系统管理", "数据字典维护");
			mainPage = "dataDic/dataDicList.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public void setDataDicList(List<DataDic> dataDicList) {
		this.dataDicList = dataDicList;
	}

	public void setDdId(String ddId) {
		this.ddId = ddId;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public void setS_ddTypeName(String s_ddTypeName) {
		this.s_ddTypeName = s_ddTypeName;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}

	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}

	public DataDic getDataDic() {
		return dataDic;
	}

	public void setDataDic(DataDic dataDic) {
		this.dataDic = dataDic;
	}


}
