package asw.ordermanager.productservice.api.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent implements ProductEvent{
    private String name;
    private String category;
    private int stockLevel;
    private double price;
}

