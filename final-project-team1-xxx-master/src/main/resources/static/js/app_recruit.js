import * as View from "./view.js";
import * as Request from "./request.js";

Handlebars.registerHelper('ifEqual', function(v1, v2, options) {
  return (v1 === v2) ? options.fn(this) : options.inverse(this);
});
Handlebars.registerHelper('ifEquals', function(a, b, options) {
	    if (a == b) {
	        return options.fn(this);
	    } else {
	        return options.inverse(this);
	    }
	});
Handlebars.registerHelper('dateFormat', function(format, date) {
  var momentDate = moment(date);
  return momentDate.format(format);
});
Handlebars.registerHelper('eq', function(a, b) {
	  return a === b;
});
Handlebars.registerHelper('not', function(value) {
    return !value;
});
Handlebars.registerHelper('subtract', function(a, b) {
    return a - b;
});
Handlebars.registerHelper('add', function(a, b) {
    return a + b;
});
Handlebars.registerHelper('gte', function(a, b) {
    return a >= b;
});
Handlebars.registerHelper('lte', function(a, b) {
    return a <= b;
});
Handlebars.registerHelper('and', function(a, b) {
  return a && b;
});
Handlebars.registerHelper('formatDate', function(date, format) {
	console.log("formatDate도착");
  return temporals.format(date, format);
});
Handlebars.registerHelper('lookup', function (obj, key) {
		 if (obj === undefined) {
		        return ""; // 또는 적절한 기본값 또는 에러 처리 로직을 추가하세요
		    }
	        return obj[key];
	 });
Handlebars.registerHelper('with', function(context, options) {
  return options.fn(context);
});
$('.changeTag').click(function(e){
	console.log("펑션접근");
	/*
		url,method,contentType,sendData,function,async
	*/
		
		let sendData={
			tagId:parseInt($(this).val())
			
		};
	console.log(sendData);
	Request.ajaxRequest('getRecruitTag',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							console.log(resultJson);
							console.log("제이슨 받아오기 성공");
								View.render("#recruit-tag-template",resultJson,"#recruit-main-list");
						},
						true); //비동기
	e.preventDefault();
});

$(document).on('click','.next-link',function(e){
	e.preventDefault();
	let sendData={
		tagId:parseInt($(".tag-data").val()),
		page:parseInt($(".next-data").val())
	};
	console.log(sendData);
	Request.ajaxRequest('getRecruitTag',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							View.render("#recruit-tag-template",resultJson,"#recruit-main-list");
							window.scrollTo(0, 0);
						},
						true);

});

$(document).on('click','.prev-link',function(e){
	e.preventDefault();
	let sendData={
		tagId:parseInt($(".tag-data").val()),
		page:parseInt($(".prev-data").val())
	};
	console.log(sendData);
	Request.ajaxRequest('getRecruitTag',
					    'GET',
						'application/json;charset=UTF-8',
						sendData,
						function(resultJson){
							View.render("#recruit-tag-template",resultJson,"#recruit-main-list");
							window.scrollTo(0, 0);
						},
						true);

});


//인서트
$(document).on('click','#insertTag',function(){
	
		console.log("태그생성펑션접근");
		
		let tagId = $('#tagId option:selected').val();//스크랩등록시 이미지
		let reqId = $(this).val();
		let jsonData = {
			tagId: tagId,
			reqId: reqId,
		};
		console.log(JSON.stringify(jsonData));
		Request.ajaxRequest('recruit-tag-insert-action',
							'POST',
							'application/json;charset=UTF-8',
							JSON.stringify(jsonData),
							function(resultJson){
							console.log("제이슨 받아오기 성공");
							View.render("#recruit-tag-insert-delete-template",resultJson,"#recruit-tag-zone");
							},
							true);
		});
		
//삭제
$(document).on('click','#tag-delete',function(){
	
		console.log("태그삭제펑션접근");
		
		let reqruitTagId = $(this).val(); //스크랩등록시 이미지
		let reqId = $('#insertTag').val();
		let jsonData = {
			reqruitTagId: reqruitTagId,
			reqId: reqId,
		};
		console.log(JSON.stringify(jsonData));
			Request.ajaxRequest('recruit-tag-delete-action',
								'POST',
								'application/json;charset=UTF-8',
								JSON.stringify(jsonData),
								function(resultJson){
								console.log("제이슨 받아오기 성공");
								View.render("#recruit-tag-insert-delete-template",resultJson,"#recruit-tag-zone");
								},
								true);
		});