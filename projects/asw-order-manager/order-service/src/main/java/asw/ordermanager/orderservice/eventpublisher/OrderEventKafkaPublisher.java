package asw.ordermanager.orderservice.eventpublisher;

import asw.ordermanager.orderservice.api.event.OrderEvent;
import asw.ordermanager.orderservice.api.event.OrderServiceEventChannel;
import asw.ordermanager.orderservice.domain.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class OrderEventKafkaPublisher implements OrderEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, OrderEvent> template;

    private final String channel = OrderServiceEventChannel.channel;

    @Override
    public void publish(OrderEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }

}