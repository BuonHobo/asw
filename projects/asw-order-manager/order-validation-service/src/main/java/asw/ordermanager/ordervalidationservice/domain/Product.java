package asw.ordermanager.ordervalidationservice.domain;

import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

/* Prodotto con inventario. */ 
@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Comparable<Product> {

	@Id
	@EqualsAndHashCode.Include
	private String name; 
	/* quantità disponibile */ 
	private int stockLevel; 
	/* prezzo di listino */ 
	private double price;

	public Product(ProductCreatedEvent event) {
		this.setName(event.getName());
		this.setStockLevel(event.getStockLevel());
		this.setPrice(event.getPrice());
	}

	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.name); 
	}
	
}
