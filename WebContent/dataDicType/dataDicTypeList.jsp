<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function dataDicTypeDelete(ddTypeId) {
		if(confirm("确认删除？")){
			$.post("dataDicType!delete",{ddTypeId:ddTypeId},function(result){
				//把json串转换成json对象
				var result=eval("("+result+")");
				if(result.success){
					alert("删除成功");
// 					是 conteXt！！！！ 不是conteNt！！
					window.location.href='${pageContext.request.contextPath}/dataDicType!list';
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
			onclick="javascript:window.location='dataDicType!preSave'">添加数据字典类别</button>
	</div>


	<div class="data_content">
		<table class='table table-bordered table-hover'>
			<tr>
				<th>序号：</th>
				<th>数据字典类别名称：</th>
				<th>数据字典类别备注：</th>
				<th>操作</th>
			</tr>
			<c:forEach var="dataDicType" items="${dataDicTypeList }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${dataDicType.ddTypeName }</td>
					<td>${dataDicType.ddTypeDesc }</td>
					<td>
						<button class='btn btn-info btn-mini'
							onclick="javascript:window.location='dataDicType!preSave?ddTypeId=${dataDicType.ddTypeId}'" Type="button">更新</button>
						<button type="button" class='btn btn-mini btn-danger' onclick="dataDicTypeDelete(${dataDicType.ddTypeId})">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>