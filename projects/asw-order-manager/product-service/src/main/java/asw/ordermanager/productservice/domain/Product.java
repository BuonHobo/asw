package asw.ordermanager.productservice.domain;

import asw.ordermanager.productservice.api.event.ProductCreatedEvent;
import asw.ordermanager.productservice.api.event.ProductStockLevelUpdatedEvent;
import jakarta.persistence.*;

import lombok.*; 

/* Prodotto con inventario. */ 
@Entity 
@Data @NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product implements Comparable<Product> {

	@Id 
	@EqualsAndHashCode.Include
	private String name; 
	private String category; 
	/* quantità disponibile */ 
	private int stockLevel; 
	/* prezzo di listino */ 
	private double price; 

	public Product(String name, String category, int stockLevel, double price) {
		this(); 
		this.name = name; 
		this.category = category; 
		this.stockLevel = stockLevel; 
		this.price = price; 
	}

	@Override
	public int compareTo(Product other) {
		return this.name.compareTo(other.name); 
	}

	public ProductCreatedEvent toProductCreatedEvent(){
		return new ProductCreatedEvent(this.getName(),this.getStockLevel(),this.getPrice());
	}

	public ProductStockLevelUpdatedEvent toProductStockLevelUpdatedEvent(int stockLevelVariation){
		return new ProductStockLevelUpdatedEvent(this.getName(),stockLevelVariation);
	}
	
}
