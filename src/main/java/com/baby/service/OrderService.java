package com.baby.service;

import java.util.List;

import com.baby.model.OrderCancelDTO;
import com.baby.model.OrderDTO;
import com.baby.model.OrderPageItemDTO;

public interface OrderService {

	/* 주문 상품 정보 */	
	public List<OrderPageItemDTO> getProductInfo(List<OrderPageItemDTO> orders);
	
	/* 주문 */
	public void  order(OrderDTO orw);
	
	/* 주문 취소 */
	public void orderCancle(OrderCancelDTO dto);

}
