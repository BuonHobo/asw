package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import asw.ordermanager.orderservice.api.event.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class OrderEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    OrderRepository orderRepository;

    public void onEvent(OrderEvent event) {
        if (event instanceof OrderCreatedEvent evt) {
            onOrderCreated(evt);
        } else {
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

    private void onOrderCreated(OrderCreatedEvent event) {
        Order order = new Order(event);
        orderRepository.save(order);
        logger.info("CREATED ORDER: " + order);
    }

}