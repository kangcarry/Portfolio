/**
 * exp
 */
 
 // addExp() : 새로운 경력 영역을 추가
function addExp() {
  // 새로운 요소를 생성하고 클래스 이름을 추가
  var newEduBlock = document.createElement("div");
  newEduBlock.classList.add("exp-block");
  // 새로운 요소의 내부 HTML을 설정
  newEduBlock.innerHTML = `
  <div class="resume-block">
  <input type="hidden" name="expId">
    <div class="inner">
      <span class="name">S</span>
      <div class="title-box">
          <div class="col-lg-11 col-md-11"></div>
          <div class="edit-btns form-group col-lg-1 col-md-1">
            <button type="button" onclick="createExp()" class="add-info-btn"><span class="la la-check-circle-o"></span></button>

          </div>
      </div>
      <div class="row">
		      <div class="form-group col-lg-6 col-md-6">
			        <label>회사</label><br>
			        <input type="text" id="expCorpName" name="expCorpName" placeholder=" 근무했던 회사명을 입력하세요.">
		      </div>
		      <div class="form-group col-lg-6 col-md-6">
		            <label>직무</label>
			        <input type="text" id="expPosition" name="expPosition" placeholder="직무를 입력하세요.">
		      </div>
        
	      <div class="form-group col-lg-12 col-md-12">
	        <label>경력상세</label>
	        <input type="text" id="expContent" name="expContent" placeholder="경력의 상세 내용을 입력하세요.">
	      </div>
      
        <div class="edit-box">
	        <div class="form-group col-lg-6 col-md-6">
		     <label>경력 시작일</label><br>
	          <input type="date" id="expStartDate" name="expStartDate" class="year edit-box">
	        </div>
	        <div class="form-group col-lg-6 col-md-6">
		     <label>경력 종료일</label><br>
	          <input type="date" id="expEndDate" name="expEndDate" class="year edit-box">
	        </div>
        </div>
     	 </div> <!-- row end -->
   		</div>
      </div>
    </div>
  </div>
  `;

  var expList = document.getElementById("exp-block");
  console.log(">>>>>>>>>> 추가영역 생성 성공 " + expList);
  var lastEduBlock = expList.lastElementChild;
  expList.insertBefore(newEduBlock, lastEduBlock.nextSibling);
}

// createExp() : 새로운 경력을 추가
function createExp() {
  let expCorpName = $('#expCorpName').val();
  let expPosition = $('#expPosition').val();
  let expContent = $('#expContent').val();
  let expStartDate = $('#expStartDate').val();
  let expEndDate = $('#expEndDate').val();
  
  if(expCorpName == "") {
  alert("회사명을 입력하세요.");
  $('#expCorpName').focus()
  return false;
  }
  if(expPosition == "") {
  alert("직무를 입력하세요.");
  $('#expPosition').focus();
  return false;
  }
  if(expContent == "") {
  alert("경력관련 상세정보를 입력하세요.");
  $('#expContent').focus();
  return false;
  }
  if(expStartDate == "") {
  alert("경력 시작일을 입력하세요.");
  $('#expStartDate').focus();
  return false;
  }
  if(expEndDate == "") {
  alert("경력 종료일을 입력하세요.");
  $('#expEndDate').focus();
  return false;
  }
  
  document.expForm.action = "exp-create";
  document.expForm.method = "POST";
  document.expForm.submit();
  console.log(">>>>>>>> exp create success");
}

function editExp(expId) {
    $('#expId'+expId).val(expId);
    console.log(">>>>>>>>>>>> " + expId);
    const expBlock = document.querySelector(`#expBlock${expId}`);
    const inputs = expBlock.querySelectorAll('input[type="text"]');
    inputs.forEach(input => input.removeAttribute('disabled'));
    expBlock.querySelectorAll('input[type="date"]').forEach(input => input.style.display = 'block');
    expBlock.querySelectorAll('.year-span').forEach(span => span.style.display = 'none');
}

function updateExp(expId) {
  console.log(">>> updateExp(expId) : " + expId);
  $('#expId'+expId).val(expId);
  console.log($('#expId'+expId).attr('value'));
  
    let expCorpName = $('#expCorpName'+expId).val();
  let expPosition = $('#expPosition'+expId).val();
  let expContent = $('#expContent'+expId).val();
  let expStartDate = $('#expStartDate'+expId).val();
  let expEndDate = $('#expEndDate'+expId).val();
  
  if(expCorpName == "") {
  alert("회사명을 입력하세요.");
  $('#expCorpName'+expId).focus()
  return false;
  }
  if(expPosition == "") {
  alert("직무를 입력하세요.");
  $('#expPosition'+expId).focus();
  return false;
  }
  if(expContent == "") {
  alert("경력관련 상세정보를 입력하세요.");
  $('#expContent'+expId).focus();
  return false;
  }
  if(expStartDate == "") {
  alert("경력 시작일을 입력하세요.");
  $('#expStartDate'+expId).focus();
  return false;
  }
  if(expEndDate == "") {
  alert("경력 종료일을 입력하세요.");
  $('#expEndDate'+expId).focus();
  return false;
  }
  
  document.expForm.action = "exp-update";
  document.expForm.method = "POST";
  document.expForm.submit();
}

function deleteExp(expId) {
  console.log(">>> deleteExp(expId) : " + expId);
  $('#expId'+expId).val(expId);
  console.log($('#expId'+expId).attr('value'));
  document.expForm.action = "exp-delete";
  document.expForm.method = "POST";
  document.expForm.submit();
  console.log(">>>>>>>> exp delete success");
}

