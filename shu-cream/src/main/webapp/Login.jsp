<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src ="resources/script/common.js"></script>
<link rel = "stylesheet" href="resources/css/Login.css" />
<link rel = "stylesheet" href="resources/css/common.css" />
</head>
<body onLoad = "errorMessage('${param.message}')">
<div id= "wrap">
		<div id="title">
		<div id="logo" class="left">shu-cream</div>	
		</div>
		<div id="content">
	<h1>로그인...........................</h1>
		<input type="text" name="userId" placeholder="아이디를 입력하세요" class="box a" />
		<input type="password" name="userPw" placeholder="비밀번호를 입력하세요"  class="box b" />
		
<input type="button" class="btn a" value="LOGIN" onclick="serverCall('1')"/>
<input type="button" class="btn b" value="REGISTRATION" onclick="serverCall('2')"/>
	</div>
</div>

<!-- 상태에 따라 사용자에게 띄워줄 Message -->
<div id="messageBox" style="display:none">
	<div id="message">Message</div>
	<div id="messageZone">
		<div id="messageContent">Server Message</div>
	<div id="messageAction">
		<div class="button" onClick="disableMessage()">OK</div>
	</div>
	</div>
</div>
</body>

<script>

</script>
</html>