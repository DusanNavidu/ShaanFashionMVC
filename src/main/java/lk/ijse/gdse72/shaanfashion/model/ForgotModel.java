package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.CustomerDTO;
import lk.ijse.gdse72.shaanfashion.dto.UserDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotModel {

    public void getEmail() throws SQLException {
        ResultSet rst = CrudUtil.execute("SELECT userEmail FROM User WHERE userID = 'U001'");
    }

    public boolean updateUser(UserDTO userDTO) throws SQLException {
        String sql = "UPDATE User SET password = ? WHERE userID = 'U001'";
        return CrudUtil.execute(sql,
                userDTO.getPassword()
        );
    }
}
