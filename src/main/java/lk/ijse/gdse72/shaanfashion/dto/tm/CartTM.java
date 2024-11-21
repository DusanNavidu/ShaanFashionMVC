package lk.ijse.gdse72.shaanfashion.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartTM {
    private String itemId;
    private String itemName;
    private int cartQuantity;
    private double unitPrice;
    private double total;
    private Button removeBtn;
}
