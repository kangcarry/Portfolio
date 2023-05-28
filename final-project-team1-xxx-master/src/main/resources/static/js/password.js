$(document).ready(function() {
  // 서버로부터 세션 정보 가져오기
  
  $.ajax({
    type: "GET",
    url: "sessionAjax", // 세션 정보를 반환하는 API의 URL
    success: function(data) {
      // 세션 정보를 받아서 처리
      var snsType = data.snsType;
		console.log(snsType);
      // Old Password 입력 칸을 disabled로 설정
      if (snsType != null) {
        $("#oldPassword-field").prop('disabled', true);
        $("#newPassword-field").prop('disabled', true);
        $("#newPasswordConfirm-field").prop('disabled', true);
      }
    },
    error: function(xhr, status, error) {
      // 오류 처리
      console.error(xhr.responseText);
    }
  });
});