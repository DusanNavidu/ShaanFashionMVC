package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.CustomerDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {
    public String getNextCustomerId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer order by customerId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("C%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "C001"; // Return the default customer ID if no data is found
    }

    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDTO> customerDTOS = new ArrayList<>();

        while (rst.next()) {
            CustomerDTO customerDTO = new CustomerDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // User ID
                    rst.getString(3),  // Name
                    rst.getString(4),  // Address
                    rst.getString(5)   // Email
            );
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into customer values (?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getUserId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerEmail()
        );
    }

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException {
        String sql = "UPDATE Customer SET userId = ?, customerName = ?, customerAddress = ?, customerEmail = ? WHERE customerId = ?";
        return CrudUtil.execute(sql,
                customerDTO.getUserId(),
                customerDTO.getCustomerName(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerEmail(),
                customerDTO.getCustomerId()
        );
    }

    public boolean deleteCustomer(String customerId) throws SQLException {
        return CrudUtil.execute("delete from customer where customerId=?", customerId);
    }

    public ArrayList<String> getAllCustomerIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select customerId from customer");

        ArrayList<String> customerIds = new ArrayList<>();

        while (rst.next()) {
            customerIds.add(rst.getString(1));
        }

        return customerIds;
    }

    public CustomerDTO findById(String selectedCusId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer where customerId=?", selectedCusId);

        if (rst.next()) {
            return new CustomerDTO(
                    rst.getString(1),  // Customer ID
                    rst.getString(2),  // User ID
                    rst.getString(3),  // Name
                    rst.getString(4),  // Address
                    rst.getString(5)   // Email
            );
        }
        return null;
    }
}
