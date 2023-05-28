package com.itwill.ilhajob.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itwill.ilhajob.common.dto.BlogCateDto;
import com.itwill.ilhajob.common.dto.BlogCommentDto;
import com.itwill.ilhajob.common.dto.BlogDto;
import com.itwill.ilhajob.common.dto.BlogHeartDto;
import com.itwill.ilhajob.common.entity.Blog;
import com.itwill.ilhajob.common.service.BlogCateService;
import com.itwill.ilhajob.common.service.BlogCommentService;
import com.itwill.ilhajob.common.service.BlogHeartService;
import com.itwill.ilhajob.common.service.BlogService;
import com.itwill.ilhajob.user.controller.LoginCheck;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.service.UserService;


@Controller
public class BlogController {

	public BlogController() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private BlogCommentService blogCommentService;
	
	@Autowired
	private BlogCateService blogCateService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogHeartService blogHeartService;
	
	/*블로그 리스트 페이징*/
	@GetMapping("/blog-list")
	public String blog_list(@PageableDefault(page=0, size=9, sort="id", direction=Sort.Direction.DESC) Pageable pageable, Model model) {
	    Page<BlogDto> blogList = blogService.findAll(pageable);
	    int nowPage = blogList.getPageable().getPageNumber();
	  
	    model.addAttribute("blogList", blogList);
	    model.addAttribute("nowPage",nowPage);
        model.addAttribute("totalPage", blogList.getTotalPages());
        model.addAttribute("prePage", blogList.previousOrFirstPageable().getPageNumber());
        model.addAttribute("nextPage", blogList.nextOrLastPageable().getPageNumber());

	    return "blog-list";
	}

	
	/*블로그 카테고리 리스트 페이징 -> blogCateid받기
	@GetMapping("/blog-list")
	public String blog_cate_list(@PageableDefault(page=0, size=3, sort="id", direction=Sort.Direction.DESC) Pageable pageable, Model model) {
		Page<BlogDto> blogCateList = blogService.findAllByBlogCateId(2L,pageable);
		int nowPage = blogCateList.getPageable().getPageNumber();
		
		model.addAttribute("blogCateList", blogCateList);
		model.addAttribute("nowPage",nowPage);
		model.addAttribute("totalPage", blogCateList.getTotalPages());
		model.addAttribute("prePage", blogCateList.previousOrFirstPageable().getPageNumber());
		model.addAttribute("nextPage", blogCateList.nextOrLastPageable().getPageNumber());
		
		return "blog-list";
		}
	*/
	
	/*블로그 전체 리스트
	
	@RequestMapping("/blog-list")
	public String blog_list(Model model) throws Exception{
		String forwardPath = "";
		List<BlogDto> blogList = blogService.selectAll();
		model.addAttribute("blogList", blogList);
		forwardPath="blog-list";
		
		return forwardPath;
	}
	*/
	
	
	/*새 블로그 작성*/  
	@LoginCheck
	@RequestMapping("/blog-write-form")
	public String blog_write_form(HttpServletRequest request) throws Exception {
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		request.setAttribute("loginUser", loginUser);
		return "blog-write-form";
	}

	@ResponseBody
	@PostMapping("/blog_write_action")
	public ResponseEntity<Object> blog_write_action(@RequestBody BlogDto blog,HttpServletRequest request,
									RedirectAttributes redirectAttributes)throws Exception {
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		blog.setUser(loginUser);
		blog = blogService.insertBlog(blog);
		//redirectAttributes.addAttribute("id",blog); 작성글로 가야됨...
		return ResponseEntity.ok().body("{\"success\": true, \"id\": \"" + blog.getId() + "\", \"message\": \"리뷰가 성공적으로 작성되었습니다.\"}");
	}
//	@PostMapping("/blog_write_action")
//	public String blog_write_action(@ModelAttribute BlogDto blog,HttpServletRequest request,
//									RedirectAttributes redirectAttributes)throws Exception {
//		String sUserId = (String)request.getSession().getAttribute("sUserId");
//		UserDto loginUser = userService.findUser(sUserId);
//		blog.setUser(loginUser);
//		blogService.insertBlog(blog);
//		//redirectAttributes.addAttribute("id",blog); 작성글로 가야됨...
//		return "redirect:blog-single";
//		}
	
/*블로그 상세*/
	
	//블로그 id가 없을 때
	@RequestMapping(value = "/blog-single",params = "!id")
	public String blog_view() {
		return "redirect:blog-list";	
	}
	
	//블로그 id가 있을때
	@RequestMapping(value = "/blog-single", params = "id")
	public String blog_view(@RequestParam("id") Long blogId , Model model, @ModelAttribute BlogCommentDto blogCommentDto,HttpServletRequest request) throws Exception{
		  
	    String sUserId = (String) request.getSession().getAttribute("sUserId");
	    UserDto loginUser = null;
	    if(sUserId != null) {
	        loginUser = userService.findUser(sUserId);
	    }
	    
		//블로그 상세내용 불러오기
		BlogDto blogDto = blogService.findBlog(blogId);
		model.addAttribute("blog", blogDto);
		
		//블로그 조회수 업데이트
		model.addAttribute("blogReadCount", blogService.updateViews(blogId));
    
		//댓글리스트 불러오기
		List<BlogCommentDto> blogCommentList = blogCommentService.findByBlogComment(blogId);
		model.addAttribute("blogCommentList", blogCommentList);
		model.addAttribute("blogComment", blogCommentDto);
		
	    
	    // 로그인 안했을땐 heartState값 0으로 넣기
	    int heartState = 0;
	    if(loginUser != null) {
	        BlogHeartDto blogHeartDto = blogHeartService.findBlogLikeState(blogId, loginUser.getId());
	        heartState = blogHeartDto != null ? blogHeartDto.getHeartState() : 0;
	    }
	    model.addAttribute("heartState", heartState);

	    return "blog-single";
	}
		
		
	/*블로그 수정*/
	
	//블로그 id가 없을 때
	@LoginCheck
	@PostMapping(value="/blog-modify-form",params = "!blogId")
	public String blog_modify_form() {
		return "redirect:blog_list";
	}
	
	//블로그 id가 있을때
	@LoginCheck
	@PostMapping(value = "/blog-modify-form",params = "blogId")
	public String blog_modify_form(@RequestParam Long blogId,Model model) throws Exception {
		BlogDto blogDto = blogService.findBlog(blogId);
		List<BlogCateDto> blogCateList = blogCateService.selectAll();
		model.addAttribute("blog", blogDto);
		model.addAttribute("blogCateList", blogCateList);
		return "blog-modify-form";
	}
	
	@PostMapping("/blog-modify-action")
	public String blog_modify_action(@ModelAttribute BlogDto blogDto, HttpServletRequest request)throws Exception {
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		System.out.println(">>>>>>>>>"+sUserId);
		UserDto loginUser = userService.findUser(sUserId);
		Long id = blogDto.getId();
		//System.out.println(">>>>>>>>"+id);
		//BlogDto findBlog = blogService.findBlog(id);
		blogDto.setUser(loginUser);
		System.out.println(">>>>>>>>"+blogDto);
		blogService.updateBlog(id, blogDto);
		
		return "redirect:blog-single?id="+blogDto.getId();
	}
	

	/*블로그 삭제*/
	@PostMapping(value = "/blog-remove-action", params = "blogId")
	public String blog_remove_action(@RequestParam Long blogId) throws Exception{
		blogService.deleteBlog(blogId);
		return "redirect:blog-list";
	}
	

	/*
	 * 블로그 댓글 작성
	 */
	@LoginCheck
	@PostMapping("/blogcomment_write_action")
	public String blogcomment_write_action(@ModelAttribute BlogCommentDto blogCommentDto, HttpServletRequest request,
									RedirectAttributes redirectAttributes,@RequestParam("blogId") Long blogId)throws Exception {
		String sUserId = (String)request.getSession().getAttribute("sUserId");
		UserDto loginUser = userService.findUser(sUserId);
		blogCommentDto.setUser(loginUser);
		blogCommentService.insertBlogComment(blogCommentDto);
		return "redirect:blog-single?id="+blogId;
	
	}
	
	/*블로그 댓글 삭제*/
	@PostMapping(value ="/blogcomment_remove")
	public String blogcomment_remove(@RequestParam Long id,Long blogId) throws Exception{
		blogCommentService.deleteBlogComment(id);
		return "redirect:blog-single?id="+blogId;
	}
	

	/*블로그 댓글 수정*/
	
	
	@PostMapping("/blogcomment_update")
	public String blogcomment_update(@RequestParam Long id, @ModelAttribute BlogCommentDto blogCommentDto, Model model,
	                                 RedirectAttributes redirectAttributes, Long blogId) throws Exception {
	    BlogCommentDto blogComment = blogCommentService.findBlogComment(id);
	    model.addAttribute("blogComment", blogComment);

	    blogCommentService.updateComment(id, blogCommentDto);

	    redirectAttributes.addAttribute("blogId", blogCommentDto.getBlogId());
	    return "redirect:blog-single?id=" + blogCommentDto.getBlogId();
	}
	

	/*블로그 좋아요*/
	@LoginCheck
	@RequestMapping("/blog_like")
	public String blog_like(@RequestParam Long blogId, HttpServletRequest request, Model model) throws Exception{
	    String sUserId = (String)request.getSession().getAttribute("sUserId");
	    UserDto loginUser = userService.findUser(sUserId);
	    if(loginUser == null) {
	        String msg="로그인이 필요합니다.";
	        model.addAttribute("msg", msg);}
	    else {
	        int result = blogService.updateLike(blogId, loginUser.getId());
	        model.addAttribute("blogLike", result);
	    }

	    BlogHeartDto blogHeart = blogHeartService.findBlogLikeState(blogId, loginUser.getId());
	    int heartState = blogHeart != null ? blogHeart.getHeartState() : 0;
	    model.addAttribute("heartState", heartState);

	    return "redirect:blog-single?id=" + blogId;
	}


}