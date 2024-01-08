package asw.ordermanager.ordervalidationservice.domain;

import java.util.*;
import java.util.stream.Collectors;

import asw.ordermanager.orderservice.api.event.OrderCreatedEvent;
import jakarta.persistence.*;
import lombok.*;

/* Ordine. */
@Entity
@Table(name="ORDERS") // altrimenti crea una tabella ORDER, che in SQL è un nome riservato
@Data 
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order implements Comparable<Order> {

	@Id
	@GeneratedValue
	@EqualsAndHashCode.Include
	private Long id;
	private String customer;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<OrderItem> orderItems;
	private double total;

	@Override
	public int compareTo(Order other) {
		return this.id.compareTo(other.id); 
	}

	public Order(OrderCreatedEvent event){
		this.setId(event.getId());
		this.setCustomer(event.getCustomer());
		this.setOrderItems(event.getOrderItems().stream().map(OrderItem::new).toList());
		this.setTotal(event.getTotal());
	}

	public List<String> getProductNames(){
		List<String> names =
				this.getOrderItems().stream()
						.map(OrderItem::getProduct)
						.collect(Collectors.toList());
		return names;
	}
}
