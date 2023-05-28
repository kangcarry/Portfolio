import * as View from "./view.js";
import * as Request from "./request.js";

  $(document).ready(function() {
	$('#create').click(function(e){
	console.log("펑션접근");
	/*
		url,method,contentType,sendData,function,async
	*/	
		var managerName= $("input[name='cmanagerName']").val();
		var managerEmail= $("input[name='cmanagerEmail']").val();
		var managerPosition= $("input[name='cmanagerPosition']").val();
		var managerPhone= $("input[name='cmanagerPhone']").val();
		let sendData={
		managerName: managerName,
		managerEmail: managerEmail,
		managerPosition: managerPosition,
		managerPhone: managerPhone
	};
	console.log(sendData);
	Request.ajaxRequest('manager',
					    'POST',
						'application/json;charset=UTF-8',
						JSON.stringify(sendData),
						function(resultJson){
							if(resultJson){
								console.log('콜백펑션 완료');
								View.render("#manager-create-list",resultJson.data,"#manager-list");
							}
						},
						true); //비동기
	e.preventDefault();
	});
});

