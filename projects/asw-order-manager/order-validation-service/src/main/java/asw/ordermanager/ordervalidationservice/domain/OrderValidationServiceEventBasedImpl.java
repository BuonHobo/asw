package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("event")
public class OrderValidationServiceEventBasedImpl implements OrderValidationService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderRepository orderRepository;


    @Override
    public OrderValidation validateOrder(Long id) {
        var optOrder = orderRepository.findById(id);
        if (optOrder.isEmpty()){
            String motivation = "L'ordine " + id + " non esiste.";
            return new OrderValidation(id, null, false, motivation);
        }
        Order order = optOrder.get();
        List<String> productNames = order.getProductNames();
        List<Product> products = productRepository.findByNameIn(productNames);
        return validateOrder(order,products);
    }

    @Override
    public OrderValidation validateOrder(Order order, List<Product> products) {
        return OrderValidationService.super.validateOrder(order, products);
    }
}
