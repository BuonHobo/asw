package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductEvent;
import asw.ordermanager.productservice.api.event.ProductStockLevelUpdatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ProductEventConsumer {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    ProductRepository productRepository;

    public void onEvent(ProductEvent event) {
        if (event instanceof ProductCreatedEvent evt) {
            onProductCreated(evt);
        } else if (event instanceof ProductStockLevelUpdatedEvent evt) {
            onProductStockLevelUpdated(evt);
        } else {
            logger.info("UNKNOWN EVENT: " + event);
        }
    }

    private void onProductStockLevelUpdated(ProductStockLevelUpdatedEvent evt) {
        var optProduct = productRepository.findById(evt.getName());
        if (optProduct.isPresent()) {
            Product product = optProduct.get();
            product.setStockLevel(product.getStockLevel() + evt.getStockLevelVariation());
            productRepository.save(product);
            logger.info("UPDATED PRODUCT: " + product);
        } else {
            logger.info("ERROR: Received a product update event for a product that does not exist");
        }
    }

    private void onProductCreated(ProductCreatedEvent event) {
        Product product = new Product(event);
        productRepository.save(product);
        logger.info("CREATED PRODUCT: " + product);
    }

}