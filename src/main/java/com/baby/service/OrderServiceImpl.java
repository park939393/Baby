package com.baby.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baby.mapper.AttachMapper;
import com.baby.mapper.CartMapper;
import com.baby.mapper.MemberMapper;
import com.baby.mapper.OrderMapper;
import com.baby.mapper.ProductMapper;
import com.baby.model.AttachImageVO;
import com.baby.model.CartDTO;
import com.baby.model.MemberVO;
import com.baby.model.OrderCancelDTO;
import com.baby.model.OrderDTO;
import com.baby.model.OrderItemDTO;
import com.baby.model.OrderPageItemDTO;
import com.baby.model.ProductVO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	@Autowired
	private AttachMapper attachMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private CartMapper cartMapper;
	
	@Autowired
	private ProductMapper productMapper;
	
	

	@Override
	public List<OrderPageItemDTO> getProductInfo(List<OrderPageItemDTO> orders) {

		List<OrderPageItemDTO> result = new ArrayList<OrderPageItemDTO>();

		for (OrderPageItemDTO ord : orders) {

			OrderPageItemDTO productInfo = orderMapper.getProductInfo(ord.getProductId());

			productInfo.setProductCount(ord.getProductCount());

			productInfo.initSaleTotal();

			List<AttachImageVO> imageList = attachMapper.getAttachList(productInfo.getProductId());

			productInfo.setImageList(imageList);

			result.add(productInfo);

		}

		return result;
	}

	@Override
	public void order(OrderDTO ord) {
		/* 사용할 데이터가져오기 */
		/* 회원 정보 */
		MemberVO member = memberMapper.getMemberInfo(ord.getMemberId());
		/* 주문 정보 */
		List<OrderItemDTO> ords = new ArrayList<>();
		for(OrderItemDTO oit : ord.getOrders()) {
			OrderItemDTO orderItem = orderMapper.getOrderInfo(oit.getProductId());
			// 수량 셋팅
			orderItem.setProductCount(oit.getProductCount());
			// 기본정보 셋팅
			orderItem.initSaleTotal();
			//List객체 추가
			ords.add(orderItem);
		}
		/* OrderDTO 셋팅 */
		ord.setOrders(ords);
		ord.getOrderPriceInfo();
		
	/*DB 주문,주문상품(,배송정보) 넣기*/
		
		/* orderId만들기 및 OrderDTO객체 orderId에 저장 */
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("_yyyyMMddmm");
		String orderId = member.getMemberId() + format.format(date);
		ord.setOrderId(orderId);
		
		/* db넣기 */
		orderMapper.enrollOrder(ord);		//vam_order 등록
		for(OrderItemDTO oit : ord.getOrders()) {		//vam_orderItem 등록
			oit.setOrderId(orderId);
			orderMapper.enrollOrderItem(oit);			
		}

	/* 비용 포인트 변동 적용 */
		
		/* 비용 차감 & 변동 돈(money) Member객체 적용 */
		int calMoney = member.getMoney();
		calMoney -= ord.getOrderFinalSalePrice();
		member.setMoney(calMoney);
		
		/* 포인트 차감, 포인트 증가 & 변동 포인트(point) Member객체 적용 */
		int calPoint = member.getPoint();
		calPoint = calPoint - ord.getUsePoint() + ord.getOrderSavePoint();	// 기존 포인트 - 사용 포인트 + 획득 포인트
		member.setPoint(calPoint);
			
		/* 변동 돈, 포인트 DB 적용 */
		orderMapper.deductMoney(member);
		
	/* 재고 변동 적용 */
		for(OrderItemDTO oit : ord.getOrders()) {
			/* 변동 재고 값 구하기 */
			ProductVO product = productMapper.getProductInfo(oit.getProductId());
			product.setProductStock(product.getProductStock() - oit.getProductCount());
			/* 변동 값 DB 적용 */
			orderMapper.deductStock(product);
		}			
		
	/* 장바구니 제거 */
		for(OrderItemDTO oit : ord.getOrders()) {
			CartDTO dto = new CartDTO();
			dto.setMemberId(ord.getMemberId());
			dto.setProductId(oit.getProductId());
			
			cartMapper.deleteOrderCart(dto);
		}
		
		
		
	}
	
	/* 주문취소 */
	@Override
	@Transactional
	public void orderCancle(OrderCancelDTO dto) {
		
		/* 주문, 주문상품 객체 */
		/*회원*/
			MemberVO member = memberMapper.getMemberInfo(dto.getMemberId());
		/*주문상품*/
			List<OrderItemDTO> ords = orderMapper.getOrderItemInfo(dto.getOrderId());
			for(OrderItemDTO ord : ords) {
				ord.initSaleTotal();
			}
		/* 주문 */
			OrderDTO orw = orderMapper.getOrder(dto.getOrderId());
			orw.setOrders(ords);
			
			orw.getOrderPriceInfo();
			
	/* 주문상품 취소 DB */
			orderMapper.orderCancle(dto.getOrderId());
			
	/* 돈, 포인트, 재고 변환 */
			/* 돈 */
			int calMoney = member.getMoney();
			calMoney += orw.getOrderFinalSalePrice();
			member.setMoney(calMoney);
			
			/* 포인트 */
			int calPoint = member.getPoint();
			calPoint = calPoint + orw.getUsePoint() - orw.getOrderSavePoint();
			member.setPoint(calPoint);
			
				/* DB적용 */
				orderMapper.deductMoney(member);
				
			/* 재고 */
			for(OrderItemDTO ord : orw.getOrders()) {
				ProductVO product = productMapper.getProductInfo(ord.getProductId());
				product.setProductStock(product.getProductStock() + ord.getProductCount());
				orderMapper.deductStock(product);
			}
		
		
	}

}
