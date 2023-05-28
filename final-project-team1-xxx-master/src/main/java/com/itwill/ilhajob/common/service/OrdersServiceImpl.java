package com.itwill.ilhajob.common.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itwill.ilhajob.common.dto.OrdersDto;
import com.itwill.ilhajob.common.dto.OrdersRequestDto;
import com.itwill.ilhajob.common.dto.PaymentDto;
import com.itwill.ilhajob.common.dto.ProductDto;
import com.itwill.ilhajob.common.entity.Orders;
import com.itwill.ilhajob.common.entity.Payment;
import com.itwill.ilhajob.common.entity.Product;
import com.itwill.ilhajob.common.repository.OrdersRepository;
import com.itwill.ilhajob.common.repository.PaymentRepository;
import com.itwill.ilhajob.common.repository.ProductRepository;
import com.itwill.ilhajob.corp.dto.CorpDto;
import com.itwill.ilhajob.corp.entity.Corp;
import com.itwill.ilhajob.corp.exception.CorpNotFoundException;
import com.itwill.ilhajob.corp.repository.CorpRepository;
import com.itwill.ilhajob.user.dto.UserDto;
import com.itwill.ilhajob.user.entity.User;
import com.itwill.ilhajob.user.exception.UserNotFoundException;
import com.itwill.ilhajob.user.repository.UserRepository;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrdersServiceImpl implements OrdersService{

	private final ProductRepository productRepository;
	private final OrdersRepository ordersRepository;
	private final PaymentRepository paymentRepository; 
	private final UserRepository userRepository;
	private final CorpRepository corpRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public OrdersServiceImpl(ProductRepository productRepository, OrdersRepository ordersRepository, PaymentRepository paymentRepository
							 ,UserRepository userRepository, CorpRepository corpRepository, ModelMapper modelMapper) {
		this.productRepository = productRepository;
		this.ordersRepository = ordersRepository;
		this.paymentRepository = paymentRepository;
		this.userRepository = userRepository;
		this.corpRepository = corpRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public OrdersDto checkAndSaveOrder(String role, long id, ProductDto productDto, String paymentMethod) throws Exception {
		List<Orders> ordersList = new ArrayList<Orders>();
		long period = 0;
		if(role.equals("user")) {
			ordersList = ordersRepository.findByUserId(id);
			//주문내역이 없거나 종료일이 만료됐을 때 신규 주문, 결제 생성
			if(ordersList.size()==0 || ordersList.get(ordersList.size()-1).getOrderEndDate().compareTo(LocalDateTime.now()) < 0) {
				Orders saveOrder = saveOrder(role,id,productDto);
				OrdersDto saveOrdersDto = modelMapper.map(saveOrder, OrdersDto.class);
				savePayment(saveOrdersDto, productDto, paymentMethod);
				User updateUser = userRepository.findById(id).orElseThrow(() 
						-> new UserNotFoundException("존재하지 않는 사용자입니다."));
				UserDto updateUserDto = modelMapper.map(updateUser, UserDto.class);
				updateUserDto.setPaymentStatus(1);
				userRepository.save(modelMapper.map(updateUserDto, User.class));
				return saveOrdersDto;
			}
			//종료일이 현재시간보다 남아있을 때 order의 종료일 현재시간으로 업데이트 및 valid 변경 후 새로운 주문, 결제 생성
			OrdersDto findOrder = modelMapper.map(ordersList.get(ordersList.size()-1), OrdersDto.class);
			//남은기간 + 새로 주문하는 상품의 기간
			period = productDto.getProductPeriod();
			productDto.setProductPeriod(period+Duration.between(LocalDateTime.now(),findOrder.getOrderEndDate()).toDays()); 
			findOrder.setOrderEndDate(LocalDateTime.now());
			findOrder.setOrderValid(0);
			Orders updateOrder = modelMapper.map(findOrder, Orders.class);
			//마지막 주문의 종료일 현재시간으로 변경 후 상태 변경
			ordersRepository.save(updateOrder);
			//새로운 주문 생성
			Orders saveOrder = saveOrder(role,id,productDto);
			OrdersDto saveOrdersDto = modelMapper.map(saveOrder, OrdersDto.class);
			savePayment(saveOrdersDto, productDto, paymentMethod);
			return saveOrdersDto;

		}else if(role.equals("corp")) {
			if (ordersList.size() == 0 || ordersList.get(ordersList.size()-1).getOrderEndDate().compareTo(LocalDateTime.now()) < 0) {
				Orders saveOrder = saveOrder(role,id,productDto);
				OrdersDto saveOrdersDto = modelMapper.map(saveOrder, OrdersDto.class);
				savePayment(saveOrdersDto, productDto, paymentMethod);
				Corp updateCorp = corpRepository.findById(id).orElseThrow(() 
						-> new UserNotFoundException("존재하지 않는 사용자입니다."));
				CorpDto updateCorpDto = modelMapper.map(updateCorp, CorpDto.class);
				updateCorpDto.setPaymentStatus(1);
				corpRepository.save(modelMapper.map(updateCorpDto, Corp.class));
				return saveOrdersDto;
			}
			OrdersDto findOrder = modelMapper.map(ordersList.get(ordersList.size()-1), OrdersDto.class);
			period = productDto.getProductPeriod();
			productDto.setProductPeriod(period + Duration.between(LocalDateTime.now(), findOrder.getOrderEndDate()).toDays());
			findOrder.setOrderEndDate(LocalDateTime.now());
			findOrder.setOrderValid(0);
			Orders updateOrder = modelMapper.map(findOrder, Orders.class);
			ordersRepository.save(updateOrder);
			
			
			Orders saveOrder = saveOrder(role,id,productDto);
			OrdersDto saveOrdersDto = modelMapper.map(saveOrder, OrdersDto.class);
			savePayment(saveOrdersDto, productDto, paymentMethod);
			return saveOrdersDto;
		}
		return null;
	}


	@Override
	public void removeOrder() {
		// TODO Auto-generated method stub
	}

	@Override
	public List<OrdersDto> findOrderByUser(long id) {
		List<Orders> findOrderList = ordersRepository.findByUserId(id);
		List<OrdersDto> orderList = findOrderList.stream()
				.map(order -> modelMapper.map(order, OrdersDto.class))
				.collect(Collectors.toList());
		return orderList;
	}

	@Override
	public List<OrdersDto> findOrderByCorp(long id) {
		List<Orders> findOrderList = ordersRepository.findByCorpId(id);
		List<OrdersDto> orderList = findOrderList.stream()
				.map(order -> modelMapper.map(order, OrdersDto.class))
				.collect(Collectors.toList());
		return orderList;
	}
		
	//재구매시 남은 기간 확인 후 시작일, 만료일 변경
	@Override
	public void reorderingCheckUpdate(String role, long id) {
		List<Orders> ordersList = new ArrayList<Orders>();
		if(role.equals("user")) {
			ordersList = ordersRepository.findByUserId(id);
			if(ordersList.get(ordersList.size()-1).getOrderEndDate().compareTo(LocalDateTime.now()) > 0) {
				OrdersDto findOrder = modelMapper.map(ordersList.get(ordersList.size()-1), OrdersDto.class);
				findOrder.setOrderEndDate(LocalDateTime.now());
				findOrder.setOrderValid(0);
				Orders updateOrders = modelMapper.map(findOrder, Orders.class);
				ordersRepository.save(updateOrders);
				
			}else {
				new OrdersDto();
			}
		}else if(role.equals("corp")) {
			ordersList = ordersRepository.findByCorpId(id);
			if(ordersList.get(ordersList.size()-1).getOrderEndDate().compareTo(LocalDateTime.now()) > 0) {
				modelMapper.map(ordersList.get(ordersList.size()-1), OrdersDto.class); 
			}else {
				new OrdersDto();
			}
		}
	}

	@Override
	public List<PaymentDto> findPayment(String role, long id) {
	    List<Payment> paymentList;
	    if (role.equals("user")) {
	        paymentList = paymentRepository.findByUserId(id);
	    } else {
	        paymentList = paymentRepository.findByCorpId(id);
	    }

	    return paymentList.stream()
	        .map(payment -> modelMapper.map(payment, PaymentDto.class))
	        .collect(Collectors.toList());
	}

	@Override
	public Map<String, Object> orderProductByUser(long user_id, long product_id) throws Exception{
		User user = userRepository.findById(user_id).orElseThrow(()
				-> new UserNotFoundException("존재하지 않습니다."));
		UserDto findUser = modelMapper.map(user, UserDto.class);
		Optional<Product> optionalProduct = productRepository.findById(product_id);
		Product product = optionalProduct.get();
		ProductDto findProduct = modelMapper.map(product, ProductDto.class);
		Map<String, Object> data = new HashMap<>();
		data.put("user", findUser);
		data.put("product", findProduct);
		return data;
	}
	
	
	@Override
	public Map<String, Object> orderProductByCorp(long corp_id, long product_id) throws Exception{
		Corp corp = corpRepository.findById(corp_id).orElseThrow(()
				-> new CorpNotFoundException("존재하지 않습니다."));
		CorpDto findCorp = modelMapper.map(corp, CorpDto.class);
		Optional<Product> optionalProduct = productRepository.findById(product_id);
		Product product = optionalProduct.get();
		ProductDto findProduct = modelMapper.map(product, ProductDto.class);
		Map<String, Object> data = new HashMap<>();
		data.put("user", findCorp);
		data.put("product", findProduct);
		return data;
	}

	@Override
	public List<OrdersRequestDto> findOrder(String role, long id) {
		 if (role.equals("user")) {
			 List<Orders> findOrderList = ordersRepository.findByUserId(id);
			 List<OrdersRequestDto> orderList = findOrderList.stream()
					 .map(order -> modelMapper.map(order, OrdersRequestDto.class))
					 .collect(Collectors.toList());
			 return orderList;
		    } else {
		    	List<Orders> findOrderList = ordersRepository.findByCorpId(id);
		    	List<OrdersRequestDto> orderList = findOrderList.stream()
		    			.map(order -> modelMapper.map(order, OrdersRequestDto.class))
		    			.collect(Collectors.toList());
		    	return orderList;
		    }
	}
	
	private Orders saveOrder(String role, long id, ProductDto productDto) {
		if(role.equals("user")) {
			OrdersDto createOrderDto = OrdersDto.builder().orderStartDate(LocalDateTime.now())
					.orderEndDate(LocalDateTime.now().plusDays(productDto.getProductPeriod())).userId(id).orderValid(1)
					.productId(productDto.getId()).build();
			Orders createOrder = modelMapper.map(createOrderDto, Orders.class);
			return ordersRepository.save(createOrder);
		}else {
			OrdersDto createOrderDto = OrdersDto.builder().orderStartDate(LocalDateTime.now())
					.orderEndDate(LocalDateTime.now().plusDays(productDto.getProductPeriod())).corpId(id).orderValid(1)
					.productId(productDto.getId()).build();
			Orders createOrder = modelMapper.map(createOrderDto, Orders.class);
			return ordersRepository.save(createOrder);
		}
	}
	
	private void savePayment(OrdersDto ordersDto, ProductDto productDto , String paymentMethod) {
		PaymentDto paymentDto = PaymentDto.builder()
				.ordersId(ordersDto.getId()).userId(ordersDto.getUserId())
				.paymentDate(LocalDateTime.now()).paymentPrice(productDto.getProductPrice())
				.paymentMethod(paymentMethod).build();
		paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
	}


		
	
}
