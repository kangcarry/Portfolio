package com.itwill.ilhajob.corp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.ManagerDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Manager;
import com.itwill.ilhajob.corp.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {
	
private final ManagerRepository managerRepository;
private final ModelMapper modelMapper;
	
public ManagerServiceImpl(ManagerRepository managerRepository, ModelMapper modelMapper) {
	this.managerRepository = managerRepository;
	this.modelMapper = modelMapper;
	}
		@Override
		public ManagerDto create(ManagerDto managerDto) throws Exception {
			/*
			Optional<Manager> found = managerRepository.findById(managerDto.getId());
			if(found.isPresent()) {
				 throw new Exception("Manager with id " + managerDto.getId() + " already exists.");
			}
			*/
			Manager manager = modelMapper.map(managerDto, Manager.class);
			manager.setId(null);
			Manager saveManager = managerRepository.save(manager);
			ManagerDto saveManagerDto = modelMapper.map(saveManager, ManagerDto.class);
			
			return saveManagerDto;
		}
	
	@Transactional
	@Override
	public ManagerDto findManager(Long id) throws Exception {
		Optional<Manager> manager = managerRepository.findById(id);
		if(!manager.isPresent()) {
			new Exception("해당 매니저가 없습니다");
		}
		Manager manager1 = manager.get();
		ManagerDto managerDto = modelMapper.map(manager1, ManagerDto.class);
		return managerDto;
	}

	@Override
	public ManagerDto update(ManagerDto managerDto) throws Exception {
		Optional<Manager> found = managerRepository.findById(managerDto.getId());
		if(!found.isPresent()) {
			new Exception("해당 매니저는 없습니다");
		}
			Manager manager = modelMapper.map(managerDto,Manager.class);
			Manager updateManager = managerRepository.save(manager);
			ManagerDto updateManagerDto = modelMapper.map(updateManager, ManagerDto.class);
			return updateManagerDto;
		}
	

	@Override
	public void remove(Long id) throws Exception {
		managerRepository.deleteById(id);
	}

	@Override
	public List<ManagerDto> findManagerByCorpID(Long corpId) {
		List<Manager> managerList =   managerRepository.findByCorpId(corpId);
		
		return managerList.stream()
				.map(manager -> modelMapper.map(manager, ManagerDto.class))
				.collect(Collectors.toList());
	}
	
}
