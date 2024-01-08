package asw.ordermanager.productservice.eventpublisher;

import asw.ordermanager.productservice.api.event.ProductEvent;
import asw.ordermanager.productservice.api.event.ProductServiceEventChannel;
import asw.ordermanager.productservice.domain.ProductEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProductEventKafkaPublisher implements ProductEventPublisher {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private KafkaTemplate<String, ProductEvent> template;

    private final String channel = ProductServiceEventChannel.channel;

    @Override
    public void publish(ProductEvent event) {
        logger.info("EVENT PUBLISHER: " + event.toString() + " ON CHANNEL: " + channel);
        template.send(channel, event);
        // template.flush();
    }
}
