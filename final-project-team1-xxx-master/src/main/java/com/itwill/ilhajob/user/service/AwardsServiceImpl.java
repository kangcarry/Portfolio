package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.user.dto.AwardsDto;
import com.itwill.ilhajob.user.entity.Awards;
import com.itwill.ilhajob.user.repository.AwardsRepository;
import com.itwill.ilhajob.user.repository.UserRepository;

@Service
public class AwardsServiceImpl implements AwardsService {

	private final AwardsRepository awardsRepository;
	private final ModelMapper modelMapper;

	public AwardsServiceImpl(AwardsRepository awardsRepository, ModelMapper modelMapper) {
		this.awardsRepository = awardsRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public AwardsDto createAwards(AwardsDto awardsDto) {
		Awards awards = modelMapper.map(awardsDto, Awards.class);
		awardsRepository.save(awards);
		return modelMapper.map(awards, AwardsDto.class);
	}
	
	@Override
	public AwardsDto updateAwards(Long id, AwardsDto awardsDto) {
		Awards awards = awardsRepository.findById(id).get();
		awards.setAwardsName(awardsDto.getAwardsName());
		awards.setAwardsContent(awardsDto.getAwardsContent());
		awards.setAwardsDate(awardsDto.getAwardsDate());
		awardsRepository.save(awards);
		return modelMapper.map(awards, AwardsDto.class);
	}

	@Override
	public void removeAwardsById(Long id) {
		awardsRepository.deleteById(id);
	}

	@Override
	public AwardsDto findAwardsById(Long id) {
		Awards awards = awardsRepository.findById(id).get();
		return modelMapper.map(awards, AwardsDto.class);
	}

	@Override
	public List<AwardsDto> findAwardsByUserId(Long userId) {
		List<Awards> awardsList = awardsRepository.findByUserIdOrderByAwardsDateDesc(userId);
		return awardsList.stream().map(awards -> modelMapper.map(awards, AwardsDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<AwardsDto> findAll() {
		List<Awards> awardsList = awardsRepository.findAll();
		return awardsList.stream().map(awards -> modelMapper.map(awards, AwardsDto.class)).collect(Collectors.toList());
	}
}
