package com.itwill.ilhajob.common.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.itwill.ilhajob.common.dto.LoginRequestDto;
import com.itwill.ilhajob.common.dto.OrdersDto;
import com.itwill.ilhajob.common.dto.OrdersRequestDto;
import com.itwill.ilhajob.common.dto.PaymentDto;
import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.common.service.OrdersService;
import com.itwill.ilhajob.common.service.ProductService;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.exception.CorpNotFoundException;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.exception.PasswordMismatchException;
import com.itwill.ilhajob.user.exception.UserNotFoundException;

import springfox.documentation.annotations.ApiIgnore;

@Controller
public class OrderController {
	
	private final OrdersService ordersService;
	private final ProductService productService;
	
	@Autowired
	public OrderController(OrdersService ordersService, ProductService productService) {
		this.ordersService = ordersService;
		this.productService = productService;
	}
	
	@GetMapping("/product")
	public String pricing(HttpServletRequest request,Model model) throws Exception {
		int paymentStatus = (Integer)request.getSession().getAttribute("paymentStatus");
		System.out.println(paymentStatus);
		model.addAttribute("paymentStatus",paymentStatus);
		if(request.getSession().getAttribute("role").equals("user")) {
			List<ProductDto> productList = productService.selectByDiv("user");
			model.addAttribute("productList", productList);
		}
		if(request.getSession().getAttribute("role").equals("corp")) {
			model.addAttribute("productList", productService.selectByDiv("corp"));
		}
		
		String forwardPath = "product";
		return forwardPath;
	}
	
	@GetMapping("/dashboard-change-password")
	public String changePassword(HttpServletRequest request) {
		//System.out.println(">>>>>>>>"+sUserId);
		String forwardPath = "dashboard-change-password";
		return forwardPath;
	}
	
	@ResponseBody
	@PostMapping("/order")
	public ResponseEntity<String> orderSave(HttpServletRequest request) throws Exception {
		//넘어온 string을 json형식으로 변환
		StringBuilder buffer = new StringBuilder();
		BufferedReader reader = request.getReader();
		String line;
		while ((line = reader.readLine()) != null) {
			buffer.append(line);
		}
		String json = buffer.toString();
		//json형식의 string을 gson객체로 변환 후 dto 매핑
		Gson gson = new Gson();
		JsonElement element = gson.fromJson(json, JsonElement.class);
		JsonObject jsonObject = element.getAsJsonObject();
		JsonObject userDataObject = jsonObject.getAsJsonObject("userData");
		JsonObject productDataObject = jsonObject.getAsJsonObject("productData");
		JsonObject paymentDataObject = jsonObject.getAsJsonObject("paymentData");
		ProductDto productDto = gson.fromJson(productDataObject, ProductDto.class);
		String role = (String)request.getSession().getAttribute("role");
		long id = (long)request.getSession().getAttribute("id");
		OrdersDto saveOrdersDto = ordersService.checkAndSaveOrder(role, id, productDto,paymentDataObject.get("paymentMethod").toString());
		request.getSession().setAttribute("paymentStatus", 1);
		JsonObject orderDataObject = new JsonObject();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		orderDataObject.addProperty("id", saveOrdersDto.getId());
		orderDataObject.addProperty("orderStartDate", saveOrdersDto.getOrderStartDate().format(formatter));
		orderDataObject.addProperty("orderEndDate", saveOrdersDto.getOrderEndDate().format(formatter));
		orderDataObject.addProperty("orderValid", saveOrdersDto.getOrderValid());
		jsonObject.add("orderData", orderDataObject);
		
		return ResponseEntity.ok(jsonObject.toString());
	}
	
	@GetMapping("/order-completed")
	public String orderCompleted(HttpServletRequest request) {
		String forwardPath = "order-completed";
		return forwardPath;
	}
	
	@GetMapping("/invoice")
	public String invoice(HttpServletRequest request, Model model) {
		String forwardPath = "";
		request.getSession().getAttribute("role");
		List<PaymentDto> paymentList = ordersService.findPayment("user", 1L);
		int totalPrice = paymentList.stream().mapToInt(PaymentDto::getPaymentPrice).sum();
		model.addAttribute("paymentList", paymentList);
		model.addAttribute("totalPrice", totalPrice);

		forwardPath = "invoice";
		return forwardPath;
	}
	
	@GetMapping("/dashboard-packages")
	public String packages(HttpServletRequest request) throws Exception {
		String forwardPath = "";
		String role = (String)request.getSession().getAttribute("role");
		Long id=(Long)request.getSession().getAttribute("id");
		List<OrdersRequestDto> orderList = ordersService.findOrder(role, id);
		request.setAttribute("orderList", orderList);
		
		forwardPath="dashboard-packages";		
		return forwardPath;
	}
}