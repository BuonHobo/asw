package asw.ordermanager.orderservice.api.event;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class OrderCreatedEvent implements OrderEvent{
    private Long id;
    private String customer;
    private List<OrderItemElement> orderItems;
    private double total;
}