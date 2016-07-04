<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<script type="text/javascript">
function clazzDelete(classId) {
	if(confirm("确认删除？")){
		$.post("clazz!delete",{classId:classId},function(result){
			//把json串转换成json对象
			var result=eval("("+result+")");
			if(result.success){
				alert("删除成功");
//是 conteXt！！！！ 不是conteNt！！
				window.location.href='${pageContext.request.contextPath}/clazz!list';
			}
			if(result.error){
				alert(result.error);
			}
		});
	}
}
</script>
<div class='data_list'>

	<div class="search_content">
		<button class="btn btn-primary btn-mini" type="button"
			style="float: right; margin-bottom: 15px;"
			onclick="javascript:window.location='clazz!preSave'">添加班级</button>
	</div>

	<div class="data_content">
		<table class='table table-bordered table-hover'>
			<tr>
				<th>序号：</th>
				<th>所在年级：</th>
				<th>班级名称：</th>
				<th>班级描述：</th>
				<th>操作：</th>
			</tr>
			<c:forEach var="clazz" items="${clazzList }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${clazz.gradeName }</td>
					<td>${clazz.className }</td>
					<td>${clazz.classDesc }</td>
					<td>
						<button class='btn btn-info btn-mini'
							onclick="javascript:window.location='clazz!preSave?classId=${clazz.classId}'"
							Type="button">更新</button>
						<button type="button" class='btn btn-mini btn-danger'
							onclick="clazzDelete(${clazz.classId})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
		<ul>${pageCode }</ul>
	</div>
</div>