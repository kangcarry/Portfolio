$(document).ready(function() {
  // Get saved data from localStorage
  let email;
  let remember = localStorage.getItem('remember');
  let separate = localStorage.getItem('separate');
  console.log(remember);
  console.log(separate);
  
	if (separate == 'user') {
		email = localStorage.getItem('email');
		$('#email').val(email);
		$('#remember').prop('checked', true);
	}

	$(document).on('click', '#corp-btn', function() {
		if (separate == 'corp') {
			email = localStorage.getItem('id');
			$('#id').val(email);
			$('#remember').prop('checked', true);
		}else {
			$('#remember').prop('checked', false);
			$('.jquery-modal.blocker.current input#id').val('');
		}
	});

	$(document).on('click', '#candidate-btn', function() {
		if (separate == 'user') {
			email = localStorage.getItem('email');
			$('#email').val(email);
			$('#remember').prop('checked', true);
		}else {
			$('#remember').prop('checked', false);
			$('.jquery-modal.blocker.current input#email').val('');
		}
	});
	
	/*console.log(email);
  // Check if remember checkbox was checked previously
  if (remember == 'true' && $('#candidate-btn').hasClass('btn-style-seven')) {
    // Set email and password fields to saved values
    $('#email').val(email);
    $('#remember').prop('checked', true);
  } else if (remember == 'true' && $('#corp-btn').hasClass('btn-style-seven')) {
    $('#id').val(email);
    $('#remember').prop('checked', true);
  }*/
});

  // Save email and password to localStorage on login
$('#log-in').click(function() {
	let email;
	let remember = $('#remember').is(':checked');
	let separate = $('#separate').val();
	if(remember){
		if ($('#candidate-btn').hasClass('btn-style-seven')) {
			// Set email and password fields to saved values
			email = $('#email').val();
			localStorage.setItem('email', email);
		} else if ($('#corp-btn').hasClass('btn-style-seven')) {
			email = $('#id').val();
			localStorage.setItem('id', email);
		}
		localStorage.setItem('separate', separate);
		localStorage.setItem('remember', remember);
	}else {
		if (localStorage.getItem('email') != null) {
			localStorage.removeItem('email');
		} else if (localStorage.getItem('id') != null) {
			localStorage.removeItem('id');
		}
		localStorage.removeItem('remember');
		localStorage.removeItem('separate');
	}
	
});
