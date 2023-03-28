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

<body onLoad = "errorMessage('${param.message}')">

	<div>
		<div id="Wrap">
			<div id="title">
				<div id="logo" class="image" onClick="movepage(Logout.jsp)">
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
			<div id="screen" class="right" style="position: relative">
				<form name="write_form_member" method="post">
					<table width="100vw" style="padding: 5px 0 5px 0;">
						<tr height="2" bgcolor="#FFC8C3">
							<td colspan="4" class=textbox></td>
						</tr>
						<tr>
							<th colspan="4" align="center">구매/판매여부 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].noteState}'></th>
						</tr>
						<tr>
							<th colspan="4" align="center" >상품명 <input type="text"
								 value='${NoteList[0].noteList[0].goodsName}' readOnly></th>
						</tr>
						<tr>
							<th colspan="2" align="center" >카테고리 <input type="text"
								 readOnly 
								 value='${NoteList[0].noteList[0].cateList[0].goodsList[0].gs_Big}'>
						<input type="text" readOnly value='${NoteList[0].noteList[0].cateList[0].goodsList[0].gs_Small}'></th>
						</tr>
						<tr>
							<th colspan="2" align="center" >지역 <input type="text"
								 readOnly 
								 value='${NoteList[0].noteList[0].cateList[0].placeList[0].placeBig}'>
								 <input type="text" readOnly value='${NoteList[0].noteList[0].cateList[0].placeList[0].placeSma}'></th>
						</tr>
						<tr>
							<th colspan="4" align="center" >거래방식 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].tranMethod}'></th>
						</tr>
						<tr>
							<th colspan="4" align="center" >상품상태 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].goodsState}'></th>
						</tr>
						<tr>
							<th colspan="4" align="center" >금액 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].goodsPrice}원'></th>
						</tr>
						<tr>
							<th colspan="4" align="center" >수량 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].goodsQuantity}개'></th>
						</tr>
						<tr>
							<th colspan="5" align="center" >판매상태 <input type="text"
								 readOnly value='${NoteList[0].noteList[0].noteIng}'></th>
						</tr>
						<tr>
							<td colspan="5" align="center">
								<div>
									<p>
										<textarea cols="80" rows="20" readOnly >${NoteList[0].noteList[0].conntents}</textarea>
									</p>
									<p>
										<input type="submit" value="찜"> 
										<input type="reset" value="취소">
									</p>
								</div>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
<div>	
    ${NoteList[0].noteList[0].noteCode}
	${NoteList[0].noteList[0].noteDate}
	${NoteList[0].noteList[0].goodsName}
	${NoteList[0].noteList[0].goodsQuantity}
	${NoteList[0].noteList[0].goodsPrice}
	${NoteList[0].noteList[0].conntents}
	${NoteList[0].noteList[0].tranMethod}
	${NoteList[0].noteList[0].noteState}
	${NoteList[0].noteList[0].noteIng}
	${NoteList[0].noteList[0].goodsState}
	${NoteList[0].userId}
	${NoteList[0].noteList[0].cateList[0].goodsList[0].gs_Big}
	${NoteList[0].noteList[0].cateList[0].goodsList[0].gs_Small}
	${NoteList[0].noteList[0].cateList[0].placeList[0].placeSma}
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
<script type="text/javascript">

function viewNote(){
	
	${NoteList[0].noteList[0].goodsName}
	${NoteList[0].noteList[0].goodsQuantity}
	${NoteList[0].noteList[0].goodsPrice}
	${NoteList[0].noteList[0].conntents}
	${NoteList[0].noteList[0].tranMethod}
	${NoteList[0].noteList[0].noteState}
	${NoteList[0].noteList[0].noteIng}
	${NoteList[0].noteList[0].goodsState}
	
	
	
	${NoteList[0].noteList[0].noteDate}
	${NoteList[0].noteList[0].noteCode}
	${NoteList[0].userId}
	
}


</script>
</html>