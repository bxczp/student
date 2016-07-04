package Web;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;

import Dao.ClazzDao;
import Dao.DataDicDao;
import Dao.GradeDao;
import Dao.StudentDao;
import Model.Clazz;
import Model.DataDic;
import Model.Grade;
import Model.PageBean;
import Model.Student;
import Util.DateUtil;
import Util.DbUtil;
import Util.NavUtil;
import Util.PageUtil;
import Util.PropertiesUtil;
import Util.ResponseUtil;
import Util.StringUtil;
import net.sf.json.JSONObject;

/**
 * @date 2016年3月13日 StudentAction.java
 * @author CZP
 * @parameter
 */
public class StudentAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DbUtil dbUtil = new DbUtil();
	private StudentDao studentDao = new StudentDao();
	private List<Student> studentList = new ArrayList<>();
	private HttpServletRequest req;
	private String navCode;
	private String mainPage;
	private Student student = new Student();
	private String studentId;
	private List<Clazz> s_clazzList = new ArrayList<>();
	private List<Grade> s_gradeList = new ArrayList<>();
	private List<Clazz> clazzList = new ArrayList<>();
	private List<Grade> gradeList = new ArrayList<>();
	private Student s_student;
	private List<DataDic> s_sexDataDicList = new ArrayList<>();
	private List<DataDic> s_nationDataDicList = new ArrayList<>();
	private List<DataDic> sexDataDicList = new ArrayList<>();
	private List<DataDic> nationDataDicList = new ArrayList<>();
	private List<DataDic> zzmmDataDicList = new ArrayList<>();
	private DataDicDao dataDicDao = new DataDicDao();
	private GradeDao gradeDao = new GradeDao();
	private ClazzDao clazzDao = new ClazzDao();
	private String page;
	private String pageCode;
	private int total;
	private HttpSession session;
	private File stuPic;
	private String stuPicFileName;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.req = request;
	}

	public String list() {
		session = req.getSession();
		if (s_student != null) {
			session.setAttribute("s_student", s_student);
		} else {
			Object o = session.getAttribute("s_student");
			if (o != null) {
				s_student = (Student) o;
			} else {
				s_student = new Student();
			}
		}
		Connection conn = null;
		if (StringUtil.isEmpty(page)) {
			page = "1";
		}
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(PropertiesUtil.getValue("pageSize")));
		try {
			conn = dbUtil.getConn();
			studentList = studentDao.getStudentList(conn, s_student, pageBean);
			navCode = NavUtil.getNav("系统管理", "学生信息维护");
			setTotal(studentDao.studentListCount(conn, s_student));
			pageCode = PageUtil.genPagation(req.getContextPath() + "/student!list?", getTotal(), Integer.parseInt(page),
					Integer.parseInt(PropertiesUtil.getValue("pageSize")));
			DataDic s_dataDic = new DataDic();
			s_dataDic.setDdTypeName("性别");
			s_sexDataDicList = dataDicDao.dataDicList(conn, s_dataDic, null);

			s_dataDic.setDdTypeName("名族");
			s_nationDataDicList = dataDicDao.dataDicList(conn, s_dataDic, null);

			// 二级联动 根据年级确定 班级
			s_gradeList = gradeDao.getGradeList(conn);
			if (s_student != null && s_student.getGradeId() != -1) {
				Clazz c = new Clazz();
				c.setGradeId(s_student.getGradeId());
				s_clazzList = clazzDao.getClazzList(conn, c);
			}
			mainPage = "student/studentList.jsp";
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
			if (StringUtil.isNotEmpty(studentId)) {
				navCode = NavUtil.getNav("系统管理", "学生信息更新");
				student = studentDao.getStudentById(conn, studentId);
			} else {
				navCode = NavUtil.getNav("系统管理", "学生信息添加");
			}
			DataDic s_dataDic = new DataDic();
			s_dataDic.setDdTypeName("性别");
			sexDataDicList = dataDicDao.dataDicList(conn, s_dataDic, null);
			s_dataDic.setDdTypeName("名族");
			nationDataDicList = dataDicDao.dataDicList(conn, s_dataDic, null);
			s_dataDic.setDdTypeName("政治");
			zzmmDataDicList = dataDicDao.dataDicList(conn, s_dataDic, null);
			gradeList = gradeDao.getGradeList(conn);
			clazzList = clazzDao.getClazzList(conn, null);
			mainPage = "student/studentSave.jsp";
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

	public String show() {
		Connection conn = null;
		try {
			conn = dbUtil.getConn();
			student = studentDao.getStudentById(conn, studentId);
			navCode = NavUtil.getNav("系统管理", "学生信息");
			mainPage = "student/studentShow.jsp";
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
		// Struts可以 自动完成 字符串类型的日期 转换成 Date类型的日期
		// 而 springmvc 不能自动转换
		Connection conn = null;
		try {
			if (stuPic != null) {
				// 文件名
				String imageName = DateUtil.getCurrentDateToString();
				// 获取文件夹userImage在项目中路径
				String realPath = ServletActionContext.getServletContext().getRealPath("/userImage");
				// 完整的文件名（有后缀）
				String imageFile = imageName + "." + stuPicFileName.split("\\.")[1];
				// 指定了 路径 和 文件名
				File saveFile = new File(realPath, imageFile);
				// 使用自带的
				FileUtils.copyFile(stuPic, saveFile);
				student.setStuPic(imageFile);
			} else {// 用户没有上传图片
				if (StringUtil.isEmpty(student.getStuPic())) {
					student.setStuPic("");
				}
			}
			conn = dbUtil.getConn();
			if (StringUtil.isNotEmpty(studentId)) {
				student.setStudentId(studentId);
				studentDao.studentUpdate(conn, student);
			} else {
				studentDao.studentAdd(conn, student);
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
		return "studentSave";
	}

	public String delete() {
		Connection conn = null;
		JSONObject jsonObject = new JSONObject();
		int delNum = 0;
		try {
			conn = dbUtil.getConn();
			delNum = studentDao.studentDelete(conn, studentId);
			if (delNum > 0) {
				jsonObject.put("success", true);
			} else {
				jsonObject.put("error", "删除失败");
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

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public Student getS_student() {
		return s_student;
	}

	public void setS_student(Student s_student) {
		this.s_student = s_student;
	}

	public List<Grade> getS_gradeList() {
		return s_gradeList;
	}

	public void setS_gradeList(List<Grade> s_gradeList) {
		this.s_gradeList = s_gradeList;
	}

	public List<DataDic> getS_sexDataDicList() {
		return s_sexDataDicList;
	}

	public void setS_sexDataDicList(List<DataDic> s_sexDataDicList) {
		this.s_sexDataDicList = s_sexDataDicList;
	}

	public List<DataDic> getS_nationDataDicList() {
		return s_nationDataDicList;
	}

	public void setS_nationDataDicList(List<DataDic> s_nationDataDicList) {
		this.s_nationDataDicList = s_nationDataDicList;
	}

	public List<Clazz> getS_clazzList() {
		return s_clazzList;
	}

	public void setS_clazzList(List<Clazz> s_clazzList) {
		this.s_clazzList = s_clazzList;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<DataDic> getSexDataDicList() {
		return sexDataDicList;
	}

	public void setSexDataDicList(List<DataDic> sexDataDicList) {
		this.sexDataDicList = sexDataDicList;
	}

	public List<DataDic> getNationDataDicList() {
		return nationDataDicList;
	}

	public void setNationDataDicList(List<DataDic> nationDataDicList) {
		this.nationDataDicList = nationDataDicList;
	}

	public List<Clazz> getClazzList() {
		return clazzList;
	}

	public void setClazzList(List<Clazz> clazzList) {
		this.clazzList = clazzList;
	}

	public List<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(List<Grade> gradeList) {
		this.gradeList = gradeList;
	}

	public List<DataDic> getZzmmDataDicList() {
		return zzmmDataDicList;
	}

	public void setZzmmDataDicList(List<DataDic> zzmmDataDicList) {
		this.zzmmDataDicList = zzmmDataDicList;
	}

	public File getStuPic() {
		return stuPic;
	}

	public void setStuPic(File stuPic) {
		this.stuPic = stuPic;
	}

	public String getStuPicFileName() {
		return stuPicFileName;
	}

	public void setStuPicFileName(String stuPicFileName) {
		this.stuPicFileName = stuPicFileName;
	}

}
