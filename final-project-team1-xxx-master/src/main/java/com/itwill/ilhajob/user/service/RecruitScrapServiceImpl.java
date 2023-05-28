package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.corp.repository.RecruitRepository;
import com.itwill.ilhajob.corp.service.RecruitService;
import com.itwill.ilhajob.user.dto.RecruitScrapDto;
import com.itwill.ilhajob.user.entity.RecruitScrap;
import com.itwill.ilhajob.user.repository.RecruitScrapRepository;

@Service
public class RecruitScrapServiceImpl implements RecruitScrapService{
	

	@Autowired
	RecruitScrapRepository recruitScrapRepository;
	@Autowired
	ModelMapper modelMapper;
	
	public void insertScrap(RecruitScrapDto recruiScrapDto) throws Exception{
		List<RecruitScrap> countList = recruitScrapRepository.findByUserId(recruiScrapDto.getUser().getId());
		for (RecruitScrap recruitScrap : countList) {
			if(recruitScrap.getRecruit().getId()==recruiScrapDto.getRecruit().getId()) {
				throw new Exception("이미 스크랩한 공고입니다.");
			}
		}
		RecruitScrap scrap =  modelMapper.map(recruiScrapDto, RecruitScrap.class);
		recruitScrapRepository.save(scrap);
	}
	
	public void deleteScrap(long id) throws Exception{
		Optional<RecruitScrap> count = recruitScrapRepository.findById(id);
		if(count.isPresent()) {
			recruitScrapRepository.deleteById(id);
		}else {
			throw new Exception("삭제하려는 스크랩이 없습니다.");
		}
	}
	
	public void deleteScrapByUserIdAndRecruitId(long userId, long recruitId) throws Exception{
		List<RecruitScrap> recruitScrapList = recruitScrapRepository.findByUserId(userId);
		RecruitScrapDto scrap = null;
		List<RecruitScrapDto> scrapList = recruitScrapList.stream()
											.map(recruitScrap -> modelMapper.map(recruitScrap, RecruitScrapDto.class))
											.collect(Collectors.toList());
		for (RecruitScrapDto recruitScrapDto : scrapList) {
			if((recruitScrapDto.getRecruit().getId()==recruitId)) {
				scrap = recruitScrapDto;
			}
		}
		if(scrap!=null) {
			recruitScrapRepository.deleteById(scrap.getId());
		}else {
			throw new Exception("삭제하려는 스크랩이 없습니다.");
		}
	}
	
	//유저의 공고스크랩 리스트
	public List<RecruitScrapDto> sellectByUserId(long userId){
		List<RecruitScrap> scrapList = recruitScrapRepository.findByUserId(userId);
		return scrapList.stream()
				.map(recruitScrap -> modelMapper.map(recruitScrap, RecruitScrapDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public RecruitScrapDto sellectById(long id) {
		Optional<RecruitScrap> scrap = recruitScrapRepository.findById(id);
		RecruitScrapDto recruitScrap = modelMapper.map(scrap.get(), RecruitScrapDto.class);
		return recruitScrap;
	}

	@Override
	public RecruitScrapDto sellectByUserIdAndRecruitId(long userId, long recruitId) {
		List<RecruitScrap> recruitScrapList = recruitScrapRepository.findByUserId(userId);
		RecruitScrapDto scrap = null;
		List<RecruitScrapDto> scrapList = recruitScrapList.stream()
											.map(recruitScrap -> modelMapper.map(recruitScrap, RecruitScrapDto.class))
											.collect(Collectors.toList());
		for (RecruitScrapDto recruitScrapDto : scrapList) {
			if((recruitScrapDto.getRecruit().getId()==recruitId)) {
				scrap = recruitScrapDto;
			}
		}
		return scrap;
	}
	
	
}
