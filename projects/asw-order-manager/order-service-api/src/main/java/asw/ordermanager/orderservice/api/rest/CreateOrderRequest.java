package asw.ordermanager.orderservice.api.rest;

import java.util.*;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class CreateOrderRequest {

	private String customer; 
	private String address;
	private List<OrderItemElement> orderItems;
	private double total; 

}

