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
import Model.Grade;
import Util.DbUtil;
import Util.NavUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月12日 GradeAction.java
 * @author CZP
 * @parameter
 */
public class GradeAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	
//	action类中变量 都应有setter getter方法
	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil = new DbUtil();
	private Grade grade = new Grade();
	private GradeDao gradeDao = new GradeDao();
	private List<Grade> gradeList = new ArrayList<Grade>();
	private HttpServletRequest req;
	private String mainPage;
	private String navCode;
	private String gradeId;
	private ClazzDao clazzDao=new ClazzDao();

	public Grade getGrade() {
		return grade;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public String list() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			gradeList = gradeDao.getGradeList(conn);
			setNavCode(NavUtil.getNav("系统管理", "年级维护"));
			setMainPage("grade/gradeList.jsp");
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
		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			if(StringUtil.isNotEmpty(gradeId)){
				navCode=NavUtil.getNav("系统管理", "年级更新");
				grade=gradeDao.getGradeById(conn, gradeId);
			}else{
				navCode=NavUtil.getNav("系统管理", "年级添加");
			}
			mainPage="grade/gradeSave.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public String save() {
      		Connection conn=null;
		try {
			conn=dbUtil.getConn();
			if(StringUtil.isNotEmpty(gradeId)){
				grade.setGradeId(Integer.parseInt(gradeId));
				gradeDao.gradeUpade(conn, grade);
			}else{
				gradeDao.gradeAdd(conn, grade);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeConn(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "gradeSave";
	}
	
	public String delete(){
		Connection conn=null;
		JSONObject jsonObject=new JSONObject();
		int delNum=0;
		try {
			conn=dbUtil.getConn();
			if(StringUtil.isNotEmpty(gradeId)){
				if(clazzDao.existClassByGradeId(conn, gradeId)){
					jsonObject.put("error", "该年级下存在班级，不能删除");
				}else{
					delNum=gradeDao.gradeDelete(conn, gradeId);
					if(delNum>0){
						jsonObject.put("success", true);
					}else{
						jsonObject.put("error", "删除失败");
					}
				}
			}else{
				
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
		return "success";
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getGradeId() {
		return gradeId;
	}

	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}

}
