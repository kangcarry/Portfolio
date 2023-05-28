package com.itwill.ilhajob.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;import javax.swing.event.TableColumnModelListener;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.user.dto.AwardsDto;
import com.itwill.ilhajob.user.dto.CvDto;
import com.itwill.ilhajob.user.dto.EduDto;
import com.itwill.ilhajob.user.dto.ExpDto;
import com.itwill.ilhajob.user.entity.Awards;
import com.itwill.ilhajob.user.entity.Cv;
import com.itwill.ilhajob.user.entity.Edu;
import com.itwill.ilhajob.user.entity.Exp;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.repository.CvRepository;
import com.itwill.ilhajob.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CvServiceImpl implements CvService{

	private final CvRepository cvRepository;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	
	@Autowired
	public CvServiceImpl(CvRepository cvRepository, ModelMapper modelMapper, UserRepository userRepository) {
		this.cvRepository = cvRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public CvDto saveCv(CvDto cvDto) {
		Cv cv = modelMapper.map(cvDto, Cv.class);
		cv = cvRepository.save(cv);
		return modelMapper.map(cv, CvDto.class);
	}
	
	public CvDto updateCv(Long id, CvDto cvDto) {
		Cv cv = cvRepository.findById(id).get();
		cv.setCvName(cvDto.getCvName());
		cv.setCvDescription(cvDto.getCvDescription());
		cv.setCvPortfolio(cvDto.getCvPortfolio());
		cvRepository.save(cv);
		return modelMapper.map(cv, CvDto.class);
	};

	@Override
	public void removeById(Long id) {
		cvRepository.deleteById(id);
		cvRepository.flush();
	}

	@Override
	public CvDto findCvById(Long id) {
		Cv cv = cvRepository.findById(id).get();
		return modelMapper.map(cv, CvDto.class);
	}

	@Override
	public List<CvDto> findByUserId(Long userId) {
		List<Cv> cvList = cvRepository.findByUserId(userId);
		return cvList.stream().map(cv -> modelMapper.map(cv, CvDto.class)).collect(Collectors.toList());
	}
	 
	@Override
	public List<CvDto> findCvAll() {
		List<Cv> cvList = cvRepository.findAll();
		return cvList.stream().map(cv -> modelMapper.map(cv, CvDto.class)).collect(Collectors.toList());
	}
}