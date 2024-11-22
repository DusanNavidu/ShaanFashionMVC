package lk.ijse.gdse72.shaanfashion.dto.tm;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ReturnTM {
    private String returnOrderId;
    private String orderId;
    private String reason;
    private BigDecimal price;
    private String itemName;
}
