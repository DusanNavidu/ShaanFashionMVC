package lk.ijse.gdse72.shaanfashion.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDTO {
    private String orderId;
    private String customerId;
    private Date orderDate;

    private ArrayList<OrderDetailsDTO> orderDetailsDTOS;
}
