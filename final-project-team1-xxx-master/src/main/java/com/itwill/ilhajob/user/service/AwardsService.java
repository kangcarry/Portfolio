package com.itwill.ilhajob.user.service;

import java.util.List;

import com.itwill.ilhajob.user.dto.AwardsDto;

public interface AwardsService {
	
	// create
	AwardsDto createAwards(AwardsDto awardsDto);
	
	// update
	AwardsDto updateAwards(Long id, AwardsDto awardsDto);
	
	// remove by awards id
	void removeAwardsById(Long id);
	
	// find awards by awards id
	AwardsDto findAwardsById(Long id);

	// find awards list by user id
	List<AwardsDto> findAwardsByUserId(Long userId);
	
	// find all awards list
	List<AwardsDto> findAll();
}
