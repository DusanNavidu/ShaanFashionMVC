package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderReturnDTO {
    private String returnOrderId;
    private String orderId;
    private String reason;
    private BigDecimal price;
    private String itemName;
}
