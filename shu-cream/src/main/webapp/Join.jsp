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

		</div>
		
		<div id="Box">
	    <input type="text" name="userId" placeholder="아이디를 입력하세요" class="box a" />
		<input type="password" name="userPw" placeholder="비밀번호를 입력하세요"  class="box b" />	
	    <input type="text" name="userName" placeholder="이름을 입력하세요" class="box c" />
		<input type="text" name="userPhone" placeholder="전화번호를 입력하세요"  class="box d" />	
		<input type="text" name="userAddr" placeholder="주소를 입력하세요"  class="box e" />	
		</div>
		
		<div id='button'>
		<input type="button" class="btn a" value="회원가입" onclick="serverCall('Join.jsp')"/>
		<input type="button" class="btn b" value="이전 화면" onclick="serverCall('Login.jsp')"/>
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
</html>