package asw.ordermanager.productservice.api.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockLevelUpdatedEvent implements ProductEvent{

    private String name;
    private int stockLevelVariation;

}