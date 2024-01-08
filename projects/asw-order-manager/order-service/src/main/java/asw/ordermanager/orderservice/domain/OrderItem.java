package asw.ordermanager.orderservice.domain;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import jakarta.persistence.*;

import lombok.*; 

/* Ordine. */ 
@Embeddable
@Data 
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	private String product; 
	private int quantity;

    public OrderItemElement toOrderItemElement() {
        return new OrderItemElement(this.getProduct(),this.getQuantity());
    }
}
