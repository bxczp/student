<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function dataDicDelete(ddId) {
	if(confirm("确认删除？")){
		$.post("dataDic!delete",{ddId:ddId},function(result){
			//把json串转换成json对象
			var result=eval("("+result+")");
			if(result.success){
				alert("删除成功");
//					是 conteXt！！！！ 不是conteNt！！
				window.location.href='${pageContext.request.contextPath}/dataDic!list';
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
		<form action="${pageContext.request.contextPath}/dataDic!list"
			method="post">
			数据字典类别名称：<input type="text" id="s_ddTypeName" name="s_ddTypeName"
				value="${s_ddTypeName}" /> &nbsp;&nbsp;&nbsp;&nbsp;
			<button class="btn btn-primary" type="submit"
				style="margin-top: -8px;">查询</button>
		</form>
	</div>

	<div class="search_content">
		<button class="btn btn-primary btn-mini" type="button"
			style="float: right; margin-bottom: 15px;"
			onclick="javascript:window.location='dataDic!preSave'">添加数据字典类别</button>
	</div>

	<div class="data_content">
		<table class='table table-bordered table-hover'>
			<tr>
				<th>序号：</th>
				<th>数据字典类别名称：</th>
				<th>数据字典：</th>
				<th>数据字典备注：</th>
				<th>操作</th>
			</tr>
			<c:forEach var="dataDic" items="${dataDicList }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${dataDic.ddTypeName }</td>
					<td>${dataDic.ddValue }</td>
					<td>${dataDic.ddDesc }</td>
					<td>
						<button class='btn btn-info btn-mini'
							onclick="javascript:window.location='dataDic!preSave?ddId=${dataDic.ddId}'"
							Type="button">更新</button>
						<button type="button" class='btn btn-mini btn-danger'
							onclick="dataDicDelete(${dataDic.ddId})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
		<ul>${pageCode }</ul>
	</div>
</div>