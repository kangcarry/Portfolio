package com.itwill.ilhajob.corp.service;



import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.corp.exception.NotFoundException;
import com.itwill.ilhajob.corp.repository.RecruitRepository;

@Service
@Transactional
public class RecruitServiceImpl implements RecruitService {

	private RecruitRepository recruitRepository;
	private ModelMapper modelMapper;

	@Autowired
	public RecruitServiceImpl(RecruitRepository recruitRepository, ModelMapper modelMapper) {
		this.recruitRepository = recruitRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<RecruitDto> findRecruitAll() throws Exception {
		List<Recruit> recruitList = recruitRepository.findAll();
		return recruitList.stream().map(recruit -> modelMapper.map(recruit, RecruitDto.class))
				.collect(Collectors.toList());
	}

	@Transactional
	@Override
	public List<RecruitDto> findAllByCorpId(long id) throws Exception {
		List<Recruit> recruitList = recruitRepository.findByCorpId(id);
		return recruitList.stream().map(recruit -> modelMapper.map(recruit, RecruitDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public RecruitDto findRecruit(long id) throws Exception {
		Optional<Recruit> optionalRecurit = recruitRepository.findById(id);
		Recruit findRecruit = optionalRecurit.get();
		return modelMapper.map(findRecruit, RecruitDto.class);
	}

	@Override
	public RecruitDto create(RecruitDto recruitDto) throws Exception {
		Recruit recruit = modelMapper.map(recruitDto, Recruit.class);
		recruit = recruitRepository.save(recruit);
		return modelMapper.map(recruit, RecruitDto.class);
	}

	@Transactional
	@Override
	public RecruitDto update(RecruitDto recruitDto) throws Exception {
		Optional<Recruit> optionalRecruit = recruitRepository.findById(recruitDto.getId());
		Recruit recruit = optionalRecruit.get();
		recruitDto.setId(recruit.getId());
		modelMapper.map(recruitDto, recruit);
		recruit = recruitRepository.save(recruit);
		return modelMapper.map(recruit, RecruitDto.class);
	}

	@Transactional
	@Override
	public void remove(long id) throws Exception {
		recruitRepository.deleteById(id);
		recruitRepository.flush();
	}

	// open jobs -3: 공고 개수 불러오기
	@Override
	public Long countByCorpId(Long id) throws Exception {
		return recruitRepository.countByCorpId(id);
	}

	// 마감일
	@Override
	public String getStatus(Date rcDeadLine) throws Exception {
		// 현재 시간으로 초기화
		LocalDate today = LocalDate.now();
		LocalDate deadLineLocalDate = rcDeadLine.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		long daysUntilDeadLine = ChronoUnit.DAYS.between(today, deadLineLocalDate);

		if (daysUntilDeadLine < 0) {
			return "마감";
		} else if (daysUntilDeadLine == 0) {
			return "마감 예정";
		} else {
			return "D-" + daysUntilDeadLine;
		}
	}
	
	// recruitList 페이징 기능
	@Override
	public Page<RecruitDto> getRecruitList(int page, int size) throws Exception {
		PageRequest pageable=PageRequest.of(page, size);
		Page<Recruit> recruitPage=recruitRepository.findAll(pageable);
		return recruitPage.map(recruit->modelMapper.map(recruit, RecruitDto.class));
	}

	@Override
	public Page<RecruitDto> findAll(Pageable pageable) {
		Page<Recruit> recruitList=recruitRepository.findAll(pageable);
		return recruitList.map(recruit->modelMapper.map(recruit, RecruitDto.class));
	}

	//readCount 증가 기능-테스트 완료
	@Override
	public void increaseReadCount(Long id) throws Exception {
		//일단 recruit 찾아오기
		   Optional<Recruit> recruitDto = recruitRepository.findById(id);
	       if(recruitDto.isPresent()) {
	    	   Recruit recruit=recruitDto.get(); 
	    	   Long readCount = recruit.getRcReadCount();
	    	   recruit.setRcReadCount(readCount + 1);
	    	   recruitRepository.save(recruit);
	       }else {
	    	   throw new Exception("Recruit not found with id: " + id);
	       }
		   
	    }

	//공고 asc, desc
	@Override
	public List<RecruitDto> getRecruitListOrderByDeadline(String order) throws Exception {
		List<Recruit> recruitList;
		if(order.equals("desc")) {
			recruitList=recruitRepository.findAllByOrderByRcDeadlineDesc();
		}else {
			recruitList=recruitRepository.findAllByOrderByRcDeadlineAsc();
		}
		return recruitList.stream().map(recruit -> modelMapper.map(recruit, RecruitDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Page<RecruitDto> searchRcTitle(String rcTitle, int page, int size) throws Exception {
		PageRequest pageable=PageRequest.of(page, size);
		Page<Recruit> recruitPage=recruitRepository.findByRcTitleContaining(rcTitle, pageable);
		return recruitPage.map(recruit->modelMapper.map(recruit, RecruitDto.class));
	}

	@Override
	public List<RecruitDto> searchRcTitle(String rcTitle) {
		List<Recruit> searchRcList = recruitRepository.findByRcTitleContainingIgnoreCase(rcTitle);
		return searchRcList.stream().map(recruit -> modelMapper.map(recruit, RecruitDto.class))
				.collect(Collectors.toList());
	}
	
	
	
}
