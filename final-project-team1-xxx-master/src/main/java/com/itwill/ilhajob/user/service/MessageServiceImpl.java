package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.user.dto.MessageDto;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.repository.MessageRepository;


@Service
@Transactional
public class MessageServiceImpl implements MessageService{
	
	private final MessageRepository messageRepository;
	private final ModelMapper modelMapper;
	
	public MessageServiceImpl(MessageRepository messageRepository, ModelMapper modelMapper) {
		this.messageRepository = messageRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	public MessageDto createMessage(MessageDto messageDto) {
		Message message = modelMapper.map(messageDto, Message.class);
		message = messageRepository.save(message);
		return modelMapper.map(message, MessageDto.class);
	}
	
	// 쓸 일 있을까/
	@Override
	public MessageDto updateMessage(MessageDto messageDto) {
		Message message = messageRepository.findById(messageDto.getId()).orElse(null);
		messageDto.setMessageContents(message.getMessageContents());
		messageDto.setMessageTitle(message.getMessageTitle());
		messageDto.setMessageDate(message.getMessageDate());
		modelMapper.map(messageDto, message);
		message = messageRepository.save(message);
		return modelMapper.map(message, MessageDto.class);
	}


	@Override
	public List<MessageDto> fineMessageOfUser(Long userId) {
		List<Message> findMsgList = messageRepository.findByUserId(userId);
		List<MessageDto> msgDtoList = findMsgList.stream()
				.map(message -> modelMapper.map(message, MessageDto.class))
				.collect(Collectors.toList());
		return msgDtoList;
	}

}
