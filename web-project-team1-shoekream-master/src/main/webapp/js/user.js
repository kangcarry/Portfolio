function idDuplicateCheck(){
	
	f.action = "user_id_check_form.jsp";
	f.method = 'POST';
	f.submit();
}
function userCreate() {
	if (document.f.user_id.value == "") {
		alert("사용자 아이디를 입력하십시요.");
		f.user_id.focus();
		return false;
	}

	if (f.user_password.value == "") {
		alert("비밀번호를 입력하십시요.");
		f.user_password.focus();
		return false;
	}
	if (f.password2.value == "") {
		alert("비밀번호확인을 입력하십시요.");
		f.password2.focus();
		return false;
	}
	if (f.user_name.value == "") {
		alert("이름을 입력하십시요.");
		f.user_name.focus();
		return false;
	}
	if (f.user_phone.value == "") {
		alert("전화번호를 입력하십시요.");
		f.user_phone.focus();
		return false;
	}
	if (f.user_email.value == "") {
		alert("이메일 주소를 입력하십시요.");
		f.user_email.focus();
		return false;
	}
	if (f.user_address.value == "") {
		alert("주소를 입력하십시요.");
		f.user_address.focus();
		return false;
	}

	f.action = "user_write_action.jsp";
	f.method = 'POST';
	f.submit();
}
function pwCheck() {
	if (f.user_password.value != f.password2.value) {
		document.getElementById('pw_span').innerText = "비밀번호가 일치하지 않습니다."
		document.getElementById('pw_span').style.color = "red";
		return false;
	}else {
		document.getElementById('pw_span').innerText = "비밀번호가 일치합니다."
		document.getElementById('pw_span').style.color = "blue";
		return true;
	}
}

function main() {
	f.action = "shop_main.jsp";
	f.submit();
}

function userModifyAction() {
	if (f.user_password.value == "") {
		alert("비밀번호를 입력하세요.");
		f.user_password.focus();
		return false;
	}
	if (f.user_password.value != f.password2.value) {
		alert("비밀번호가 일치하지 않습니다.");
		f.user_password.focus();
		f.user_password.select();
		return false;
		}
	if (f.user_name.value == "") {
		alert("이름을 입력하십시요.");
		f.user_name.focus();
		return false;
	}
	if (f.user_phone.value == "") {
		alert("전화번호를 입력하십시요.");
		f.user_phone.focus();
		return false;
	}
	if (f.user_email.value == "") {
		alert("이메일 주소를 입력하십시요.");
		f.user_email.focus();
		return false;
	}
	if (f.user_address.value == "") {
		alert("주소를 입력하십시요.");
		f.user_address.focus();
		return false;
	}
	document.f.action = "user_modify_action.jsp";
	document.f.method='POST';
	document.f.submit();
}

function deliveryAddAction(){
	let popupX = (document.body.offsetWidth / 2) - (200 / 2);
// 만들 팝업창 좌우 크기의 1/2 만큼 보정값으로 빼주었음

	let popupY= (window.screen.height / 2) - (300 / 2);
// 만들 팝업창 상하 크기의 1/2 만큼 보정값으로 빼주었음

	window.open('user_view_delivery_add_form.jsp','_blank','status=no, width=500, height=300, resizable = no,scrollbar = no','left='+ popupX + ', top='+ popupY);
}

function returnUserView() {
	location.href="user_view.jsp";
}

function delivery_delete(){
	console.log(document.getElementById('delivery_f'));	
}
