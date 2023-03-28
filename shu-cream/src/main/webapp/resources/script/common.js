
function movepage(page){
	location.href = "http://192.168.0.82/shu-cream/"+page;

}

function lengthCheck(obj){
	const length =[["userId",5,20],["userName",2,5],["userPw",5,15],["userPhone",11,11],
				  ["userAddr",0,20],["goodsName",2,20],["Conntents",2,30]];
	let result = null;
	
	for(let recordIdx=0; recordIdx<length.length; recordIdx++){
		if(obj.getAttribute("name") == length[recordIdx][0]){
			result = (obj.value.length >= length[recordIdx][1] && 
			          obj.value.length <= length[recordIdx][2])? true : false;
		break;
		
		}else{
			alert("올바르지못한 접근입니다....");
		}
	}
	return result;
}

function createForm(name, action, method){
	const form = document.createElement("form");
	if(name !== "") form.setAttribute("name",name);
	form.setAttribute("action",action);
	form.setAttribute("method",method);
	
	
	return form;
}
/* Input Box 생성*/
function createInputBox(type, name, value, placeholder){
	const input = document.createElement("input");
	input.setAttribute("type", type);
	input.setAttribute("name", name);
	if(value != "") input.setAttribute("value", value);
	if(placeholder != "") input.setAttribute("placeholde", placeholder);
	return input;
}

function serverCall() {
	const form = createForm("", "Logout", "post");
	document.body.appendChild(form);
	form.submit();
}

/* 페이지 이동 */
function serverCall(Page){
	
	if(Page == '1'){
	const form = createForm("", "Login","post");
		
	let group = [];
	group.push(document.getElementsByName("userId")[0]) 
	group.push(document.getElementsByName("userPw")[0]) 
	
	for(let idx = 0; idx<group.length; idx++){
		form.appendChild(group[idx]);	
	}
	document.body.appendChild(form);
	form.submit();	
	
	
	
	}else if(Page == 'Join.jsp'){
		const form = createForm("","Join", "post");
		
		form.appendChild(document.getElementById("Box"));
		
		document.body.appendChild(form);
		form.submit();
	}
	
	else if(Page == '2'){
		location.href = "localhost/shu-cream/index.jsp";
	}
}

function errorMessage(message){
	let ErrorMessage = message.split(":")[0];
	
	if(message != ''){
		document.getElementById("message").innerText = ErrorMessage;
		document.getElementById("messageContent").innerText = message.split(":")[1];
		document.getElementById("messageBox").style.display = "block";
	}
}
function disableMessage(){
	document.getElementById("message").innerText = "";
	document.getElementById("messageContent").innerText = "";
	document.getElementById("messageBox").style.display = "none";
}


/* search기능 구현을 위한 form을 형성하기 submit */ 
function searchCtl(userId){
	
	const Id = createInputBox("hidden", "userId", userId, "");
	
	// const form = createForm("","searchMenu","get");
	if(document.getElementsByName("searchBox")[0].value == "Title"){
		
		const form = createForm("","searchMenu","get");
		form.appendChild(createInputBox("hidden", "UserSearch", document.getElementsByName("search")[0].value, ""));
		
		form.appendChild(Id);
		document.body.appendChild(form);
		form.submit();
	}	
	
	// const form = createForm("", "searchUser","get")	
	else if(document.getElementsByName("searchBox")[0].value == "User"){
		
		const form = createForm("", "searchUser","get");
		const user = createInputBox("hidden", "UserSearch", document.getElementsByName("search")[0].value, "");
		
		form.appendChild(Id);
		form.appendChild(user);
		document.body.appendChild(form);
		form.submit();
	
	}else{
		
		const form = createForm("", "simpleAll","get");
		form.appendChild(createInputBox("hidden", "UserSearch", document.getElementsByName("search")[0].value, ""));
		
		form.appendChild(Id);
		document.body.appendChild(form);
		form.submit();
	}
	
}

/* DIV 생성 */
function createDIV(objId, className, funcName, value){
	const div = document.createElement("div");
	if(objId != '') div.setAttribute("id", objId);
	div.setAttribute("class", className);
	if(funcName != '') div.setAttribute("onClick", funcName);
	if(value != '') div.setAttribute("currentIdx", value);
	return div;
}

function createDIV(objId, className, funcName){
	const div = document.createElement("div");
	if(objId != '') div.setAttribute("id", objId);
	div.setAttribute("class", className);
	if(funcName != '') div.setAttribute("onClick", funcName);
	
	return div;
}

