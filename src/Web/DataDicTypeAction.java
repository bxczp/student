package Web;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator.Success;

import Dao.DataDicDao;
import Dao.DataDicTypeDao;
import Model.DataDicType;
import Util.DbUtil;
import Util.NavUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月11日 DataDicTypeAction.java
 * @author CZP
 * @parameter
 */
public class DataDicTypeAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;
	private DbUtil dbUtil = new DbUtil();
	private DataDicTypeDao dataDicTypeDao = new DataDicTypeDao();
	// 要传到前台的数据 要new!!!!!!!
	// 要写成全局变量，并有setter getter方法
	private List<DataDicType> dataDicTypeList = new ArrayList<>();
	// 同上
	private String mainPage;
	private String navCode;
	// 由前台传入的数据，不需要new!!!!!!! 但要注意传入的参数名
	private String ddTypeId;
	private DataDicType dataDicType;
	private DataDicDao dataDicDao=new DataDicDao();

	public String delete(){
		Connection conn=null;
		JSONObject jsonObject=new JSONObject();
		try {
			conn=dbUtil.getConn();
			//判断该数据字典Id下是否存在数据字典
			boolean exist=dataDicDao.existDataDicWithTypeId(conn, ddTypeId);
			if(exist){
				//使用ajax向页面传送信息
				jsonObject.put("error", "该类别下存在数据，不能删除");
				
			}else{
				dataDicTypeDao.dataDicTypeDelete(conn, ddTypeId);
				jsonObject.put("success",true);
			}
			ResponseUtil.write(ServletActionContext.getResponse(), jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public DataDicType getDataDicType() {
		return dataDicType;
	}

	public List<DataDicType> getDataDicTypeList() {
		return dataDicTypeList;
	}

	public String getDdTypeId() {
		return ddTypeId;
	}

	public String getMainPage() {
		return mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public String list() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			dataDicTypeList = dataDicTypeDao.dataDicTypeList(conn);
			navCode = NavUtil.getNav("系统管理", "数据字典类别维护");
			mainPage = "dataDicType/dataDicTypeList.jsp";
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


	public String preSave() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(ddTypeId)) {// 更新操作
				dataDicType = dataDicTypeDao.getDataDicTypeById(conn, ddTypeId);
				navCode = NavUtil.getNav("系统管理", "数据字典类别更新");
			} else {// 添加操作
				navCode = NavUtil.getNav("系统管理", "数据字典类别添加");
			}
			mainPage = "dataDicType/dataDicTypeSave.jsp";
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
	
	
	public String save() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(ddTypeId)) {// 更新操作
				dataDicType.setDdTypeId(Integer.parseInt(ddTypeId));
				dataDicTypeDao.dataDicTypeUpdate(conn, dataDicType);
			} else {// 添加操作
				dataDicTypeDao.dataDicTypeAdd(conn, dataDicType);
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
		return "typeSave";
	}

	public void setDataDicType(DataDicType dataDicType) {
		this.dataDicType = dataDicType;
	}

	public void setDataDicTypeList(List<DataDicType> dataDicTypeList) {
		this.dataDicTypeList = dataDicTypeList;
	}

	public void setDdTypeId(String ddTypeId) {
		this.ddTypeId = ddTypeId;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

}
