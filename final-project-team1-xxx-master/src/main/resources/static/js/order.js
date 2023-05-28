(function($) {
	$('.call-order-modal').on('click', function(e) {
		e.preventDefault();
		if ($('.order-model.modal').length > 0) {
			// 모달이 존재하는 경우
			$('.order-model.modal').remove();
		}
		let productId = $(this).data('product-id');
		let productIdJson = JSON.stringify({id: productId});
		let promise = $.ajax({
			type: 'POST',
			url: 'order-popup',
			data: productIdJson,
			contentType: 'application/json',
			dataType: 'json'
		});

		// Promise 객체를 사용하여 Ajax 요청 처리
		promise.then(function(responseData) {
			let product = responseData.product;
            // HTML 파일 추가
			$(responseData.html).appendTo('body').modal({
				closeExisting: true,
				fadeDuration: 300,
				fadeDelay: 0.15
			});
			$('#order-name').val(responseData.user.userName);
			$('#order-phone').val(responseData.user.userPhone);
			$('#order-email').val(responseData.user.userEmail);
			$('#product-id').val(product.id);
			$('.product-name').text(product.productName);
			$('.product-total').text(product.productPrice.toLocaleString()+'원');
			$('.product-period').text('결제일로부터 '+product.productPeriod+'일');
		})
		.fail(function(xhr) {
				// Ajax 요청 실패 시 처리
				window.location.href = 'errorPage';
		});
			
	});
	//결제요청
	$(document).on('click', '#pay-btn', function(e) {
		e.preventDefault();
		//modal창 끄기
		$('.jquery-modal.blocker.current').css('display', 'none');
		
		const productData = {
			id: $('#product-id').val(),
			productName: $('.product-name').text(),
			productPeriod: parseInt($('.product-period').text().match(/\d+/)[0]),
			productPrice: parseInt($('.product-total').text().replace(',', '')),
		};
		const userData = {
			userName: $('#order-name').val(),
			userPhone: $('#order-phone').val(),
			userEmail: $('#order-email').val()
		};
		
		const emailRegEx = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // 이메일 정규식
		const phoneRegEx = /^\d{3}-\d{4}-\d{4}$/; // 전화번호 형식 정규식

		if (!phoneRegEx.test(userData.userPhone)) {
			$('#order-phone').siblings('.error-message-phone').text('전화번호를 올바르게 입력해주세요.').show();
			$('#order-phone').focus();
			return;
		} else {
			$('#order-phone').siblings('.error-message-phone').hide();
		}
		
		if (!emailRegEx.test(userData.userEmail)) { // 이메일 유효성 검사
			$('#order-email').siblings('.error-message-email').text('이메일을 올바르게 입력해주세요.').show();
			$('#order-email').focus();
			return false;
		} else {
			$('#order-email').siblings('.error-message-email').hide();
		}

		const paymentData = {
			paymentMethod: $('input[name="payment-group"]:checked').val(),
			paymentPg: $('input[name="payment-group"]:checked').attr('data-pg')
		};
		
		const currentDate = new Date();
		const orderId = 'ORDER_' + currentDate.getFullYear() 
		+ (currentDate.getMonth() + 1) + currentDate.getDate() 
		+ currentDate.getHours() + currentDate.getMinutes() 
		+ currentDate.getSeconds();
		const orderData = {
			orderId: orderId
		};
		
		const formData = {
			productData: productData,
			userData: userData,
			paymentData: paymentData,
			orderData: orderData
		};
		const jsonData = JSON.stringify(formData);
		IMP.init("imp21102268");
		//결제 api
		IMP.request_pay({
			pg: paymentData.paymentPg,
			pay_method: paymentData.paymentMethod,
			merchant_uid: orderData.orderId, //상점에서 생성한 고유 주문번호
			name: '주문명:' + productData.productName,
			amount: productData.productPrice,
			buyer_email: userData.userEmail,
			buyer_name: userData.userName,
			buyer_tel: userData.userPhone,
		}, function(rsp) { // callback 로직
			if (rsp.success) {
				alert('결제가 완료되었습니다.');
				// 결제 성공 시 처리할 로직을 추가하세요.
				let promise = $.ajax({
					type: 'POST',
					url: 'order',
					data: jsonData,
					contentType: 'application/json',
					dataType: 'json'
				});

				// Promise 객체를 사용하여 Ajax 요청 처리
				promise.then(function(response) {
					// 로그인 성공 시 처리					
					// response 문자열로 변환 후 LocalStorage에 저장
					localStorage.setItem("data", JSON.stringify(response));
					window.location.href="/final-project-team1-ilhajob/order-completed"
				})
				.fail(function(xhr) {
						// Ajax 요청 실패 시 처리
						alert("결제에 성공했습니다.");
				});
			} else {
				var msg = '결제에 실패하였습니다.';
				msg += '에러내용 : ' + rsp.error_msg;
				alert(msg);
			}
		});
	});
	
	$(document).on('keyup','#order-phone',function(e){
		// 입력값에서 숫자만 추출
		const numbersOnly = this.value.replace(/[^0-9]/g, '');
		// 전화번호 형식으로 변환
		let formattedNumber = '';
		if (numbersOnly.length > 3) {
			formattedNumber += numbersOnly.substr(0, 3) + '-';
			if (numbersOnly.length > 7) {
				formattedNumber += numbersOnly.substr(3, 4) + '-' + numbersOnly.substr(7);
			} else {
				formattedNumber += numbersOnly.substr(3);
			}
		} else {
			formattedNumber += numbersOnly;
		}
		// 입력값 갱신
		this.value = formattedNumber.substr(0, 13);
	});
	
	$(function() {
		let validList = document.getElementsByName('orderVaild');
		for (let i = 0; i < validList.length; i++) {
			const valid = validList[i];
			const validValue = parseInt(valid.getAttribute('value'));
			if (validValue === 0) {
				valid.textContent = 'End';
				valid.style.color = 'red';
			} else if (validValue === 1) {
				valid.textContent = 'Active';
				valid.style.color = 'green';
			}
		}
	});


})(window.jQuery);