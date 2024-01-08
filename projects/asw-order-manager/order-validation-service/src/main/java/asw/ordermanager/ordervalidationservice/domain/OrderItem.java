package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import jakarta.persistence.Embeddable;
import lombok.*;

/* Ordine. */ 
@Data
@Embeddable
@NoArgsConstructor @AllArgsConstructor
public class OrderItem {

	private String product; 
	private int quantity;

    public OrderItem(OrderItemElement orderItemElement) {
        this.setProduct(orderItemElement.getProduct());
        this.setQuantity(orderItemElement.getQuantity());
    }
}
