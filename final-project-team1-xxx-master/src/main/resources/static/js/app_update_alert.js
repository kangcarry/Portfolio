function openPopup() {
  var popup = document.getElementById("myModal");
  popup.style.display = "block";
}

function closePopup() {
  var popup = document.getElementById("myModal");
  popup.style.display = "none";
}

// 팝업 열기
openPopup();

// 3초 후 팝업 닫기
setTimeout(function() {
  closePopup();
}, 1200);