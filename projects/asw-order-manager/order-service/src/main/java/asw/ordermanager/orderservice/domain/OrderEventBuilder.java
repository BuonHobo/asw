package asw.ordermanager.orderservice.domain;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;

@Component
public class OrderEventBuilder {

    public OrderCreatedEvent toOrderCreatedEvent(Order order){
        return new OrderCreatedEvent(
                order.getId(),
                order.getCustomer(),
                order.getOrderItems()
                        .stream()
                        .map(this::toOrderItemElement)
                        .toList(),
                order.getTotal());
    }

    public OrderItemElement toOrderItemElement(OrderItem item) {
        return new OrderItemElement(item.getProduct(),item.getQuantity());
    }
}
