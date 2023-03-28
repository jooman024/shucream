<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/HomePage.css" />
<link rel = "stylesheet" href="resources/css/common.css" />
</head>
<body onLoad = "errorMessage('${param.message}')">
	<div id="Wrap">
			<div id="title">
				<div id="logo" class="image">
					<div class="sselect">
						<select name="searchBox" size='1' class='select'>
							<option value='Title'>제목</option>
							<option value='User'>사용자</option>
						</select>
					    <input type="text" name="search" placeholder="최대 10자 이내로 검색해주십시오.">
						<input type="button" value="검색" onClick="searchCtl('${UserBean.userId}')">
					</div>
					<div class="regist on" onclick="movepage(RegistPage.jsp)">등록</div>

					<div class="zzim on">찜</div>
					<div class="end on" onclick="movepage('Login.jsp')">로그인</div>
					<div class="sign on" onclick="movepage('Join.jsp')">회원가입</div>
				</div>
			</div>
			<div class="menu">
				<input type="checkbox" class="" id="collapsible"> <label
					for="collapsible"><span>메뉴</span></label>
				<ul class="menuitems">
				
				  <li><a href="#">여성의류</a>
					<ul class="subdropmenu">
					<li><a href="#">외투</a></li>
					<li><a href="#">상의</a></li>
					<li><a href="#">바지</a></li>
					<li><a href="#">치마</a></li>
					<li><a href="#">원피스</a></li>
					</ul>
					</li>
					
				  <li><a href="#">남성의류</a>
					<ul class="subdropmenu">
					<li><a href="#">외투</a></li>
					<li><a href="#">상의</a></li>
					<li><a href="#">바지</a></li>
					</ul>
					</li>
					
					<li><a href="#">신발</a>
					<ul class="subdropmenu">
					<li><a href="#">남성화</a></li>
					<li><a href="#">여성화</a></li>
					</ul>
					</li>
					
					<li><a href="#">가방</a>
					<ul class="subdropmenu">
					<li><a href="#">남성가방</a></li>
					<li><a href="#">여성가방</a></li>
					<li><a href="#">여행가방</a></li>
					</ul>
					</li>
					
					<li><a href="#">쥬얼리/시계</a>
					<ul class="subdropmenu">
					<li><a href="#">시계</a></li>
					<li><a href="#">쥬얼리</a></li>
					</ul>
					</li>
					
					<li><a href="#">디지털/가전</a>
					<ul class="subdropmenu">
					<li><a href="#">모바일</a></li>
					<li><a href="#">가전</a></li>
					<li><a href="#">pc</a></li>
					<li><a href="#">오디오영상</a></li>
					<li><a href="#">게임</a></li>
					<li><a href="#">카메라</a></li>
					</ul>
					</li>
					
					<li><a href="#">스포츠/레저</a>
					<ul class="subdropmenu">
					<li><a href="#">골프</a></li>
					<li><a href="#">캠프</a></li>
					<li><a href="#">낚시</a></li>
					<li><a href="#">축구</a></li>
					<li><a href="#">농구</a></li>
					<li><a href="#">자전거</a></li>
					</ul>
					</li>
					
					<li><a href="#">장난감</a>
					<ul class="subdropmenu">
					<li><a href="#">피규어</a></li>
					<li><a href="#">레고</a></li>
					<li><a href="#">프라모델</a></li>
					<li><a href="#">보드게임</a></li>
					</ul>
					</li>
					
					<li><a href="#">음반/악기</a>
					<ul class="subdropmenu">
					<li><a href="#">cd</a></li>
					<li><a href="#">dvd</a></li>
					<li><a href="#">lp</a></li>
					<li><a href="#">악기</a></li>
					</ul>
					</li>
					
					<li><a href="#">도서</a>
					<ul class="subdropmenu">
					<li><a href="#">도서</a></li>
					<li><a href="#">문구</a></li>
					<li><a href="#">상품권</a></li>
					</ul>
					</li>
					
					<li><a href="#">뷰티/미용</a>
					<ul class="subdropmenu">
					<li><a href="#">스킨케어</a></li>
					<li><a href="#">메이크업</a></li>
					<li><a href="#">향수</a></li>
					<li><a href="#">네일아트</a></li>
					</ul>
					</li>
					
					<li><a href="#">가구/인테리어</a>
					<ul class="subdropmenu">
					<li><a href="#">가구</a></li>
					<li><a href="#">인테리어</a></li>
					</ul>
					</li>
					
					<li><a href="#">생활/가공식품</a>
					<ul class="subdropmenu">
					<li><a href="#">주방용품</a></li>
					<li><a href="#">생활용품</a></li>
					<li><a href="#">식품</a></li>
					<li><a href="#">산업용품</a></li>
					</ul>
					</li>
					
					<li><a href="#">아동물품</a>
					<ul class="subdropmenu">
					<li><a href="#">0~2세</a></li>
					<li><a href="#">3~6세</a></li>
					<li><a href="#">7세</a></li>
					<li><a href="#">출산용품</a></li>
					</ul>
					</li>
					
					<li><a href="#">반려용품</a>
					<ul class="subdropmenu">
					<li><a href="#">반려용품</a></li>
					<li><a href="#">사료/간식</a></li>
					</ul>
					</li>
				</ul>
			</div>
			<div id="screen" class="right" style="position: relative"></div>
		</div>
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