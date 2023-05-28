(function($) {
	$(document).on('click', '#modify-btn', (e) => {
		e.preventDefault();
		const inputElems = document.querySelectorAll('.default-form input');
		inputElems.forEach(elem => {
			elem.disabled = false;
		})
		$('#address-postcode-disable').attr('id', 'address-postcode');
		$('#address-road-disable').attr('id', 'address-road');
		$('#address-detail-disable').attr('id', 'address-detail');
		window.scrollTo(0, 0);
		const saveBtn = $('<button/>', {
			text: '저장',
			id: 'save-btn',
			class: 'theme-btn btn-style-one',
			click: (e) => {
				e.preventDefault();
				const form = document.querySelector('.default-form');
				const formData = new FormData(form);
				const jsonData = {};
				for (let [key, value] of formData.entries()) {
					jsonData[key] = value;
				}
				console.log(JSON.stringify(jsonData));
/*				const formJson = {};
				$.each($('.default-form').serializeArray(), function() {
					formJson[this.name] = this.value;
				});*/
				fetch('modify_action', {
					method: 'POST',
					headers: {
						'Content-Type': 'application/json'
					},
					body: JSON.stringify(jsonData)
				})
					.then(response => {
						if (!response.ok) {
							throw new Error('Network response was not ok');
						}
						return response.json();
					})
					.then(data => {
						console.log(data);
						window.location.href="/final-project-team1-ilhajob/candidate-dashboard-profile";
					})
					.catch(error => {
						console.error(error);
					});
			}
		});
		saveBtn.insertBefore('#modify-btn');
		$('#modify-btn').remove();
	});
	
	
	$(document).on('click','#address-postcode, #address-road',(e) =>{
		e.preventDefault();
		 new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                let addr = ''; // 주소 변수
                let extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("addressDetail").value = extraAddr;
                
                } else {
                    document.getElementById("addressDetail").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('postCode').value = data.zonecode;
                document.getElementById("address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("addressDetail").focus();
            }
        }).open();
	});
	
})(window.jQuery);