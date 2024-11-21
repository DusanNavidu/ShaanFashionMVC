package lk.ijse.gdse72.shaanfashion.dto.tm;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class ItemTM {
    private String itemId;
    private String itemName;
    private String categoryId;
    private String brandId;
    private String itemQuantityOnHand;
    private String batchNumber;
    private String description;
    private double price;
    private BigDecimal profit;
}
