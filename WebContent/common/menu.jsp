<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script type="text/javascript">
	// 	function logout(){
    // 		if(confirm("确认退出系统？")){
    // 			window.location.href='user!logout';
    // 		}
    // 	}
</script>
<div class="row-fluid">
	<div class="span12">
		<div class="navbar">
			<div class="navbar-inner">
				<a class="brand" href="main.jsp">首页</a>
				<ul class="nav">
					<li class="dropdown">
						<!--dropdown-toggle下拉菜单 -->
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							学生信息管理 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="student!preSave">学生信息添加</a>
							</li>
							<li>
								<a href="student!list">学生信息维护</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							班级信息管理 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="clazz!preSave">班级信息添加</a>
							</li>
							<li>
								<a href="clazz!list">班级信息维护</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							年级信息管理 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="grade!preSave">年级信息添加</a>
							</li>
							<li>
								<a href="grade!list">年级信息维护</a>
							</li>
						</ul>
					</li>
					<li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							系统管理 <b class="caret"></b>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="dataDic!list">数据字典维护</a>
							</li>
							<li>
								<a href="dataDicType!list">数据字典类别维护</a>
							</li>
							<li>
								<a href="user!preSave">修改密码</a>
							</li>
							<li class="divider"></li>
							<li>
								<a href="user!logout">退出系统</a>
							</li>
						</ul>
					</li>
				</ul>
			</div>
		</div>
	</div>
</div>