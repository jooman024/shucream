<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글등록페이지</title>
<script src="resources/script/common.js"></script>
<link rel="stylesheet" href="resources/css/RegistPage.css" />
<link rel = "stylesheet" href="resources/css/common.css" />
<style type="text/css">
table {
	border-spacing: 0;
}

table td, table th {
	padding: 2px;
}
</style>
</head>

<body onLoad = "errorMessage('${param.message}' ,'${UserBean.userId}')" >
<div>
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
					</div>
					<div class="regist on">등록</div>
					
					<div class="zzim on">찜</div>
					<div class="end on" onclick="serverCall()">로그아웃</div>
				</div>
			</div>
			<div id="screen" class="right" style="position: relative">
				<form name="write_form_member" >
					<table width="100vw" style="padding: 5px 0 5px 0;">
						<tr height="2" bgcolor="#FFC8C3">
							<td colspan="4"></td>
						</tr>
						<tr>
							<th colspan="4" align="center">구매/판매여부</th>
							<td><select name='noteState' size='1' class='select'>
									<option value=''>선택하세요</option>
									<option value='삽니다'>삽니다</option>
									<option value='팝니다'>팝니다</option>
							</select>
						</tr>
						<tr>
							<th colspan="4" align="center">상품명</th>
							<td><input type="text" name="goodsName" placeholder="최대 20자이내 작성해주세요. ">
						</tr>
						<tr>
							<th colspan="4" align="center">카테고리</th>
							<td><select name="gs_Big" onchange="bcChange(this)">
									<option value='0'>대분류</option>
									<option value='여성의류'>여성의류</option>
									<option value='남성의류'>남성의류</option>
									<option value='신발'>신발</option>
									<option value='가방'>가방</option>
									<option value='쥬얼리x시계'>쥬얼리/시계</option>
									<option value='디지털x가전'>디지털/사전</option>
									<option value='스포츠x레저'>스포츠/레저</option>
									<option value='장난감'>장난감</option>
									<option value='음반x악기'>음반/악기</option>
									<option value='도서'>도서</option>
									<option value='뷰티x미용'>뷰티/미용</option>
									<option value='가구x인테리어'>가구/인테리어</option>
									<option value='생활x가공식품'>생활/가공식품</option>
									<option value='아동물품'>아동물품</option>
									<option value='반려동물물품'>반려동물물품</option>
							</select> 
							<select id="good" name="gs_Small">
									<option>소분류</option>
							</select> 
							
							<script>
								function bcChange(e) {
									var good_여성의류 = [ "소분류", "외투", "상의", "바지",
											"치마", "원피스" ];
									var good_남성의류 = [ "소분류", "외투", "상의", "바지" ];
									var good_신발 = [ "소분류", "남성화", "여성화" ];
									var good_가방 = [ "소분류", "남성가방", "여성가방",
											"여행가방" ];
									var good_쥬얼리x시계 = [ "소분류", "시계", "쥬얼리" ];
									var good_디지털x가전 = [ "소분류", "모바일", "가전제품", "pc",
											"오디오/영상", "게임", "카메라" ];
									var good_스포츠x레저 = [ "소분류", "골프", "캠핑", "낚시",
											"축구", "농구", "자전거" ];
									var good_장난감 = [ "소분류", "피규어", "레고", "프라모델",
											"보드게임" ];
									var good_음반x악기 = [ "소분류", "cd/dvd/lp", "악기" ];
									var good_도서 = [ "소분류", "도서", "문구", "상품권" ];
									var good_뷰티x미용 = [ "소분류", "스킨케어", "메이크업", "향수",
											"네일아트" ];
									var good_가구x인테리어 = [ "소분류", "가구", "인테리어" ];
									var good_생활x가공식품 = [ "소분류", "주방용품", "생활용품", "식품",
											"산업용품" ];
									var good_아동물품 = [ "소분류", "0~2세", "3~6세", "7세",
											"출산용품" ];
									var good_반려동물물품 = [ "소분류", "반려용품", "사료/간식" ];
									var target = document
											.getElementById("good");

									if (e.value == "여성의류")
										var d = good_여성의류;
									else if (e.value == "남성의류")
										var d = good_남성의류;
									else if (e.value == "신발")
										var d = good_신발;
									else if (e.value == "가방")
										var d = good_가방;
									else if (e.value == "쥬얼리x시계")
										var d = good_쥬얼리x시계;
									else if (e.value == "디지털x가전")
										var d = good_디지털x가전;
									else if (e.value == "스포츠x레저")
										var d = good_스포츠x레저;
									else if (e.value == "장난감")
										var d = good_장난감;
									else if (e.value == "음반x악기")
										var d = good_음반x악기;
									else if (e.value == "도서")
										var d = good_도서;
									else if (e.value == "뷰티x미용")
										var d = good_뷰티x미용;
									else if (e.value == "가구x인테리어")
										var d = good_가구x인테리어;
									else if (e.value == "생활x가공식품")
										var d = good_생활x가공식품;
									else if (e.value == "아동물품")
										var d = good_아동물품;
									else if (e.value == "반려동물물품")
										var d = good_반려동물물품;

									target.options.length = 0;

									for (x in d) {
										var opt = document
												.createElement("option");
										opt.value = d[x];
										opt.innerHTML = d[x];
										target.appendChild(opt);
									}
								}
							</script>
						</tr>
						<tr>
							<th colspan="4" align="center">지역</th>

							<td><select name="placeBig" id="" onchange="categoryChange(this)">
									<option value="">시/도 선택</option>
									<option value="강원도">강원</option>
									<option value="경기도">경기</option>
									<option value="경상도">경상도</option>
									<option value="광주광역시">광주</option>
									<option value="대구광역시">대구</option>
									<option value="대전광역시">대전</option>
									<option value="부산광역시">부산</option>
									<option value="서울특별시">서울</option>
									<option value="울산광역시">울산</option>
									<option value="인천광역시">인천</option>
									<option value="전라도">전라도</option>
									<option value="제주특별자치도">제주</option>
									<option value="충청도">충청도</option>
									
							</select> 
							<select name="placeSma" id="state">
									<option>군/구 선택</option>
							</select> <script>
								function categoryChange(e) {
									const state = document
											.getElementById("state");

									const gangwon = [ "강릉시", "동해시", "삼척시",
											"속초시", "원주시", "춘천시", "태백시", "고성군",
											"양구군", "양양군", "영월군", "인제군", "정선군",
											"철원군", "평창군", "홍천군", "화천군", "횡성군" ];
									const gyeonggi = [ "고양시", "과천시", "광명시",
											"광주시", "구리시", "군포시", "김포시", "남양주시",
											"동두천시", "부천시", "성남시", "수원시", "시흥시",
											"안산시", "안성시", "안양시", "양주시", "오산시",
											"용인시", "의왕시", "의정부시", "이천시", "파주시",
											"평택시", "포천시", "하남시", "화성시", "가평군",
											"양평군", "여주군", "연천군" ];
									const gyeongsangnamdo = [ "거제시", "김해시",
											"마산시", "밀양시", "사천시", "양산시", "진주시",
											"진해시", "창원시", "통영시", "거창군", "고성군",
											"남해군", "산청군", "의령군", "창녕군", "하동군",
											"함안군", "함양군", "합천군", "경산시", "경주시",
											"구미시", "김천시", "문경시", "상주시", "안동시",
											"영주시", "영천시", "포항시", "고령군", "군위군",
											"봉화군", "성주군", "영덕군", "영양군", "예천군",
											"울릉군", "울진군", "의성군", "청도군", "청송군",
											"칠곡군" ];
									const gwangju = [ "광산구", "남구", "동구", "북구",
											"서구" ];
									const daegu = [ "남구", "달서구", "동구", "북구",
											"서구", "수성구", "중구", "달성군" ];
									const daejeon = [ "대덕구", "동구", "서구", "유성구",
											"중구" ];
									const busan = [ "강서구", "금정구", "남구", "동구",
											"동래구", "부산진구", "북구", "사상구", "사하구",
											"서구", "수영구", "연제구", "영도구", "중구",
											"해운대구", "기장군" ];
									const seoul = [ "강남구", "강동구", "강북구", "강서구",
											"관악구", "광진구", "구로구", "금천구", "노원구",
											"도봉구", "동대문구", "동작구", "마포구",
											"서대문구", "서초구", "성동구", "성북구", "송파구",
											"양천구", "영등포구", "용산구", "은평구", "종로구",
											"중구", "중랑구" ];
									const ulsan = [ "남구", "동구", "북구", "중구",
											"울주군" ];
									const incheon = [ "계양구", "남구", "남동구", "동구",
											"부평구", "서구", "연수구", "중구", "강화군",
											"옹진군" ];
									const jeonlado = [ "광양시", "나주시", "목포시",
											"순천시", "여수시", "강진군", "고흥군", "곡성군",
											"구례군", "담양군", "무안군", "보성군", "신안군",
											"영광군", "영암군", "완도군", "장성군", "장흥군",
											"진도군", "함평군", "해남군", "화순군", "군산시", "김제시", "남원시",
											"익산시", "전주시", "정읍시", "고창군", "무주군",
											"부안군", "순창군", "완주군", "임실군", "장수군",
											"진안군" ];
									const jeju = [ "서귀포시", "제주시", "남제주군",
											"북제주군" ];
									const chungchong = [ "제천시", "청주시", "충주시",
											"괴산군", "단양군", "보은군", "영동군", "옥천군",
											"음성군", "증평군", "진천군", "청원군" ];

									if (e.value == "강원도") {
										add = gangwon;
									} else if (e.value == "경기도") {
										add = gyeonggi;
									} else if (e.value == "경상도") {
										add = gyeongsangnamdo;
									} else if (e.value == "광주광역시") {
										add = gwangju;
									} else if (e.value == "대구광역시") {
										add = daegu;
									} else if (e.value == "대전광역시") {
										add = daejeon;
									} else if (e.value == "부산광역시") {
										add = busan;
									} else if (e.value == "서울특별시") {
										add = seoul;
									} else if (e.value == "울산광역시") {
										add = ulsan;
									} else if (e.value == "인천광역시") {
										add = incheon;
									} else if (e.value == "전라도") {
										add = jeonlado;
									} else if (e.value == "제주특별자치도") {
										add = jeju;
									} else if (e.value == "충청도") {
										add = chungchong;
									}

									state.options.length = 1;
									// 군/구 갯수;

									for (property in add) {
										let opt = document
												.createElement("option");
										opt.value = add[property];
										opt.innerHTML = add[property];
										state.appendChild(opt);
									}
								}
							</script>
						</tr>

						<tr>
							<th colspan="4" align="center">거래방식</th>
							<td><select name='tranMethod' size='1' class='select'>
									<option value=''>선택하세요</option>
									<option value='직거래'>직거래</option>
									<option value='택배거래'>택배거래</option>
							</select>
						</tr>
						<tr>
							<th colspan="4" align="center">상품상태</th>
							<td>
							<input type='checkbox' name='goodsState' value='새삥'>
								새삥 
							<input type='checkbox' name='goodsState' value='보통'>
								보통 
							<input type='checkbox' name='goodsState' value='빈티지'>
								빈티지</td>
						</tr>
						<tr>
							<th colspan="4" align="center">금액</th>
							<td><input type="text" name="goodsPrice" maxlength=8 placeholder="Max 99999999">원</td>

						</tr>
						<tr>
							<th colspan="4" align="center" >수량</th>
							<td><input type="text" name="goodsQuantity" maxlength=2 placeholder="Max 99">개</td>
						</tr>
						<tr>
							<th colspan="4" align="center">판매상태</th>
							<td><select name='noteIng' size='1' class='select'>
									<option value=''>선택하세요</option>
									<option value='구매중'>구매중</option>
									<option value='구입완료'>구매완료</option>
									<option value='판매중'>판매중</option>
									<option value='판매완료'>판매완료</option>
							</select>
						</tr>
						<tr>
							<th  colspan="5" align="center">내용입력</th>
						</tr>
						<tr>
							<td colspan="5" align="center">
								<div>
									<textarea id="textarea" name="textarea" cols="80" rows="20" placeholder="내용과 장소를 300자 이내로 간략히 작성해주십시오."></textarea>
								<input type="button" value="등록" onClick="regNote('${UserBean.userId}')"> 
								<input type="reset"  value="취소" onClick="cencel()">
								</div>
						</tr>
						<tr>
							<th colspan="6" align="center">첨부사진<input type="file" name="filename" onClick="skip('${UserBean.userId}')";>
							</th>
						</tr>
					</table>
				</form>
			</div>
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

<script>
const noteform = ["noteState", "goodsName", "gs_Big","gs_Small", "placeBig", "placeSma", 
	              "tranMethod", "goodsState", "goodsPrice", "goodsQuantity", "noteIng"]
	  
function regNote(userId){
	
	const form = createForm("", "insNote", "post");
	const Id = createInputBox("hidden", "userId", userId, "");
	const content = createInputBox("hidden", "textarea", document.getElementById("textarea").value, "");
	
	for(let idx=0; idx<noteform.length; idx++){
		
		form.appendChild(createInputBox("hidden", ﻿noteform[idx], document.getElementsByName(noteform[idx])[0].value,""));
	}
	
	form.appendChild(content);
	form.appendChild(Id);
	document.body.appendChild(form);
	form.submit();

}

function skip(){

	console.log(${UserBean.userId});
	
}

function cencel(){
	
	
}

</script>
</html>