package com.itwill.ilhajob.common.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.common.service.OrdersService;
import com.itwill.ilhajob.common.service.ProductService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.finallyClause_return;
import net.bytebuddy.asm.Advice.OffsetMapping.Sort;

@Controller
public class ProductController {
	
	private final ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/product")
	public String product(HttpServletRequest request, Model model) throws Exception {
		String forwardPath = "";
		String pDiv=(String)request.getSession().getAttribute("pDiv");
		List<ProductDto> productList = productService.selectByDiv(pDiv);
		model.addAttribute("productList", productList);
		
	    forwardPath = "product";
	    return forwardPath;
	}
		
}