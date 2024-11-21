package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailsDTO {
    private String itemId;
    private String ordersId;
    private int qty;
    private BigDecimal price;
    private Time time;
    private Date date;
}
