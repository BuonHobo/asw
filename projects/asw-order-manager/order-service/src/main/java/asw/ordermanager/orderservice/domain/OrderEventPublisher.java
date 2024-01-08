package asw.ordermanager.orderservice.domain;

import asw.ordermanager.orderservice.api.event.OrderEvent;

public interface OrderEventPublisher {

    void publish(OrderEvent event);

}
