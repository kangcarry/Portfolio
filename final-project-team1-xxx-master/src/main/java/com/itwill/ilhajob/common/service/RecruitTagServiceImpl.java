package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.common.dto.RecruitTagDto;
import com.itwill.ilhajob.common.entity.RecruitTag;
import com.itwill.ilhajob.common.repository.RecruitTagRepository;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Recruit;

@Service
public class RecruitTagServiceImpl implements RecruitTagService{

	private final RecruitTagRepository recruitTagRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public RecruitTagServiceImpl(RecruitTagRepository recruitTagRepository, ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.recruitTagRepository = recruitTagRepository;
	}
	
	@Override
	public void insertRecruitTag(RecruitTagDto recruitTagDto) {
		RecruitTag recruitTag = modelMapper.map(recruitTagDto, RecruitTag.class);
		Optional<RecruitTag> found = recruitTagRepository.findById(recruitTag.getId());
		if(found.isPresent()) {
			new Exception("이미 같은 태그가 존재합니다");
		}
		recruitTagRepository.save(recruitTag);
	}
	
	@Override
	public void deleteRecruitTag(Long id) {
		Optional<RecruitTag> found = recruitTagRepository.findById(id);
		if(!found.isPresent()) {
			new Exception("삭제할 태그가 없습니다");
		}
		recruitTagRepository.deleteById(id);
	}
	
	@Override
	public List<RecruitTagDto> selectAllByRecruitId(long recruitId) {
		List<RecruitTag> recruitTagList = recruitTagRepository.findByRecruitId(recruitId);
		return recruitTagList.stream()
				.map(recruitTag ->modelMapper.map(recruitTag, RecruitTagDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RecruitTagDto> selectAll() {
		List<RecruitTag> recruitTagList = recruitTagRepository.findAll();
		return recruitTagList.stream()
				.map(recruitTag ->modelMapper.map(recruitTag, RecruitTagDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<RecruitTagDto> selectAllBytagId(long tagId) {
		List<RecruitTag> recruitTagList = recruitTagRepository.findByTagId(tagId);
		return recruitTagList.stream()
				.map(recruitTag ->modelMapper.map(recruitTag, RecruitTagDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public Page<RecruitDto> getRecruitTagList(int Page, int size, long tagId) throws Exception {
		PageRequest pageable=PageRequest.of(Page, size);
		Page<Recruit> recruitTagPage=recruitTagRepository.findRecruitsByTagId(tagId, pageable);
		return recruitTagPage.map(recruitTag->modelMapper.map(recruitTag, RecruitDto.class));
	}

	@Override
	public Page<RecruitDto> selectRecruitsByTagId(long tagId, Pageable pageable) {
	    Page<Recruit> recruitList = recruitTagRepository.findRecruitsByTagId(tagId, pageable);
	    return recruitList.map(recruit -> modelMapper.map(recruit, RecruitDto.class));
	}
}
