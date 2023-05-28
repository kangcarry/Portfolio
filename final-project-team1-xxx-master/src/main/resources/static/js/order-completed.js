(function($) {
	
	$(document).ready(function() {
		// localStorage에서 데이터를 불러오기
		const data = JSON.parse(localStorage.getItem("data"));
		// 데이터를 사용하여 DOM을 변경
		const productPrice = parseInt(data.productData.productPrice);
		const orderEndDate = new Date(data.orderData.orderEndDate); // 주문 종료 날짜
		const currentDate = new Date(); // 현재 날짜와 시간
		const timeDiff = orderEndDate.getTime() - currentDate.getTime(); // 두 날짜의 차이(밀리초)
		const dayDiff = Math.ceil(timeDiff / (1000 * 3600 * 24)); // 차이를 일(day)로 변환
		
		$(".order-info #order-id").text(data.orderData.id);
		$(".order-info #order-date").text(data.orderData.orderStartDate);
		$(".order-info #order-total").text(productPrice.toLocaleString()+"원");
		$(".order-info #order-method").text(data.paymentData.paymentMethod);
		$(".product-name").text(data.productData.productName);
		$(".product-total").text(productPrice.toLocaleString()+"원");
		$(".order-period").text('결제일로부터 '+dayDiff+'일');
		$(".amount").text(productPrice.toLocaleString()+"원");
		
	});

	$(window).on('beforeunload', function() {
		// 페이지 전환시 localStorage 데이터를 삭제
		localStorage.removeItem("data");localStorage.removeItem("data");
	});

})(window.jQuery);