import * as View from "./view.js";
import * as Request from "./request.js";


$(document).on('click','#deleteScrap',function(e){
		console.log("펑션접근");
		let sendData = {
			id:$(this).val()
		};
		console.log(sendData);
			Request.ajaxRequest('delete-recruit-scrap',
								'POST',
								'application/json;charset=UTF-8',
								JSON.stringify(sendData),
								function(resultJson){
								console.log("제이슨 받아오기 성공");
								View.render("#refresh-scrap-list",resultJson,"#scrap-list");
								},
								true);
			e.preventDefault();	
});
//리스트에서 스크랩 등록/제거
$(document).on('click','#check-bookmark',function(){
	
		console.log("펑션접근");
		
		let img1 = $(this).find("#id1");//스크랩등록시 이미지
		let img2 = $(this).find("#id2");//스크랩해지시 이미지
		let jsonData = {
			rId: $(this).val()
		};
		console.log(JSON.stringify(jsonData));
	$.ajax({
		url: "recruit_scrap_insert_delete",
		type: "POST",
		contentType: 'application/json;charset=UTF-8', // Content-Type 설정
		data: jsonData,
		success: function(response) {
			console.log("컨트롤러 갔다옴");
			if (response == 1) {    //스크랩성공시
				img2.removeClass('show-img');
				img2.addClass('hidden-img');
				img1.removeClass('hidden-img');
				img1.addClass('show-img');
			} else {     //스크랩해지시
				img1.removeClass('show-img');
				img1.addClass('hidden-img');
				img2.removeClass('hidden-img');
				img2.addClass('show-img');
			}
			console.log(response);
		}, error: function(error) {
			console.log(error);	
		}
	});
	console.log("적용완료");
});

//디테일에서 스크랩 등록/제거
$(document).on('click','#detail-check-bookmark',function(){
	
		console.log("펑션접근");
		let button = $(this);
		let jsonData = {
			rId: $(this).val()
		};
		console.log(JSON.stringify(jsonData));
	$.ajax({
		url: "recruit_scrap_insert_delete",
		type: "POST",
		contentType: 'application/json;charset=UTF-8', // Content-Type 설정
		data: jsonData,
		success: function(response) {
			console.log("컨트롤러 갔다옴");
			if (response == 1) {    //스크랩성공시
				button.removeClass('scrap-btn');
				button.addClass('scrap-btn-click');
			} else {     //스크랩해지시
				button.removeClass('scrap-btn-click');
				button.addClass('scrap-btn');
			}
			console.log(response);
		}, error: function(error) {
			console.log(error);	
			window.location.replace("http://localhost/final-project-team1-ilhajob/login");
		}
	});
	console.log("적용완료");
});



