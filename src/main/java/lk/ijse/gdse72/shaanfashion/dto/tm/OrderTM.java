package lk.ijse.gdse72.shaanfashion.dto.tm;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OrderTM {
    private String OrdersId;
    private String customerId;
    private BigDecimal totalAmount;
    private String returnOrderId;
    private String description;
}
