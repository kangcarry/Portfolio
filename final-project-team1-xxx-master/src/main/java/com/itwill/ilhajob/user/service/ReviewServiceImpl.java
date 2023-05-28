package com.itwill.ilhajob.user.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.Review;
import com.itwill.ilhajob.user.repository.ReviewRepository;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
	
	private final ReviewRepository reviewRepository;
	private final ModelMapper modelMapper;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
		this.reviewRepository = reviewRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public void remove(Long reviewId) throws Exception {
		reviewRepository.deleteById(reviewId);
		
	}
	/*
	private final ReviewRepository reviewRepository;
	private final ModelMapper modelMapper;

	public ReviewServiceImpl(ReviewRepository reviewRepository, ModelMapper modelMapper) {
		this.reviewRepository = reviewRepository;
		this.modelMapper = modelMapper;
	}
	*/
	//insert review
	/*
	@Override
	public ReviewDto insertReview(ReviewDto reviewDto) throws Exception {
		Review review = modelMapper.map(reviewDto, Review.class);
		review = reviewRepository.save(review);
		return modelMapper.map(review, ReviewDto.class);
	} */

	@Override
	public Long isReviewDuplicate(Long id, Long Corpid) throws Exception {
		
		Long count = reviewRepository.countByUserIdAndCorpId(id, Corpid);
		return count;
	}

	@Override
	public List<ReviewDto> findAll() {
		List<Review> reviewList = reviewRepository.findAll();
		return reviewList.stream().map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList());
	}
	
	//update review
	/*
	@Override
	public ReviewDto updateReview(ReviewDto reviewDto) throws Exception {
		Review review = new Review();
		reviewDto.setId(review.getId());
		reviewDto.setReviewTitle(review.getReviewTitle());
		reviewDto.setReviewContent(review.getReviewContent());
		reviewDto.setReviewGrade(review.getReviewGrade());
		modelMapper.map(reviewDto, review);
		return modelMapper.map(review, ReviewDto.class);
	}
	
	//delete review
	@Override
	public void deleteReview(Long id) throws Exception {
		reviewRepository.deleteById(id);
	}
	
	*/
	
	
	
	
	
}
