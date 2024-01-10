package asw.ordermanager.orderservice.domain;

import asw.ordermanager.orderservice.api.common.OrderItemElement;
import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderEventPublisher orderEventPublisher;


 	public Order createOrder(String customer, String address, List<OrderItem> orderItems, double total) {
		Order order = new Order(customer, address, orderItems, total); 
		order = orderRepository.save(order);
		orderEventPublisher.publish(toOrderCreatedEvent(order));
		return order;
	}

 	public Order getOrder(Long id) {
		Order order = orderRepository.findById(id).orElse(null);
		return order;
	}

	public Collection<Order> getOrders() {
		Collection<Order> orders = orderRepository.findAll();
		return orders;
	}

	public Collection<Order> getOrdersByCustomer(String customer) {
		Collection<Order> orders = orderRepository.findByCustomer(customer);
		return orders;
	}

	public Collection<Order> getOrdersByProduct(String product) {
		Collection<Order> orders = orderRepository.findByOrderItems_Product(product);
		return orders;
	}

	private OrderCreatedEvent toOrderCreatedEvent(Order order) {
		return new OrderCreatedEvent(
				order.getId(),
				order.getCustomer(),
				order.getOrderItems()
						.stream()
						.map(item -> new OrderItemElement(
										item.getProduct(),
										item.getQuantity()
								)
						)
						.toList(),
				order.getTotal());
	}
}
