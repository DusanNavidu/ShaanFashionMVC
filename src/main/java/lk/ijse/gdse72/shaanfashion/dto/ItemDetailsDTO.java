package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ItemDetailsDTO {
    private String itemName;
    private String categoryId;
    private String brandId;
    private String itemQuantityOnHand;
    private String batchNumber;
    private String description;
    private BigDecimal price;
    private BigDecimal profit;
}
