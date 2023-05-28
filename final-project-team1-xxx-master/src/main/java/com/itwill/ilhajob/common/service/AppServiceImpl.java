package com.itwill.ilhajob.common.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.entity.App;
import com.itwill.ilhajob.common.repository.AppRepository;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.corp.repository.RecruitRepository;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.repository.MessageRepository;

@Service
public class AppServiceImpl implements AppService {

	private final AppRepository appRepository;
	private final RecruitRepository recruitRepository;
	private final MessageRepository messageRepository; 
	private final ModelMapper modelMapper;
	
	@Autowired	
	public AppServiceImpl(AppRepository appRepository, 
							RecruitRepository recruitRepository, 
							MessageRepository messageRepository,
							ModelMapper modelMapper) {
		this.appRepository = appRepository;
		this.recruitRepository = recruitRepository;
		this.messageRepository = messageRepository;
		this.modelMapper = modelMapper;
	}
	
	//지원하기시 공고rcAppCount(지원자숫자) 증가
	@Transactional
	@Override
	public void insertApp(AppDto appDto) {
		RecruitDto recruitDto = appDto.getRecruit();
		recruitDto.setRcAppCount(recruitDto.getRcAppCount()+1);
		Recruit recruit = modelMapper.map(recruitDto, Recruit.class);
		recruitRepository.saveAndFlush(recruit);
		
		appDto.setRecruit(modelMapper.map(recruit, RecruitDto.class));
		App createApp = modelMapper.map(appDto, App.class);
		appRepository.save(createApp);
		
		Message applyMessage = new Message();
		applyMessage.setMessageTitle("["+createApp.getRecruit().getRcTitle()+"] 이력서 접수 완료.");
		applyMessage.setMessageContents(createApp.getUser().getUserName()+"님의 합격을 기원합니다.");
		applyMessage.setMessageDate(LocalDateTime.now());
		applyMessage.setUser(createApp.getUser());
		applyMessage = messageRepository.save(applyMessage);
	}

	//주로 appStatus 변경
	@Transactional
	@Override
	public AppDto updateApp(long id, int appStatus) {
		/*
		 * appStatus
		 * 0 : 접수중(디폴트)
		 * 1 : [제출된 이력서 클릭 시]접수확인
		 * 2 : [승인 버튼 클릭 시]합격
		 * ?? 3 : [거절 버튼 클릭 시]불합격
		 */
		App findApp = appRepository.findById(id).get();
		AppDto updateDto = modelMapper.map(findApp, AppDto.class);
		if(appStatus==1) {
			updateDto.setAppStatus(appStatus);
			modelMapper.map(updateDto, findApp);
			findApp = appRepository.save(findApp);
			Message applyMessage = new Message();
			applyMessage.setMessageTitle("["+findApp.getRecruit().getRcTitle()+"] 접수확인");
			applyMessage.setMessageContents("채용담당자가 이력서를 확인했습니다.");
			applyMessage.setMessageDate(LocalDateTime.now());
			applyMessage.setUser(findApp.getUser());
			applyMessage = messageRepository.save(applyMessage);
		}
		else if(appStatus==2) {
			updateDto.setAppStatus(appStatus);
			modelMapper.map(updateDto, findApp);
			findApp = appRepository.save(findApp);
			Message applyMessage = new Message();
			applyMessage.setMessageTitle("["+findApp.getRecruit().getRcTitle()+"] 서류합격");
			applyMessage.setMessageContents(findApp.getUser().getUserName()+"님은 서류에 합격 하셨습니다.");
			applyMessage.setMessageDate(LocalDateTime.now());
			applyMessage.setUser(findApp.getUser());
			applyMessage = messageRepository.save(applyMessage);
		}
		else if(appStatus==3) {
			updateDto.setAppStatus(appStatus);
			modelMapper.map(updateDto, findApp);
			findApp = appRepository.save(findApp);
			Message applyMessage = new Message();
			applyMessage.setMessageTitle("["+findApp.getRecruit().getRcTitle()+"] 서류불합격");
			applyMessage.setMessageContents(findApp.getUser().getUserName()+"님은 서류에 불합격 하셨습니다.");
			applyMessage.setMessageDate(LocalDateTime.now());
			applyMessage.setUser(findApp.getUser());
			applyMessage = messageRepository.save(applyMessage);
		}
		else {
			
		}
			return modelMapper.map(findApp, AppDto.class);
	}
	
	@Transactional
	@Override
	public void deleteApp(Long id) {
		App app = appRepository.findById(id).get();
		AppDto appDto = modelMapper.map(app, AppDto.class);
		RecruitDto recruitDto = appDto.getRecruit();
		recruitDto.setRcAppCount(recruitDto.getRcAppCount()-1);
		Recruit recruit = modelMapper.map(recruitDto, Recruit.class);
		recruitRepository.saveAndFlush(recruit);
		
		appRepository.deleteById(id);
	}

	/*
	 * 지원받은 or 지원한 이력서 상세보기(appCvName경로의 json파일)
	 */
	@Override
	public AppDto findById(Long id){
		App findApp = appRepository.findById(id).get();
		return modelMapper.map(findApp,AppDto.class);
	}
	
	/*
	 * 기업이 등록한 공고리스트뷰에서 공고하나 클릭시 appList출력하여 이력서리스트 확인
	 */
	@Transactional
	@Override
	public List<AppDto> findAllByRecruitId(long id) throws Exception {
		List<App> appList = appRepository.findAppsByRecruitId(id);

		return appList.stream()
				.map(app ->modelMapper.map(app, AppDto.class))
				.collect(Collectors.toList());
	}
	
	/*
	 * 구직자가 지원한 appList출력하여 공고리스트 확인
	 */
	@Transactional
	@Override
	public List<AppDto> findAllByUserId(long id) {
		List<App> appList = appRepository.findAppsByUserIdOrderByAppCreateDateDesc(id);
		return appList.stream()
				.map(app ->modelMapper.map(app, AppDto.class))
				.collect(Collectors.toList());
	}
	/*
	 * 이력서리스트에서 특정이력서 클릭시 지원한 공고들 출력
	 * but, 구직자가 지원한 공고리스트 필요
	 */
	@Transactional
	@Override
	public List<AppDto> findAllByCvId(long id) {
		List<App> appList = appRepository.findAppsByCvId(id);
		return appList.stream()
				.map(app ->modelMapper.map(app, AppDto.class))
				.collect(Collectors.toList());
	}

	
}
