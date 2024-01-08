package asw.ordermanager.productservice.domain;

import asw.ordermanager.productservice.api.event.ProductEvent;

public interface ProductEventPublisher {

    void publish(ProductEvent event);
}
