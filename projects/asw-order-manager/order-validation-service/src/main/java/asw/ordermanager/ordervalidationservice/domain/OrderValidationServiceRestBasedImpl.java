package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
@Profile("rest")
public class OrderValidationServiceRestBasedImpl implements OrderValidationService {

	@Autowired 
	private OrderServiceClientPort orderServiceClient;

	@Autowired 
	private ProductServiceClientPort productServiceClient;

	/* Verifica la validità di un ordine. 
	 * Nota: con una sola chiamata REST trova tutti i prodotti dell'ordine. */ 
	public OrderValidation validateOrder(Long id) {
		Order order = orderServiceClient.getOrder(id); 
		if (order==null) {
			String motivation = "L'ordine " + id + " non esiste.";
			return new OrderValidation(id, order, false, motivation);
		}
		List<String> productNames = order.getProductNames();
		List<Product> products = productServiceClient.getProductsByNames(productNames);
		return validateOrder(order,products);
	}

}
