package com.itwill.ilhajob.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.common.service.OrdersService;
import com.itwill.ilhajob.common.service.ProductService;
import com.itwill.ilhajob.user.dto.AwardsDto;
import com.itwill.ilhajob.user.dto.CvDto;
import com.itwill.ilhajob.user.dto.EduDto;
import com.itwill.ilhajob.user.dto.ExpDto;
import com.itwill.ilhajob.user.service.AwardsService;
import com.itwill.ilhajob.user.service.CvService;
import com.itwill.ilhajob.user.service.EduService;
import com.itwill.ilhajob.user.service.ExpService;

@RestController
public class ModalController {

	private final ProductService productService;
	private final OrdersService ordersService;
	private final CvService cvService;
	private final EduService eduService;
	private final ExpService expService;
	private final AwardsService awardsService;
	
	@Autowired
	public ModalController(ProductService productService, OrdersService ordersService, CvService cvService, EduService eduService, ExpService expService, AwardsService awardsService) {
		this.productService = productService;
		this.ordersService = ordersService;
		this.cvService = cvService;
		this.eduService = eduService;
		this.expService = expService;
		this.awardsService = awardsService;
	}
	
    @GetMapping("/login-popup")
    public ResponseEntity<String> showLoginPopup() {
    	String html = ""; // html 파일 내용을 읽어와서 변수에 저장

    	try {
    	    Resource resource = new ClassPathResource("templates/login-popup.html");
    	    InputStream inputStream = resource.getInputStream();
    	    html = new BufferedReader(new InputStreamReader(inputStream))
    	         .lines().collect(Collectors.joining("\n"));
    	} catch (IOException e) {
    	     e.printStackTrace();
    	     // 예외 처리
    	}
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.TEXT_HTML);

    	return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
    
    @GetMapping("/register-popup")
    public ResponseEntity<String> showRegisterPopup() {
    	String html = "";
    	try {
    	    Resource resource = new ClassPathResource("templates/register-popup.html");
    	    InputStream inputStream = resource.getInputStream();
    	    html = new BufferedReader(new InputStreamReader(inputStream))
    	         .lines().collect(Collectors.joining("\n"));
    	} catch (IOException e) {
    	     e.printStackTrace();
    	     // 예외 처리
    	}

    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.TEXT_HTML);

    	return new ResponseEntity<>(html, headers, HttpStatus.OK);
    }
    
    @GetMapping("/apply-popup")
    public ResponseEntity<Object> showApplyPopup(HttpServletRequest request) {
    	Map<String, Object> responseData = new HashMap<>();
    	Long userId = (Long)request.getSession().getAttribute("id");
		/* user cv list */
		List<CvDto> cvList = cvService.findByUserId(userId);
		/* edu list */
		List<EduDto> eduList = eduService.findEduListByUserId(userId);
		/* exp list */
		List<ExpDto> expList = expService.findExpListByUserId(userId);
		/* awards list */
		List<AwardsDto> awardsList = awardsService.findAwardsByUserId(userId);
		responseData.put("cvList", cvList);
		responseData.put("eduList", eduList);
		responseData.put("expList", expList);
		responseData.put("awardsList", awardsList);
    	String html = "";

    	try {
    	    Resource resource = new ClassPathResource("templates/apply-popup.html");
    	    InputStream inputStream = resource.getInputStream();
    	    html = new BufferedReader(new InputStreamReader(inputStream))
    	         .lines().collect(Collectors.joining("\n"));
    	} catch (IOException e) {
    	     e.printStackTrace();
    	     // 예외 처리
    	}
    	responseData.put("html", html);
    	return ResponseEntity.ok(responseData);
    }
    
    @PostMapping("order-popup")
    public ResponseEntity<Object> getProductInfo(@RequestBody ProductDto productDto, HttpServletRequest request) throws Exception {
    	String html = "";
    	Map<String, Object> responseData = new HashMap<>();
    	if(request.getSession().getAttribute("role").equals("user")) {
    		long userId = (long)request.getSession().getAttribute("id");
    		responseData = ordersService.orderProductByUser(userId, productDto.getId());
    		
    	}else if(request.getSession().getAttribute("role").equals("corp")) {
    		long corpId = (long)request.getSession().getAttribute("id");
    		responseData = ordersService.orderProductByCorp(corpId, productDto.getId());
    	}
        try {
            Resource resource = new ClassPathResource("templates/order-popup.html");
            InputStream inputStream = resource.getInputStream();
            html = new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        responseData.put("html", html);

        return ResponseEntity.ok(responseData);
    }
}
