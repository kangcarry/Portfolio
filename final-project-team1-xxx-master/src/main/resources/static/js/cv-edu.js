/**
 * edu
 */
 
 // addEdu() : 새로운 학력 영역을 추가
function addEdu() {
  // 새로운 요소를 생성하고 클래스 이름을 추가
  var newEduBlock = document.createElement("div");
  newEduBlock.classList.add("edu-block");
  // 새로운 요소의 내부 HTML을 설정
  newEduBlock.innerHTML = `
  <div class="resume-block"">
	  <div class="inner">
	  <span class="name">E</span>
	  	<div class="title-box">
	  		  <div class="col-lg-11 col-md-11"></div>
		      <div class="edit-btns col-lg-1 col-md-1">
			      <button type="button" onclick="createEdu()"><span class="la la-check-circle-o"></span></button>      
	      	  </div>
	     </div>
	      	  <div class="row">
			  	<div class="form-group col-lg-6 col-md-6">
		        <label>학교명</label>
				  <input type=text id="eduName" name="eduName" placeholder="학교명을 입력하세요.">
			    </div>
			  	<div class="form-group col-lg-6 col-md-6">
		        <label>전공</label>
				  <input type=text id="eduMajor" name="eduMajor" placeholder="전공을 입력하세요.">
				</div>
			    <div class="form-group col-lg-6 col-md-6">
			    <label>학점</label>
			    <input type="text" id="eduScore" name="eduScore" placeholder="학점을 입력하세요.">
			    </div>
			    <div class="form-group col-lg-6 col-md-6">
			    <label>추가 정보 입력</label>
			    <input type="text" id="eduContent" name="eduContent" placeholder="학력 관련 추가 정보를 입력하세요.">
			    </div>
			    
			    <div class="edit-box">
			  	<div class="form-group col-lg-6 col-md-6">
			    <label>입학일</label><br>
			      <input type="date" class="year" id="eduStartDate" name ="eduStartDate">
			    </div>
			  	<div class="form-group col-lg-6 col-md-6">
			    <label>졸업일</label><br>
			      <input type="date" class="year" id="eduEndDate" name ="eduEndDate">
			    </div>
		        </div>
		  </div>
	 	</div>
    </div>
  `;

  var eduList = document.getElementById("edu-block");
  console.log(">>>>>>>>>> 추가영역 생성 " + eduList);
  var lastEduBlock = eduList.lastElementChild;
  eduList.insertBefore(newEduBlock, lastEduBlock.nextSibling);
}

// deleteEdu() : 학력 삭제
function deleteEdu(eduId) {
	//confirm(">>>>>>> eduId : " + eduId + "typeof : " + typeof eduId);
	$('#eduId'+eduId).val(eduId);
	console.log($('#eduId'+eduId).attr('value'));
	
		$.ajax({
    	url: "edu/" + eduId,
    	type: "DELETE",
    	data: $('.default-form').serialize(),
    	success: function(response) {
      	console.log(response);
      	//alert("success");
      	location.reload();
   	 },
    	error: function(xhr, status, error) {
     	console.log(error);
      	//alert("fail");
    }
  });
}

// createEdu() : 새로운 학력을 추가
function createEdu() {
    let eduName = $('#eduName').val();
    let eduMajor = $('#eduMajor').val();
    let eduScore = $('#eduScore').val();
    let eduContent = $('#eduContent').val();
    let eduStartDate = $('#eduStartDate').val();
    let eduEndDate = $('#eduEndDate').val();
    var cvId = document.querySelector('.chosen-select').value;
    document.querySelector('#cvId').value = cvId;
    let pattern = /^(?:4(?:\.[0-4]?[0-9]?)?|[0-3](?:\.[0-9]{1,2})?|0?\.[0-9]{1,2})$/;
    
	if (eduName == "") {
		alert("학교를 입력하세요.");
		$('#eduName').focus();
		return false;
	}
	if (eduMajor == "") {
		alert("전공을 입력하세요.");
		$('#eduMajor').focus();
		return false;
	}
	if (eduScore == "") {
		alert("학점을 입력하세요.");
		$('#eduScore').focus();
		return false;
	}
	if (!pattern.test(eduScore)) {
		alert("유효한 학점이 아닙니다. 다시 입력하세요.");
		$('#eduScore').focus();
		return false;
	}
	if (eduContent == "") {
		alert("추가 정보를 입력하세요.");
		$('#eduContent').focus();
		return false;
	}
	if (eduStartDate == "") {
		alert("입학일을 입력하세요.");
		$('#eduStartDate').focus();
		return false;
	}
	if (eduEndDate == "") {
		alert("졸업일을 입력하세요.");
		$('#eduEndDate').focus();
		return false;
	}

  $.ajax({
    url: "edu",
    type: "POST",
    data: {
      eduName: eduName,
      eduMajor: eduMajor,
      eduScore: eduScore,
      eduContent: eduContent,
      eduStartDate: eduStartDate,
      eduEndDate: eduEndDate,
      id : cvId
    },
    success: function(response) {
      //alert("success");
      location.reload();
    },
    error: function(xhr, status, error) {
      //alert("fail");
      console.log("fail");
    }
  });
}

// editEdu() : 학력을 수정하기 위해 input 태그로 변경
function editEdu(eduId) {
	$('#eduId'+eduId).val(eduId);
    console.log(">>>>>>>>>>>> " + eduId);
    const EduBlock = document.querySelector(`#eduBlock${eduId}`);
    const inputs = EduBlock.querySelectorAll('input[type="text"]');
    inputs.forEach(input => input.removeAttribute('disabled'));
    EduBlock.querySelectorAll('input[type="date"]').forEach(input => input.style.display = 'block');
    EduBlock.querySelectorAll('.year-span').forEach(span => span.style.display = 'none');
}

// updateEdu() : 학력 수정
function updateEdu(eduId) {
	let eduName = $("#eduName" + eduId).val();
	let eduMajor = $("#eduMajor" + eduId).val();
	let eduScore = $("#eduScore" + eduId).val();
	let eduContent = $("#eduContent" + eduId).val();
	let eduStartDate = $("#eduStartDate" + eduId).val();
	let eduEndDate = $("#eduEndDate" + eduId).val();
    let cvId = $('.chosen-select').val();
    let pattern = /^(?:4(?:\.[0-4]?[0-9]?)?|[0-3](?:\.[0-9]{1,2})?|0?\.[0-9]{1,2})$/;

	console.log(">>>>> eduId : " + eduId);
    console.log(">>>>> eduName : " + eduName);
    console.log(">>>>> eduMajor : " + eduMajor);
    console.log(">>>>> eduScore : " + eduScore);
    console.log(">>>>> eduContent : " + eduContent);
    console.log(">>>>> eduStartDate : " + eduStartDate);
    console.log(">>>>> eduEndDate : " + eduEndDate);
    
	if (eduName == "") {
	alert("학교를 입력하세요.");
		$('#eduName' + eduId).focus();
		return false;
	}
	if (eduMajor == "") {
		alert("전공을 입력하세요.");
		$('#eduMajor' + eduId).focus();
		return false;
	}
	if (eduScore == "") {
		alert("학점을 입력하세요.");
		$('#eduScore' + eduId).focus();
		return false;
	}
	if (!pattern.test(eduScore)) {
	alert("유효한 학점이 아닙니다. 다시 입력하세요.");
	$('#eduScore').focus();
	return false;
	}
	if (eduContent == "") {
		alert("추가 정보를 입력하세요.");
		$('#eduContent' + eduId).focus();
		return false;
	}
	if (eduStartDate == "") {
		alert("입학일을 입력하세요.");
		$('#eduStartDate' + eduId).focus();
		return false;
	}
	if (eduEndDate == "") {
		alert("졸업일을 입력하세요.");
		$('#eduEndDate' + eduId).focus();
		return false;
	}
    
    $.ajax({
    url: "edu-update",
    type: "PUT",
    data: {
	  eduId : eduId,
      eduName: eduName,
      eduMajor: eduMajor,
      eduScore: eduScore,
      eduContent: eduContent,
      eduStartDate: eduStartDate,
      eduEndDate: eduEndDate,
      id : cvId
    },
    success: function(response) {
      //alert("success");
      location.reload();
    },
    error: function(xhr, status, error) {
      //alert("fail");
      console.log("fail");
    }
  });
}
