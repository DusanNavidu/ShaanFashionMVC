package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.CustomerDTO;
import lk.ijse.gdse72.shaanfashion.dto.OrderDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderModel {
    public String getNextCustomerId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select ordersId from orders order by ordersId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("O%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "O001"; // Return the default customer ID if no data is found
    }

    public ArrayList<OrderDTO> getAllOrders() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from orders");

        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();

        while (rst.next()) {
            OrderDTO orderDTO = new OrderDTO(
                    rst.getString(1),  //  order id
                    rst.getString(2),  // customer id
                    rst.getBigDecimal(3),  // total
                    rst.getString(4)  // description
            );
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
