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
		var ddTypeId = document.getElementById("ddTypeId").value;
		var ddValue = $("#ddValue").val();
		if (ddTypeId == null || ddTypeId == "") {
			document.getElementById("error").innerHTML = "类别名称不能为空";
			return false;
		}
		if (ddValue == null || ddValue == "") {
			$("#error").html("内容不能为空");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div class="data_list">
		<div class="data_content">
			<form action="dataDic!dataSave" method="post"
				onsubmit="return checkForm()">
				<table class='table table-bordered table-hover'>
					<tr>
						<th>数据字典类别名称：</th>
						<th>数据字典内容：</th>
						<th>数据字典备注：</th>
						<th>操作</th>
					</tr>
					<tr>
						<!-- 要使用Struts2的自动封装，name值要使用 对象名.属性名 -->
						<td><select id="ddTypeId"
							name="dataDic.ddTypeId">
							<option value=''>选择数据字典类别</option>
							<c:forEach var="dataDicType" items="${dataDicTypeList }">
									<!-- 注意 EL 表达式中 不要加分号； -->
									<option value="${dataDicType.ddTypeId }" ${dataDicType.ddTypeId==dataDic.ddTypeId?'selected':'' }>${dataDicType.ddTypeName }</option>
							</c:forEach>
							</select></td>
						<td><input type="text" id="ddValue" name="dataDic.ddValue"
							value="${dataDic.ddValue }" /></td>
						<td><input type="text" id="ddDesc" name="dataDic.ddDesc"
							value="${dataDic.ddDesc }" /></td>
						<td><input type="hidden" value="${dataDic.ddId }"
							id="ddId" name="ddId" />
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