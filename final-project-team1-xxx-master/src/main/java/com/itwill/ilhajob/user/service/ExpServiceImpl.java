package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.itwill.ilhajob.user.dto.ExpDto;
import com.itwill.ilhajob.user.entity.Exp;
import com.itwill.ilhajob.user.repository.ExpRepository;
@Service
public class ExpServiceImpl implements ExpService{
	
	private final ExpRepository expRepository;
	private final ModelMapper modelMapper;
	
	public ExpServiceImpl(ExpRepository expRepository, ModelMapper modelMapper) {
		this.expRepository = expRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public ExpDto createExp(ExpDto expDto) {
		Exp exp = modelMapper.map(expDto, Exp.class);
		exp = expRepository.save(exp);
		return modelMapper.map(exp, ExpDto.class);
	}

	@Override
	public ExpDto updateExp(Long id, ExpDto expDto) {
		Exp exp = expRepository.findById(id).get();
		exp.setExpContent(expDto.getExpContent());
		exp.setExpCorpName(expDto.getExpCorpName());
		exp.setExpPosition(expDto.getExpPosition());
		exp.setExpStartDate(expDto.getExpStartDate());
		exp.setExpEndDate(expDto.getExpEndDate());
		expRepository.save(exp);
		return modelMapper.map(exp, ExpDto.class);
	}

	@Override
	public void removeById(Long id) {
		expRepository.deleteById(id);
	}

	@Override
	public List<ExpDto> findExpListByUserId(Long userId) {
		List<Exp> expList = expRepository.findByUserIdOrderByExpEndDateDesc(userId);
		return expList.stream().map(exp -> modelMapper.map(exp, ExpDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ExpDto> findAll() {
		List<Exp> expList = expRepository.findAll();
		return expList.stream().map(exp -> modelMapper.map(exp, ExpDto.class)).collect(Collectors.toList());
	}
}
