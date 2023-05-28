package com.itwill.ilhajob.common.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import com.itwill.ilhajob.FinalProjectTeam1IlhajobApplicationTests;
import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.repository.RecruitRepository;
import com.itwill.ilhajob.corp.service.CorpService;
import com.itwill.ilhajob.user.repository.MessageRepository;

class AppRepositoryTest extends FinalProjectTeam1IlhajobApplicationTests{

	@Autowired
	private AppRepository appRepository;
	@Autowired
	private MessageRepository messageRepository;
	@Autowired
	private RecruitRepository recruitRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	EntityManager entityManager;
	
	
	@Test
	@Transactional
	@Rollback(false)
	void testCreateApp() {
		App apply = App.builder()
						.appCreateDate(LocalDateTime.now())
						.appStatus(0)
						.cv(null)
						.recruit(null)
						.user(null)
						.build();
		appRepository.save(apply);
		
		
		entityManager.flush();
	}
	@Test
	void testCountByCorpId() {
		//System.out.println(appRepository.countByCorpId(1L));
	}

	@Test
	void testFindAppsByRecruitId() {
		//List<App> appList = appRepository.findAppsByRecruitId(1L);
//		List<AppDto> appDtoList = appList.stream()
//									.map(app->modelMapper.map(app, AppDto.class))
//									.collect(Collectors.toList());
	}

	@Test
	void testFindAppsByCvId() {
		
	}

	@Test
	void testFindAppsByUserId() {
		
	}

	@Test
	void testFindAppsByUserEmail() {
		
	}

	@Test
	void testFindAppsByCorpId() {
	//	List<App> appList = appRepository.findAppsByCorpId(1L);
	}

	@Test
	void testFindAppsByCorpLoginId() {
		
	}

}
