<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<script type="text/javascript">
	function setDateTime ()
    {
	    var date = new Date ();
	    var day = date.getDay ();
	    var week;
	    switch (day)
	    {
		    case 0:
			    week = "星期日";
			    break;
		    case 1:
			    week = "星期一";
			    break;
		    case 2:
			    week = "星期二";
			    break;
		    case 3:
			    week = "星期三";
			    break;
		    case 4:
			    week = "星期四";
			    break;
		    case 5:
			    week = "星期五";
			    break;
		    case 6:
			    week = "星期六";
			    break;
	    }
	    var today = date.getFullYear () + "年" + (date.getMonth () + 1) + "月" + date.getDate () + "日  " + week + " "
	            + date.getHours () + ":" + date.getMinutes () + ":" + date.getSeconds ();
	    // 	    innerHTML 是从对象的起始位置到终止位置的全部内容,包括Html标签
	    // 	    alert (document.getElementById ("today").innerHTML);
	    document.getElementById ("today").innerHTML = today;
	    // html 是 Jquery的方法 所以没有 document.getElementById ("today").html=today;
	    // 只有 $("today").html(today);
	    // html() 方法来获取任意一个元素的内容。
	    // 	alert (document.getElementById ("today").html);
    }
    //每隔一秒 重刷一次
    window.setInterval ("setDateTime()", 1000);
</script>
<div class="row-fluid">
	<div class="span12">
		<div class="head">
			<div class="headLeft">
				<img src="${pageContext.request.contextPath}/images/logo.png" />
			</div>
			<div class="headRight">
				欢迎管理员：
				<font color="red">${currentUser.userName }</font>
				&nbsp;&nbsp;&nbsp;
				<font id="today" class="currentDateTime">rrrrr</font>
			</div>
		</div>
	</div>
</div>