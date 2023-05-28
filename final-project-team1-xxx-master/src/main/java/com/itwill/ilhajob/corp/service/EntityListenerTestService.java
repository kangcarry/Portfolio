package com.itwill.ilhajob.corp.service;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionManager;

import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.common.repository.AppRepository;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.repository.MessageRepository;
import com.itwill.ilhajob.user.repository.UserRepository;

@Service
public class EntityListenerTestService {
	@Autowired
	private AppRepository appRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private EntityManager entityManager;
	
	
	@Transactional
	public void createUserMessage() {
		User user=userRepository.findById(1L).get();
		App apply = App.builder()
				.appCreateDate(LocalDateTime.now())
				.appStatus(0)
				.cv(null)
				.recruit(null)
				.user(user)
				.build();
		appRepository.save(apply);
		
		
		
		
			
	}
}
