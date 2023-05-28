package com.itwill.ilhajob.common.service;

import java.util.List;
import java.util.Map;

import com.itwill.ilhajob.common.dto.OrdersDto;
import com.itwill.ilhajob.common.dto.OrdersRequestDto;
import com.itwill.ilhajob.common.dto.PaymentDto;
import com.itwill.ilhajob.common.dto.ProductDto;

public interface OrdersService {
	//Orders savaOrder(String role, long id, ProductDto productDto);
	OrdersDto checkAndSaveOrder(String role, long id, ProductDto productDto, String paymentMethod) throws Exception;
	void removeOrder();
	List<OrdersDto> findOrderByUser(long id);
	List<OrdersRequestDto> findOrder(String role, long id);
	List<OrdersDto> findOrderByCorp(long id);
	void reorderingCheckUpdate(String role, long id);
	List<PaymentDto> findPayment(String role, long id);
	Map<String, Object> orderProductByUser(long user_id, long product_id) throws Exception;
	Map<String, Object> orderProductByCorp(long corp_id, long product_id) throws Exception;
}
