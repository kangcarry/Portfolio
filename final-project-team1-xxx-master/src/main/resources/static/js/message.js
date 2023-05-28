function deleteMessage(messageId){
	var input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'messageId';
    input.value = messageId;
    document.getElementById('listId').appendChild(input);
	
	
	document.f.action = "alerts-remove";
	document.method='POST';
	document.f.submit();
	
}
