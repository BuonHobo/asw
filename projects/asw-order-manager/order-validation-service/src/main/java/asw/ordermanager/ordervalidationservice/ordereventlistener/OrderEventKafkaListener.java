package asw.ordermanager.ordervalidationservice.ordereventlistener;

import asw.ordermanager.orderservice.api.event.OrderEvent;
import asw.ordermanager.orderservice.api.event.OrderServiceEventChannel;
import asw.ordermanager.ordervalidationservice.domain.OrderEventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderEventKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private OrderEventConsumer orderEventConsumer;

    @KafkaListener(topics = OrderServiceEventChannel.channel, groupId = "${spring.kafka.consumer.group-id}-orders")
    public void listen(ConsumerRecord<String, OrderEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        OrderEvent event = record.value();
        orderEventConsumer.onEvent(event);
    }

}