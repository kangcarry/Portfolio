package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.user.dto.EduDto;
import com.itwill.ilhajob.user.entity.Edu;
import com.itwill.ilhajob.user.repository.EduRepository;

@Service
public class EduServiceImpl implements EduService {
	
	private final EduRepository eduRepository;
	private final ModelMapper modelMapper;
	
	public EduServiceImpl(EduRepository eduRepository, ModelMapper modelMapper) {
		this.eduRepository = eduRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public EduDto createEdu(EduDto eduDto) {
		Edu edu = modelMapper.map(eduDto, Edu.class);
		eduRepository.save(edu);
		return modelMapper.map(edu, EduDto.class);
	}

	@Override
	public EduDto updateEdu(Long id, EduDto eduDto) {
		Edu edu = eduRepository.findById(id).get();
		edu.setEduName(eduDto.getEduName());
		edu.setEduMajor(eduDto.getEduMajor());
		edu.setEduScore(eduDto.getEduScore());
		edu.setEduStartDate(eduDto.getEduStartDate());
		edu.setEduEndDate(eduDto.getEduEndDate());
		edu.setEduContent(eduDto.getEduContent());
		eduRepository.save(edu);
		return modelMapper.map(edu, EduDto.class);
	}

	@Override
	public void deleteEdu(Long id) {
		eduRepository.deleteById(id);
	}

	@Override
	public List<EduDto> findEduListByUserId(Long userId) {
		List<Edu> eduList = eduRepository.findByUserIdOrderByEduEndDateDesc(userId);
		return eduList.stream().map(edu -> modelMapper.map(edu, EduDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<EduDto> findAll() {
		List<Edu> eduList = eduRepository.findAll();
		return eduList.stream().map(edu -> modelMapper.map(edu, EduDto.class)).collect(Collectors.toList());
	}

	@Override
	public EduDto findById(Long id) {
		Edu edu = eduRepository.findById(id).get();
		return modelMapper.map(edu, EduDto.class);
	}
}