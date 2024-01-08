package asw.ordermanager.orderservice.api.rest;

import java.util.*;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import lombok.*;

@Data 
@NoArgsConstructor @AllArgsConstructor
public class GetOrderResponse {

	private Long id; 
	private String customer; 
	private List<OrderItemElement> orderItems;
	private double total; 

}

