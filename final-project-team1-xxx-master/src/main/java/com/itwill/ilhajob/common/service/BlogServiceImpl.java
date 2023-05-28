package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.entity.BlogHeart;
import com.itwill.ilhajob.common.repository.BlogHeartRepository;
import com.itwill.ilhajob.common.repository.BlogRepository;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.repository.UserRepository;


@Service
public class BlogServiceImpl implements BlogService {
	
	private final BlogRepository blogRepository;
	private final UserRepository userRepository;
	private final BlogHeartRepository blogHeartRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public BlogServiceImpl(BlogRepository blogRepository, ModelMapper modelMapper, BlogHeartRepository blogHeartRepository, UserRepository userRepository ) {
		this.blogRepository = blogRepository;
		this.userRepository = userRepository;
		this.blogHeartRepository = blogHeartRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public BlogDto insertBlog(BlogDto blogDto) throws Exception {
        Blog blog = modelMapper.map(blogDto, Blog.class);
        blog = blogRepository.save(blog);
        return modelMapper.map(blog, BlogDto.class);
	}
	

	@Override
	public BlogDto updateBlog(Long id,BlogDto blogDto) throws Exception {
		Blog blog = blogRepository.findById(id).orElseThrow(null);
		blogDto.setId(blog.getId());
		modelMapper.map(blogDto,blog);
		blog = blogRepository.save(blog);
		return modelMapper.map(blog, BlogDto.class);
	}

	
	@Override
	public void deleteBlog(Long id) throws Exception {
		blogRepository.deleteById(id);
	}
	
	@Override
	public List<BlogDto> selectAll(){
		List<Blog> blogList = blogRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
		return blogList.stream().map(blog -> modelMapper.map(blog, BlogDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public BlogDto findBlog(Long id) throws Exception {
		Optional<Blog> optionalBlog = blogRepository.findById(id);
		Blog findBlog = optionalBlog.get();
		return modelMapper.map(findBlog, BlogDto.class);
	}
	
	 @Override
	    public Page<BlogDto> findAll(Pageable pageable) {
		Page<Blog> blogList = blogRepository.findAll(pageable);
		return blogList.map(blog -> modelMapper.map(blog, BlogDto.class)); 
	 }

	 @Override
	 public Page<BlogDto> findAllByBlogCateId(Long blogCateId, Pageable pageable) {
		 Page<Blog> blogCateList = blogRepository.findAllByBlogCateId(blogCateId, pageable);
		 return blogCateList.map(blog -> modelMapper.map(blog, BlogDto.class)); 
	 }


	@Override
	@Transactional
	public int updateViews(Long id) {
		 return blogRepository.updateViews(id);
	}	

	@Override
	@Transactional
	public int updateLike(Long blogId, Long userId) {

		Blog blog = blogRepository.findById(blogId).orElseThrow(null);
		User user = userRepository.findById(userId).orElseThrow(null);
		
		if(blogHeartRepository.findByBlogAndUser(blog, user)==null) {
	            BlogHeart blogHeart = new BlogHeart(blog, user);
	            blogHeartRepository.save(blogHeart);
	            blogRepository.plusLike(blogId);         
		
	    return 1; // 좋아요 -->  1
	            
			}else {

		blogHeartRepository.deleteByBlogIdAndUserId(blogId,userId);
        blogRepository.minusLike(blogId);

        return 0; // 좋아요 취소 --> 0
     }
	}	
}