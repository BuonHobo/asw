package asw.ordermanager.ordervalidationservice.producteventlistener;

import asw.ordermanager.ordervalidationservice.domain.ProductEventConsumer;
import asw.ordermanager.productservice.api.event.ProductEvent;
import asw.ordermanager.productservice.api.event.ProductServiceEventChannel;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class ProductEventKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private ProductEventConsumer productEventConsumer;

    @KafkaListener(topics = ProductServiceEventChannel.channel, groupId = "${spring.kafka.consumer.group-id}-products")
    public void listen(ConsumerRecord<String, ProductEvent> record) throws Exception {
        logger.info("EVENT LISTENER: " + record.toString());
        ProductEvent event = record.value();
        productEventConsumer.onEvent(event);
    }

}