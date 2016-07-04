<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function gradeDelete(gradeId) {
	if(confirm("确认删除？")){
		$.post("grade!delete",{gradeId:gradeId},function(result){
			//把json串转换成json对象
			var result=eval("("+result+")");
			if(result.success){
				alert("删除成功");
//					是 conteXt！！！！ 不是conteNt！！
				window.location.href='${pageContext.request.contextPath}/grade!list';
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
		<button class="btn btn-primary btn-mini" type="button" style="float: right; margin-bottom: 15px;"
			onclick="javascript:window.location='grade!preSave'">添加年级</button>
	</div>

	<div class="data_content">
		<table class='table table-bordered table-hover'>
			<tr>
				<th>序号：</th>
				<th>年级名称：</th>
				<th>年级描述：</th>
				<th>操作；</th>
			</tr>
			<c:forEach var="grade" items="${gradeList }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${grade.gradeName }</td>
					<td>${grade.gradeDesc }</td>
					<td>
						<button class='btn btn-info btn-mini' onclick="javascript:window.location='grade!preSave?gradeId=${grade.gradeId}'"
							Type="button">更新</button>
						<button type="button" class='btn btn-mini btn-danger' onclick="gradeDelete(${grade.gradeId})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
		<ul>${pageCode }</ul>
	</div>
</div>