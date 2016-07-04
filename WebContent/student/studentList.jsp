<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script type="text/javascript">
	function studentDelete (studentId)
    {
	    if (confirm ("确认删除？"))
	    {
		    $.post ("student!delete",
		    {
			    studentId : studentId
		    }, function (result)
		    {
			    //把json串转换成json对象
			    var result = eval ("(" + result + ")");
			    if (result.success)
			    {
				    alert ("删除成功");
				    //					是 conteXt！！！！ 不是conteNt！！
				    window.location.href = '${pageContext.request.contextPath}/student!list';
			    }
			    if (result.error)
			    {
				    alert (result.error);
			    }
		    });
	    }
    }

    function loadClassInfo ()
    {
	    var s_gradeId = $ ("#s_gradeId").val ();
	    // 	把value！=-1的项除去（除去前面 操作 留下来的内容）
	    // 属性要用 [ ] 引起来！！！！ 
	    $ ("#s_classId option[value!='-1']").remove ();
	    //没有选择年级
	    if (s_gradeId == -1)
	    {
		    $ ("#s_classId").val ("-1");
		    return;
	    }
	    // 	Ajax的post请求
	    $.post ("clazz!getClassByGradeId",
	    {
		    s_gradeId : s_gradeId
	    }, function (result)
	    {
		    $ ("#s_classId").val ("-1");
		    var result = eval ("(" + result + ")");
		    //循环操作（JQuery的遍历）
		    // 	result是集合 function()中的参数是 i是序号 和 item是集合中每个项
		    $.each (result, function (i, item)
		    {
			    
			    // [attribute]	匹配包含给定属性的元素	查找所有含有 id 属性的 div 元素: 
			    // 		    	$("div[id]")
			    // 	[attribute=value]	匹配给定的属性是某个特定值的元素	查找所有 name 属性是 newsletter 的 input 元素: 
			    // 		    	$("input[name='newsletter']").attr("checked", true);
			    // 根据 HTML 原始字符串动态创建 Dom 元素.
			    // appendTo()添加到指定节点后面
			    $ ("<option value='"+item.classId+"'>" + item.className + "</option>").appendTo ($ ("#s_classId"));
		    });
	    });
    }

    function resetValue ()
    {
	    $ ("#s_stuNo").val ("");
	    $ ("#s_stuSex").val ("-1");
	    $ ("#s_stuName").val ("");
	    $ ("#s_stuNation").val ("-1");
	    $ ("#s_gradeId").val ("-1");
	    $ ("#s_classId").val ("-1");
	    $ ("#startBirthday").val ("");
	    $ ("#endBirthday").val ("");
	    $ ("#startRxsj").val ("");
	    $ ("#endRxsj").val ("");
    }
</script>
<div class='data_list'>
	<div class="search_content">
		<form action="${pageContext.request.contextPath}/student!list" method="post">
			<table align="center">
				<tr>
					<td><lable>学号：</lable></td>
					<td><input type="text" id="s_stuNo" name="s_student.stuNo" value="${s_student.stuNo }" /></td>
					<td width="30px;">&nbsp;</td>
					<td><lable>姓名：</lable></td>
					<td><input type="text" id="s_stuName" name="s_student.stuName" value="${s_student.stuName }" /></td>
				</tr>
				<tr>
					<td><lable>性别：</lable></td>
					<td><select id="s_stuSex" name="s_student.stuSex">
							<option value="-1">请选择性别...</option>
							<c:forEach var="sexData" items="${s_sexDataDicList }">
								<option value="${sexData.ddValue }" ${s_student.stuSex==sexData.ddValue?'selected':'' }>${sexData.ddValue }</option>
							</c:forEach>
						</select></td>
					<td>&nbsp;</td>
					<td><lable>名族：</lable></td>
					<td><select id="s_stuNation" name="s_student.stuNation">
							<option value="-1">请选择名族...</option>
							<c:forEach var="nationData" items="${s_nationDataDicList }">
								<option value="${nationData.ddValue }" ${s_student.stuNation==nationData.ddValue?'selected':'' }>${nationData.ddValue }</option>
							</c:forEach>
						</select></td>
				</tr>
				<tr>
					<td><lable>所在年级：</lable></td>
					<!-- 调用Ajax请求 来实现二级联动 -->
					<td><select id="s_gradeId" name="s_student.gradeId" onchange="loadClassInfo()">
							<option value="-1">请选择年级...</option>
							<c:forEach var="grade" items="${s_gradeList }">
								<option value="${grade.gradeId }" ${s_student.gradeId==grade.gradeId?'selected':'' }>${grade.gradeName }</option>
							</c:forEach>
						</select></td>
					<td>&nbsp;</td>
					<td><lable>所在班级：</lable></td>
					<td><select id="s_classId" name="s_student.classId">
							<option value="-1">请选择班级...</option>
							<c:forEach var="clazz" items="${s_clazzList }">
								<option value="${clazz.classId }" ${s_student.classId==clazz.classId?'selected':'' }>${clazz.className }</option>
							</c:forEach>
						</select></td>
				</tr>
				<tr>
					<td><lable>出生日期：</lable></td>
					<td colspan="4"><input type="text" id="startBirthday" name="s_student.startBirthday"
							value="<fmt:formatDate value='${s_student.startBirthday }'
							pattern="yyyy-MM-dd" type="date" />" class="Wdate"
							onClick="WdatePicker()" />&nbsp;&nbsp;到&nbsp;&nbsp; <input type="text" id="endBirthday" name="s_student.endBirthday"
							value="<fmt:formatDate value='${s_student.endBirthday }'
							pattern="yyyy-MM-dd" type="date" />" class="Wdate"
							onClick="WdatePicker()" /></td>
				</tr>
				<tr>
					<td><lable>入学时间：</lable></td>
					<td colspan="4"><input type="text" id="startRxsj" name="s_student.startRxsj"
							value="<fmt:formatDate value='${s_student.startRxsj }'
							pattern="yyyy-MM-dd" type="date" />" class="Wdate"
							onClick="WdatePicker()" />&nbsp;&nbsp;到&nbsp;&nbsp; <input type="text" id="endRxsj" name="s_student.endRxsj"
							value="<fmt:formatDate value='${s_student.endRxsj }'
							pattern="yyyy-MM-dd" type="date" />" class="Wdate"
							onClick="WdatePicker()" /></td>
				</tr>
				<tr>
					<td colspan="2">
						<button class="btn btn-primary" type="submit">查询</button>
					</td>
					<td>&nbsp;</td>
					<td colspan="2">
						<button class="btn btn-primary" onclick="resetValue()" type="button">重置</button>
					</td>
				</tr>
			</table>
		</form>
		<button class="btn btn-mini btn-primary" type="button" style="float: right; margin-bottom: 5px;"
			onclick="javascript:window.location='student!preSave'">添加学生</button>
	</div>

	<div class="data_content">
		<table class='table table-bordered table-hover'>
			<tr>
				<th>序号：</th>
				<th>学号：</th>
				<th>学生姓名：</th>
				<th>性别：</th>
				<th>所在班级：</th>
				<th>所在年级：</th>
				<th>学生备注：</th>
				<th>入学时间：</th>
				<th>出生日期：</th>
				<th>操作</th>
			</tr>
			<c:forEach var="student" items="${studentList }" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${student.stuNo }</td>
					<td>${student.stuName }</td>
					<td>${student.stuSex }</td>
					<td>${student.className }</td>
					<td>${student.gradeName }</td>
					<td>${  fn:substring(student.stuDesc,0,10)  }</td>
					<td><fmt:formatDate value="${student.stuRxsj }" pattern="yyyy-MM-dd" type="date" /></td>
					<td><fmt:formatDate value="${student.stuBirthday }" pattern="yyyy-MM-dd" type="date" /></td>
					<td>
						<button type="button" class='btn btn-mini btn-primary'
							onclick="javascript:window.location='student!show?studentId=${student.studentId}'">查看详细信息</button>
						<button class='btn btn-info btn-mini' onclick="javascript:window.location='student!preSave?studentId=${student.studentId}'"
							Type="button">更新</button>
						<button type="button" class='btn btn-mini btn-danger' onclick="studentDelete('${student.studentId}')">删除</button>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<div class="pagination pagination-centered">
		<ul>${pageCode }</ul>
	</div>
</div>