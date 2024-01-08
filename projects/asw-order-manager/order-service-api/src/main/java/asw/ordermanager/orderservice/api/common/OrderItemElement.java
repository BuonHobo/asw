package asw.ordermanager.orderservice.api.common;

import lombok.*;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrderItemElement {

	private String product; 
	private int quantity; 

}

