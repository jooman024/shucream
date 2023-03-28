<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/HomePage.css" />
</head>
<body>
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
					<div class="regist on" onclick="movepage('RegistPage.jsp')">등록</div>

					<div class="zzim on">찜</div>
					<div class="end on" onclick="serverCall()">로그아웃</div>
				</div>
			</div>
			<div class="menu">
				<input type="checkbox" class="" id="collapsible"> <label
					for="collapsible"><span>메뉴</span></label>
				<ul id="menuitems" class="menuitems">
				  <li class="subdropmenu"><a href="#">여성의류</a>
					<ul>
					<li><a href="#">외투</a></li>
					<li><a href="#">상의</a></li>
					<li><a href="#">바지</a></li>
					<li><a href="#">치마</a></li>
					<li><a href="#">원피스</a></li>
					</ul>
					<li>
					<li><a href="#">남성의류</a>
					<li>
					<li><a href="#">신발</a>
					<li>
					<li><a href="#">가방</a>
					<li>
					<li><a href="#">쥬얼리/시계</a>
					<li>
					<li><a href="#">가전제품</a>
					<li>
					<li><a href="#">스포츠/레저</a>
					<li>
					<li><a href="#">장난감</a>
					<li>
					<li><a href="#">음반/악기</a>
					<li>
					<li><a href="#">도서</a>
					<li>
					<li><a href="#">뷰티/미용</a>
					<li>
					<li><a href="#">가구/인테리어</a>
					<li>
					<li><a href="#">생활/가공식픔</a>
					<li>
					<li><a href="#">아동물품</a>
					<li>
					<li><a href="#">반려용품</a>
					<li>
				</ul>
			</div>
			<div id="screen" class="right" style="position: relative"></div>
		</div>
	
</body>
<script>

</script>
</html>