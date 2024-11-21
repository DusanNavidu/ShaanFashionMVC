package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemDTO {
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
