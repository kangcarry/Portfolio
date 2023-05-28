package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.common.entity.Product;
import com.itwill.ilhajob.common.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ModelMapper modelMapper;


	
	@Autowired
	//constructor
	public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public ProductDto selectById(long id) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent()){
			Product findProduct = optionalProduct.get();
			return modelMapper.map(findProduct, ProductDto.class);
		}
		return null;
	}
		
	@Override
	public List<ProductDto> selectByDiv(String pDiv) throws Exception {
		List<Product> productList = productRepository.findByproductDiv(pDiv);
		return productList.stream()
				.map(product -> modelMapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
	}
	
	@Override
	public int updateProduct(ProductDto productDto) throws Exception {
		return 0;
	}
	
	
	@Override
	public int deleteProduct(int pNo) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
