package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailsDTO {
    private String orderId;
    private String itemId;
    private int quantity;
    private double price;
}
