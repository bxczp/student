<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统登录</title>
<script type="text/javascript">
	function checkForm ()
    {
	    var gradeId = document.getElementById ("gradeId").value;
	    var className = $ ("#className").val ();
	    if (gradeId == null || gradeId == "")
	    {
		    document.getElementById ("error").innerHTML = "年级不能为空";
		    return false;
	    }
	    if (className == null || className == "")
	    {
		    $ ("#error").html ("班级不能为空");
		    return false;
	    }
	    return true;
    }
</script>
</head>
<body>
	<div class="data_list">
		<div class="data_content">
			<form action="clazz!save" method="post" onsubmit="return checkForm()">
				<table class='table table-bordered table-hover'>
					<tr>
						<th>所在年纪：</th>
						<th>班级名称：</th>
						<th>班级备注：</th>
						<th>操作：</th>
					</tr>
					<tr>

						<!-- 要使用Struts2的自动封装，name值要使用 对象名.属性名 -->
						<td><select id="gradeId" name="clazz.gradeId">
								<option value=''>选择年级</option>
								<c:forEach var="grade" items="${gradeList }">
									<!-- 注意 EL 表达式中 不要加分号； -->
									<option value="${grade.gradeId }" ${grade.gradeId==clazz.gradeId?'selected':'' }>${grade.gradeName }</option>
								</c:forEach>
							</select></td>
						<td><input type="text" id="className" name="clazz.className" value="${clazz.className }" /></td>
						<td><input type="text" id="classDesc" name="clazz.classDesc" value="${clazz.classDesc }" /></td>
						<td><input type="hidden" value="${clazz.classId }" id="classId" name="classId" />
							<button class='btn btn-info btn-mini' Type="submit">添加</button>
							<button type="reset" class='btn btn-mini btn-danger'>重置</button>
							<button class='btn btn-info btn-mini' type="button" onclick="javascript:history.back()">返回</button></td>
					</tr>
					<tr>
						<font color="red" id="error">${error }</font>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>