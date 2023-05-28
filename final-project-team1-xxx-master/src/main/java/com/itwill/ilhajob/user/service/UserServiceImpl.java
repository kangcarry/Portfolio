package com.itwill.ilhajob.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.hibernate.internal.build.AllowSysOut;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NamingConventions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.user.dto.MessageDto;
import com.itwill.ilhajob.user.dto.ReviewDto;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.Message;
import com.itwill.ilhajob.user.entity.Review;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.exception.ExistedReviewException;
import com.itwill.ilhajob.user.exception.ExistedUserException;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.exception.UserNotFoundException;
import com.itwill.ilhajob.user.repository.MessageRepository;
import com.itwill.ilhajob.user.repository.ReviewRepository;
import com.itwill.ilhajob.user.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final MessageRepository messageRepository;
	private final ReviewRepository reviewRepository;
	
	
	private final ModelMapper modelMapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository, ReviewRepository reviewRepository, ModelMapper modelMapper, MessageRepository messageRepository) {
		this.userRepository = userRepository;
		this.reviewRepository = reviewRepository;
		this.modelMapper = modelMapper;
		this.messageRepository = messageRepository;
	}
	
	@Override
	public UserDto create(UserDto userDto) throws Exception {
		Optional<User> found = userRepository.findByUserEmail(userDto.getUserEmail());
        if (found.isPresent()) {
        	//아이디중복
			ExistedUserException exception=
					new ExistedUserException(userDto.getUserEmail()+" 는 이미 존재하는아이디입니다.");
			exception.setData(userDto);
			throw exception;
        }
        User user = modelMapper.map(userDto, User.class);
        user = userRepository.save(user);
        
        return modelMapper.map(user, UserDto.class);
	}

	@Override
	public UserDto login(String userEmail, String userPassword) throws Exception {
		User user = userRepository.findByUserEmail(userEmail).orElseThrow(() 
				-> new UserNotFoundException(userEmail+"와 일치하는 email이 존재하지 않습니다."));
        if (!user.getUserPassword().equals(userPassword)) {
        	//패쓰워드불일치
			PasswordMismatchException exception=
				new PasswordMismatchException("패쓰워드가 일치하지않습니다.");
			throw exception;
        }
        return modelMapper.map(user, UserDto.class);
	}
	
	@Override
	public UserDto findUser(String userEmail) throws Exception {
		Optional<User> optionalUser = userRepository.findByUserEmail(userEmail);
		User findUser = optionalUser.get();
		return modelMapper.map(findUser, UserDto.class);
	}
	/*
	@Override
	public boolean findKakaoUser(String snsId) throws Exception {
		return userRepository.findBySnsId(snsId);
	}
	*/

	@Override
	@Transactional
	public UserDto update(Long id, UserDto userDto) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() 
        		-> new UserNotFoundException("존재하지 않습니다."));
        userDto.setId(id);
        userDto.setUserEmail(user.getUserEmail());
        //userDto.setUserPassword(user.getUserPassword());
        modelMapper.map(userDto, user);
        user = userRepository.save(user);
        return modelMapper.map(user, UserDto.class);
	}

	@Override
	public void remove(Long id) throws Exception {
		User user = userRepository.findById(id).orElseThrow(() 
				-> new UserNotFoundException("User not found for id : " + id));
        userRepository.delete(user);
	}
	
	@Override
	public void remove(String userEmail) throws Exception {
		User user = userRepository.findByUserEmail(userEmail).orElseThrow(()-> new UserNotFoundException("해당 유저를 찾을 수 없습니다"));
		userRepository.delete(user);
	}

	
	@Override
	public boolean isDuplicateEmail(String userEmail) throws Exception {
		return userRepository.existsByUserEmail(userEmail);
	}
	/*
	@Override
	public UserDto findAppListById(Long id) throws Exception {
		Optional<User> OptionalUser = userRepository.findAppListById(id);
		User user = OptionalUser.get();
		return modelMapper.map(user,UserDto.class);
	}

	*/
	
	@Override
	public List<MessageDto> findMessageList(Long userId) {
		List<Message> findMsgList = messageRepository.findByUserId(userId);
		List<MessageDto> msgDtoList = findMsgList.stream()
				.map(message -> modelMapper.map(message, MessageDto.class))
				.collect(Collectors.toList());
		return msgDtoList;
		/*
		List<Message> messageList = messageRepository.findAll();
		List<Message> findMessageList = new ArrayList<Message>();
		for(int i = 0; i < messageList.size(); i++) {
			if(messageList.get(i).getUser().getId() == userId) {
				findMessageList.add(messageList.get(i));
			}
		}
		System.out.println(findMessageList);
		List<MessageDto> messageDtoList = findMessageList.stream().map(message-> new MessageDto(message.getId(),message.getMessageTitle(),message.getMessageContents(),message.getMessageDate())).collect(Collectors.toList());
		*/

		//return messageDtoList;
	}

	@Override
	public void removeMessageBySeq(Long messageSeq) {
		messageRepository.deleteById(messageSeq);
	}
	
	//리뷰 작성
	
	@Override
	public ReviewDto insertReview(ReviewDto reviewDto) throws Exception {
		Long count = reviewRepository.countByUserIdAndCorpId(reviewDto.getUser().getId(), reviewDto.getCorp().getId());
		System.out.println(">>>>>>>>>>>>>>>>>"+count);
		if (count != 0) {
			ExistedReviewException exception = 
				new ExistedReviewException(reviewDto.getUser().getUserEmail()+"회원님이 작성한 리뷰가 존재합니다."); //getUserEmail() -> getUserName으로 변경될예정~
			exception.setData(reviewDto);
			throw exception;
	    // 특정 유저 + 기업 -> 조건에 맞는 리뷰가 이미 작성되어 있는 경우
		} else {
		    // 특정 유저 + 기업 -> 조건에 맞는 리뷰가 작성이 안되어 있는 경우
			Review review = modelMapper.map(reviewDto, Review.class);
			
			review = reviewRepository.save(review);
			return modelMapper.map(review, ReviewDto.class);
		}
	}
	
	
	//리뷰 수정
	
	@Override
	public ReviewDto updateReview(ReviewDto reviewDto) throws Exception {
		Review review = reviewRepository.findById(reviewDto.getId()).orElse(null); //null이 반환될수도 있음.
		reviewDto.setReviewTitle(review.getReviewTitle());
		reviewDto.setReviewContent(review.getReviewContent());
		reviewDto.setReviewGrade(review.getReviewGrade());
		modelMapper.map(reviewDto, review);
		review = reviewRepository.save(review);
		return modelMapper.map(review, ReviewDto.class);
	}


	
	
	

}
