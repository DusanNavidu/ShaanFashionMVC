package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.tm.UserDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public String getNextUserId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select userId from user order by userId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("U%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "U001"; // Return the default customer ID if no data is found
    }

    public boolean saveUser(UserDTO userDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into user values (?,?,?,?,?)",
                userDTO.getUserId(),
                userDTO.getUserFullName(),
                userDTO.getUsername(),
                userDTO.getUserEmail(),
                userDTO.getPassword()
        );
    }

    public boolean updateUser(UserDTO userDTO) throws SQLException {
        return CrudUtil.execute(
                "update customer set userFullName=?, username=?, userEmail=?, password=? where userId=?",
                userDTO.getUserFullName(),
                userDTO.getUsername(),
                userDTO.getUserEmail(),
                userDTO.getPassword(),
                userDTO.getUserId()
        );
    }

    public UserDTO findById(String selectedUserId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from user where userId=?", selectedUserId);

        if (rst.next()) {
            return new UserDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            );
        }
        return null;
    }
}