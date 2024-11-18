package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.ItemDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemModel {

    public String getNextItemId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select itemId from item order by itemId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("I%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "I001"; // Return the default customer ID if no data is found
    }

    public String getNexBatchNumber() throws SQLException {
        ResultSet rst = CrudUtil.execute("select batchNumber from item order by batchNumber desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last batch number
            String numericPart = lastId.replaceAll("[^\\d]", ""); // Extract numeric part ignoring characters
            int i = Integer.parseInt(numericPart); // Convert the numeric part to an integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("B%05d", newIdIndex); // Format to 5 digits with leading zeros
        }
        return "B00001"; // Return the default batch number if no data is found
    }

    public ArrayList<ItemDTO> getAllItems() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from item");

        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();

        while (rst.next()) {
            ItemDTO itemDTO = new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getBigDecimal(8),
                    rst.getBigDecimal(9)
            );
            itemDTOS.add(itemDTO);
        }
        return itemDTOS;
    }

    public ArrayList<String> getAllItemIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select itemId from item");

        ArrayList<String> itemIds = new ArrayList<>();

        while (rst.next()) {
            itemIds.add(rst.getString(1));
        }

        return itemIds;
    }

    public ItemDTO findById(String selectedItemId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from item where itemId=?", selectedItemId);

        if (rst.next()) {
            return new ItemDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7),
                    rst.getBigDecimal(8),
                    rst.getBigDecimal(9)
            );
        }
        return null;
    }

    public boolean saveItem(ItemDTO itemDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into customer values (?,?,?,?,?,?,?,?,?)",
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getCategoryId(),
                itemDTO.getBrandId(),
                itemDTO.getItemQuantityOnHand(),
                itemDTO.getBatchNumber(),
                itemDTO.getDescription(),
                itemDTO.getPrice(),
                itemDTO.getProfit()
        );
    }

    public boolean updateItem(ItemDTO itemDTO) throws SQLException {
        return CrudUtil.execute(
                "update item set itemName=?, categoryId=?, brandId=?, itemQuantityOnHand=?, batchNumber=?, description=?, price=?, profit=? where itemId=?",
                itemDTO.getItemId(),
                itemDTO.getItemName(),
                itemDTO.getCategoryId(),
                itemDTO.getBrandId(),
                itemDTO.getItemQuantityOnHand(),
                itemDTO.getBatchNumber(),
                itemDTO.getDescription(),
                itemDTO.getPrice(),
                itemDTO.getProfit()
        );
    }

    public boolean deleteCustomer(String itemId) throws SQLException {
        return CrudUtil.execute("delete from item where itemId=?", itemId);
    }

    /*public boolean reduceQty(OrderDetailsDTO orderDetailsDTO) throws SQLException {
        return CrudUtil.execute(
                "update item set quantity = quantity - ? where item_id = ?",
                orderDetailsDTO.getQuantity(),   // Quantity to reduce
                orderDetailsDTO.getItemId()      // Item ID
        );
    }*/
}
