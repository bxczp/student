package Web;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import Dao.ClazzDao;
import Dao.GradeDao;
import Dao.StudentDao;
import Model.Clazz;
import Model.Grade;
import Util.DbUtil;
import Util.NavUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月12日 ClazzAction.java
 * @author CZP
 * @parameter
 */
public class ClazzAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest req;
	private DbUtil dbUtil = new DbUtil();
	private ClazzDao clazzDao = new ClazzDao();
	private List<Clazz> clazzList = new ArrayList<Clazz>();
	private String navCode;
	private String mainPage;
	private String classId;
	private Clazz clazz = new Clazz();
	private StudentDao studentDao = new StudentDao();
	private GradeDao gradeDao = new GradeDao();
	private String s_gradeId;
	// 年级的下拉框
	// 前台需要什么，就必须要在action有相应变量
	// 而且要在相应的方法中获取变量的实例
	private List<Grade> gradeList = new ArrayList<>();

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	public String list() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			clazzList = clazzDao.getClazzList(conn, null);
			navCode = NavUtil.getNav("系统管理", "班级信息维护");
			mainPage = "clazz/clazzList.jsp";
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

	public String getClassByGradeId() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			Clazz clazz = new Clazz();
			clazz.setGradeId(Integer.parseInt(getS_gradeId()));
			List<Clazz> clazzList=clazzDao.getClazzList(conn, clazz);
			//把list转换为jsonArray
			JSONArray jsonArray=JSONArray.fromObject(clazzList);
			ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String preSave() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			gradeList = gradeDao.getGradeList(conn);
			if (StringUtil.isNotEmpty(classId)) {
				clazz = clazzDao.getClazzByClazzId(conn, classId);
				navCode = NavUtil.getNav("系统管理", "班级信息更新");
			} else {
				navCode = NavUtil.getNav("系统管理", "班级信息添加");
			}
			mainPage = "clazz/clazzSave.jsp";
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
			if (StringUtil.isNotEmpty(classId)) {
				clazz.setClassId(Integer.parseInt(classId));
				clazzDao.clazzUpdate(conn, clazz);
			} else {
				clazzDao.clazzAdd(conn, clazz);
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
		return "clazzSave";
	}

	public String delete() {
		Connection conn = null;
		JSONObject jsonObject = new JSONObject();
		try {
			conn = dbUtil.getConn();
			if (studentDao.existStudentByClassId(conn, classId)) {
				jsonObject.put("error", "该班级下有学生，不能删除");
			} else {
				clazzDao.clazzDelete(conn, classId);
				jsonObject.put("success", true);
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

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public String getS_gradeId() {
		return s_gradeId;
	}

	public void setS_gradeId(String s_gradeId) {
		this.s_gradeId = s_gradeId;
	}

}
