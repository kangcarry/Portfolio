package com.itwill.ilhajob.user.service;

import java.util.List;

import com.itwill.ilhajob.user.dto.MessageDto;


public interface MessageService {
	
	MessageDto createMessage(MessageDto message);
	MessageDto updateMessage(MessageDto message);
	//int removeMessageBySeq(Long id);
	//int removeMessageByUserSeq(Long userId);
	//MessageDto findMessageDetail(Long id);
	//List<MessageDto> findMessageList();
	List<MessageDto> fineMessageOfUser(Long userId);
	
}
