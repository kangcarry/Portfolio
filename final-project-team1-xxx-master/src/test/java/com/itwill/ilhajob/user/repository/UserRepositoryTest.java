package com.itwill.ilhajob.user.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.common.repository.AppRepository;
import com.itwill.ilhajob.user.entity.Awards;
import com.itwill.ilhajob.user.entity.Edu;
import com.itwill.ilhajob.user.entity.Exp;
import com.itwill.ilhajob.user.entity.User;

class UserRepositoryTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	UserRepository userRepository;
	@Autowired
	AppRepository appRepository;
	@Autowired
	AwardsRepository awardsRepository;
	@Autowired
	CvRepository cvRepository;
	@Autowired
	EduRepository eduRepository;
	@Autowired
	ExpRepository expRepository;
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	ReviewRepository reviewRepository;
	
	
	@Test
	void userSaveUpdate() {
		User user = User.builder()
					.userEmail("testtt@ttt.com")
					.userPassword("1111")
					.build();
		userRepository.save(user);
//		System.out.println(">>>>>>>>>>>>> awardsList : "+userRepository.findUserByAwardsList(1L));
		
//		findUser.getAwardsList().addAll(Lists.newArrayList(awards1,awards2,awards3));
		
		
//		findUser.getEduList().addAll(Lists.newArrayList(edu1,edu2,edu3));
//		findUser.getExpList().addAll(Lists.newArrayList(exp1,exp2));
		//System.out.println(expRepository.findAll());
				
	}
	
	

}
