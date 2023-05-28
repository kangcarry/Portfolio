package com.itwill.ilhajob.corp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.common.dto.CorpTagDto;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.dto.RecruitDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.entity.Recruit;
import com.itwill.ilhajob.corp.exception.CorpNotFoundException;
import com.itwill.ilhajob.corp.exception.ExistedCorpException;
import com.itwill.ilhajob.corp.repository.CorpRepository;
import com.itwill.ilhajob.corp.repository.RecruitRepository;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.entity.Review;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.repository.ReviewRepository;



@Service
@Transactional
public class CorpServiceImpl implements CorpService{
	
	private final CorpRepository corpRepository;
	private final ReviewRepository reviewRepository;
	private final RecruitRepository recruitRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CorpServiceImpl(CorpRepository corpRepository, ModelMapper modelMapper,ReviewRepository reviewRepository, RecruitRepository recruitRepository) {
		this.corpRepository = corpRepository;
		this.modelMapper = modelMapper;
		this.reviewRepository = reviewRepository;
		this.recruitRepository=recruitRepository;
	}

	@Override
	public CorpDto create(CorpDto corpDto) throws ExistedCorpException, Exception {
		//1.아이디중복체크
		Optional<Corp> found = corpRepository.findByCorpLoginId(corpDto.getCorpLoginId());
        if (found.isPresent()) {
        	//아이디중복
			ExistedCorpException exception=
					new ExistedCorpException(corpDto.getCorpLoginId()+" 는 이미 존재하는아이디입니다.");
			exception.setData(corpDto);
			throw exception;
        }
		
		if(corpRepository.existsByCorpLoginId(corpDto.getCorpLoginId())) {
			//아이디중복
			throw new ExistedCorpException(corpDto.getCorpLoginId()+" 는 이미 존재하는 아이디입니다.");
		}
		//아이디안중복
		//2.회원가입
		Corp corp = modelMapper.map(corpDto, Corp.class);
		corp = corpRepository.save(corp);
		return modelMapper.map(corp, CorpDto.class);
	}

	@Override
	public CorpDto login(String corpLoginId, String corpPassword) throws Exception {
		Corp corp = corpRepository.findByCorpLoginId(corpLoginId).orElseThrow(() 
				-> new CorpNotFoundException(corpLoginId+"가 존재하지 않습니다."));
		if(!corp.getCorpPassword().equals(corpPassword)) {
			//패쓰워드불일치
			PasswordMismatchException exception=
				new PasswordMismatchException("패쓰워드가 일치하지않습니다.");
			throw exception;
		}
		return modelMapper.map(corp, CorpDto.class);
	}

	@Override
	public CorpDto findByCorpId(Long id) throws Exception {
		Optional<Corp> optionalCorp = corpRepository.findById(id);
		Corp findCorp = optionalCorp.get();
		return modelMapper.map(findCorp, CorpDto.class);
	}
	@Override
	public CorpDto findByCorpLoginId(String corpLoginId) throws Exception {
		Optional<Corp> optionalCorp = corpRepository.findByCorpLoginId(corpLoginId);
		Corp findCorp = optionalCorp.get();
		return modelMapper.map(findCorp, CorpDto.class);
	}

	@Override
	public CorpDto update(Long id,CorpDto corpDto) throws Exception {
		Corp corp = corpRepository.findById(id).orElseThrow(() 
				-> new CorpNotFoundException(corpDto.getCorpLoginId()+"가 존재하지않습니다."));
		corpDto.setId(id);
		corpDto.setCorpLoginId(corp.getCorpLoginId());
		corpDto.setCorpPassword(corp.getCorpPassword());
		
		modelMapper.map(corpDto, corp);
		corp = corpRepository.save(corp);
		return modelMapper.map(corp, CorpDto.class);
	}

	@Override
	public void remove(String corpLoginId) throws Exception {
		Corp corp = corpRepository.findByCorpLoginId(corpLoginId).orElseThrow(() 
				-> new CorpNotFoundException(corpLoginId+"가 존재하지않습니다."));
		corpRepository.delete(corp);
	}

	@Override
	public CorpDto findCorpWithimagesAndManagers(String corpLoginId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorpDto findCorpWithOrdersWithProduct(String corpLoginId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CorpDto findCorpWithRecruits(String corpLoginId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//회사의 리뷰목록 가져오기
	@Override
	public List<ReviewDto> findReviewList(Long corpId) {
			List<Review> findReviewList = reviewRepository.findByCorpId(corpId);
			List<ReviewDto> ReviewDtoList = findReviewList.stream()
					.map(review -> modelMapper.map(review, ReviewDto.class))
					.collect(Collectors.toList());
			return ReviewDtoList;	

	}

	@Override
	public List<CorpDto> findCorpAll() throws Exception {
		List<Corp> corpList = corpRepository.findAll();
		return corpList.stream()
				.map(corp -> modelMapper.map(corp, CorpDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public boolean isDuplicateId(String corpLoginId) throws Exception {
		return corpRepository.existsByCorpLoginId(corpLoginId);
	}


	
//	@Override
//	public List<CorpDto> searchCorpList(String query) throws Exception{
//		List<CorpDto> result = this.findCorpAll();
//		for(CorpDto corp:result) {
//			if((corp.getCorpName().toLowerCase()).contains(query.toLowerCase())) {
//				result.add(corp);
//			}
//		}
//		return result;
//	}

	//corpName으로 검색 기능 
	@Override
	public Page<CorpDto> searchByCorpName(String corpName, Pageable pageable) throws Exception {
		Page<Corp> corpList=corpRepository.findByCorpNameContaining(corpName, pageable);
		return corpList.map(corp -> modelMapper.map(corp, CorpDto.class));
	}
	//job으로만 검색 기능
	@Override
	public Page<CorpDto> searchByjob(String job, Pageable pageable) throws Exception {
		Page<Corp> jobList=corpRepository.findByJobContaining(job, pageable);
		return jobList
		        .map(corp->modelMapper.map(corp, CorpDto.class));
	}

	//corpName이랑 job으로 검색 기능
	@Override
	public Page<CorpDto> searchCorps(String corpName, String job, Pageable pageable) throws Exception {
		//corpName, job 둘 다 알 때
		if (corpName != null && !corpName.isEmpty() && job != null && !job.isEmpty()) {
		    Page<Corp> corpNameJobList=corpRepository.findByCorpNameContainingAndJobContaining(corpName, job, pageable);  
			return corpNameJobList
			        .map(corp->modelMapper.map(corp, CorpDto.class));
			//corpName만 알 때
		    } else if (corpName != null && !corpName.isEmpty()) {
		    	Page<Corp> corpNameList=corpRepository.findByCorpNameContaining(corpName, pageable);
		      return corpNameList
				        .map(corp->modelMapper.map(corp, CorpDto.class));
		    //job만 알 때
		    } else if (job != null && !job.isEmpty()) {
		    	Page<Corp> corpJobList=corpRepository.findByJobContaining(job, pageable);
		    	return corpJobList
				        .map(corp->modelMapper.map(corp, CorpDto.class));
		    //corpName이랑 job 둘 다 모를 때->전체 리스트 띄우기
		    } else {
		    	Page<Corp> corpAllList=corpRepository.findAll(pageable);
		      return corpAllList
				        .map(corp->modelMapper.map(corp, CorpDto.class));
		    }
		  }

	//corpList 페이징 기능...수정해야할 듯
	@Override
	public Page<CorpDto> getCorpList(int page, int size) throws Exception {
		PageRequest pageable=PageRequest.of(page, size);
		Page<Corp> corpPage=corpRepository.findAll(pageable);
		return corpPage.map(corp->modelMapper.map(corp, CorpDto.class));
	}
	
	//corpList 페이징 기능->수정된 버전
	@Override
	public Page<CorpDto> findAll(Pageable pageable) {
		Page<Corp> corpList=corpRepository.findAll(pageable);
		return corpList.map(corp->modelMapper.map(corp, CorpDto.class));
	}


	
	//corpId로 rcCount
	@Override
	public Long getRcCountByCorpId(Long corpId) throws Exception {
		return recruitRepository.countByCorpId(corpId);
	}

	@Override
	public Map<Long, Long> getRcCountByCorpIdList(List<Long> corpIdList) {
		Map<Long, Long>rcCountMap=new HashMap<>();
		for(Long corpId:corpIdList) {
			Long rcCount=recruitRepository.countByCorpId(corpId);
			rcCountMap.put(corpId, rcCount);
		}
		return rcCountMap;
	}

	@Override
	public List<CorpDto> searchByCorpName(String corpName) {
		List<Corp> searchCorpList = corpRepository.findByCorpNameContainingIgnoreCase(corpName);
		return searchCorpList.stream().map(corp->modelMapper.map(corp, CorpDto.class))
		        .collect(Collectors.toList());
	}

}
