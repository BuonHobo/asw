package asw.ordermanager.ordervalidationservice.domain;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public interface OrderValidationService {

	/* Verifica la validità di un ordine. */ 
	public OrderValidation validateOrder(Long id); 

	default OrderValidation validateOrder(Order order, List<Product> products) {
		Map<String,Product> productMap = toProductMap(products);
		String motivation = "";

		boolean isValid = true;
		for (OrderItem orderItem: order.getOrderItems()) {
			String name = orderItem.getProduct();
			Product product = productMap.get(name);
			if (product==null) {
				isValid = false;
				motivation += "Il prodotto " + name + " non esiste. ";
				// break;
			} else if (product.getStockLevel() < orderItem.getQuantity()) {
				isValid = false;
				motivation += "Il prodotto " + name + " non è disponibile nella quantità richiesta. ";
				// break;
			}
		}
		if (motivation.length()==0) {
			motivation = "OK";
		}
		return new OrderValidation(order.getId(), order, isValid, motivation);
	}

	private Map<String,Product> toProductMap(Collection<Product> products) {
		Map<String,Product> map =
				products.stream()
						.collect(Collectors.toMap(Product::getName, Function.identity()));
		return map;
	}
}
