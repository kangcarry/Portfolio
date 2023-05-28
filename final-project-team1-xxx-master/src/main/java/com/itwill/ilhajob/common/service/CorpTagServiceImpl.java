package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.common.dto.AppDto;
import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.common.entity.CorpTag;
import com.itwill.ilhajob.common.repository.CorpTagRepository;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Corp;

@Service
public class CorpTagServiceImpl implements CorpTagService{

	private final CorpTagRepository corpTagRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CorpTagServiceImpl(CorpTagRepository corpTagRepository, ModelMapper modelMapper) {
		this.corpTagRepository = corpTagRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public void insertCorpTag(CorpTagDto corpTagDto) {
		CorpTag corpTag = modelMapper.map(corpTagDto, CorpTag.class);
		Optional<CorpTag> found = corpTagRepository.findById(corpTagDto.getId());
		if(found.isPresent()) {
			new Exception("이미 같은 태그가 존재합니다");
		}
		corpTagRepository.save(corpTag);
	}
	
	@Override
	public void deleteCorpTag(Long id) {
		//CorpTag corpTag = modelMapper.map(corpTagDto, CorpTag.class);
		Optional<CorpTag> found = corpTagRepository.findById(id);
		if(!found.isPresent()) {
			new Exception("삭제할 태그가 없습니다");
		}
		corpTagRepository.deleteById(id);
	}
	
	@Override
	public List<CorpTagDto> selectAllByCorpId(Long corpid) {
		List<CorpTag> corpTagList = corpTagRepository.findByCorpId(corpid);
		return corpTagList.stream()
				.map(corpTag ->modelMapper.map(corpTag, CorpTagDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public Page<CorpTagDto> selectListByTagId(Long tagId, Pageable pageable) {
		Page<CorpTag> corpTagList = corpTagRepository.findByTagId(tagId, pageable);
		return corpTagList.map(corpTag -> modelMapper.map(corpTag, CorpTagDto.class));
	}

	@Override
	public List<CorpTagDto> selectAll() {
		List<CorpTag> corpTagList = corpTagRepository.findAll();
		return corpTagList.stream()
				.map(corpTag ->modelMapper.map(corpTag, CorpTagDto.class))
				.collect(Collectors.toList());
	}
}
