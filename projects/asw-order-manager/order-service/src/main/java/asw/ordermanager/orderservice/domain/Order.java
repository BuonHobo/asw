package asw.ordermanager.orderservice.domain;

import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import jakarta.persistence.*;

import java.util.*;

import lombok.*; 

/* Ordine. */ 
@Entity 
@Table(name="ORDERS") // altrimenti crea una tabella ORDER, che in SQL è un nome riservato
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Comparable<Order> {

	@Id 
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id; 
	
	private String customer; 
	private String address; 
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	private double total; 

	public Order(String customer, String address, List<OrderItem> orderItems, double total) {
		this(); 
		this.customer = customer; 
		this.address = address; 
		this.orderItems = orderItems; 
		this.total = total; 
	}

	@Override
	public int compareTo(Order other) {
		return this.id.compareTo(other.id); 
	}

	public OrderCreatedEvent toOrderCreatedEvent(){
		return new OrderCreatedEvent(this.getId(),this.getCustomer(),this.getOrderItems().stream().map(OrderItem::toOrderItemElement).toList(),this.getTotal());
	}
}
