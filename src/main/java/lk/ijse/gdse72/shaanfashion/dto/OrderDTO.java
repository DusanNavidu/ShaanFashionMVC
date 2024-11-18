package lk.ijse.gdse72.shaanfashion.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDTO {
    private String OrdersId;
    private String customerId;
    private BigDecimal totalAmount;
    private String returnOrderId;
    private String description;
}
