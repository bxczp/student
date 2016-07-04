<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生信息管理系统登录</title>
<script type="text/javascript">
	function checkForm() {
		var ddTypeName = document.getElementById("ddTypeName").value;
		// 		var ddTypeName=$("#ddTypeName").val();
		if (ddTypeName == null || ddTypeName == "") {
// 			$("#error").html("类别名称不能为空");
			document.getElementById("error").innerHTML="类别名称不能为空";
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="data_list">
		<div class="data_content">
			<form action="dataDicType!save" method="post"
				onsubmit="return checkForm()">
				<table class='table table-bordered table-hover'>
					<tr>
						<th>数据字典类别名称：</th>
						<th>数据字典类别备注：</th>
						<th>操作</th>
					</tr>
					<tr>
						<!-- 要使用Struts2的自动封装，name值要使用 对象名.属性名 -->
						<td><input type="text" id="ddTypeName"
							name="dataDicType.ddTypeName" value="${dataDicType.ddTypeName }" /></td>
						<td><input type="text" id="ddTypeDesc"
							name="dataDicType.ddTypeDesc" value="${dataDicType.ddTypeDesc }" /></td>
						<td><input type="hidden" value="${dataDicType.ddTypeId }"
							id="ddTypeId" name="ddTypeId" />
							<button class='btn btn-info btn-mini' Type="submit">添加</button>
							<button type="reset" class='btn btn-mini btn-danger'>重置</button>
							<button class='btn btn-info btn-mini' type="button"
								onclick="javascript:history.back()">返回</button></td>
					</tr>
					<tr>
						<font color="red" id="error">${error }</font>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>