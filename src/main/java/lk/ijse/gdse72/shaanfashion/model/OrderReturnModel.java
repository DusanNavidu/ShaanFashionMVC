package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.OrderReturnDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderReturnModel {
    public String getNextOrderReturnId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select returnOrderId from return_order order by returnOrderId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001";
    }

    public boolean saveOrderReturn(OrderReturnDTO orderReturnDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into return_order values (?,?,?,?,?)",
                orderReturnDTO.getReturnOrderId(),
                orderReturnDTO.getOrderId(),
                orderReturnDTO.getReason(),
                orderReturnDTO.getPrice(),
                orderReturnDTO.getItemName()
        );
    }

    public ArrayList<OrderReturnDTO> getAllReturns() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from return_order");

        ArrayList<OrderReturnDTO> orderReturnDTOS = new ArrayList<>();

        while (rst.next()) {
            OrderReturnDTO orderReturnDTO = new OrderReturnDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getBigDecimal(4),  // Email
                    rst.getString(5)   // Phone
            );
            orderReturnDTOS.add(orderReturnDTO);
        }
        return orderReturnDTOS;
    }

    public boolean updateOrderReturns(OrderReturnDTO orderReturnDTO) throws SQLException {
        return CrudUtil.execute(
                "update return_order set orderId=?, reason=?, price=?, itemName=? where returnOrderId=?",
                orderReturnDTO.getOrderId(),
                orderReturnDTO.getReason(),
                orderReturnDTO.getPrice(),
                orderReturnDTO.getItemName(),
                orderReturnDTO.getReturnOrderId()
        );
    }

    public boolean deleteOrderReturns(String returnOrderId) throws SQLException {
        return CrudUtil.execute("delete from return_order where returnOrderId=?", returnOrderId);
    }

    public OrderReturnDTO findById(String selectedROId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from return_order where returnOrderId=?", selectedROId);

        if (rst.next()) {
            return new OrderReturnDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // NIC
                    rst.getBigDecimal(4),  // Email
                    rst.getString(5)   // Phone
            );
        }
        return null;
    }
}
