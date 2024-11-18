package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.SupplierDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {
    public String getNextSupplierId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select supplierId from supplier order by supplierId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("S%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "S001"; // Return the default customer ID if no data is found
    }

    public ArrayList<SupplierDTO> getAllSuppliers() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier");

        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

        while (rst.next()) {
            SupplierDTO supplierDTO = new SupplierDTO(
                    rst.getString(1),  // Supplier ID
                    rst.getString(2),  // Name
                    rst.getString(3),  // Item
                    rst.getString(4),  // Address
                    rst.getString(5)   // Phone
            );
            supplierDTOS.add(supplierDTO);
        }
        return supplierDTOS;
    }

    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into supplier values (?,?,?,?,?)",
                supplierDTO.getSupplierId(),
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplyItem(),
                supplierDTO.getSupplierAddress(),
                supplierDTO.getContactNo()
        );
    }

    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException {
        return CrudUtil.execute(
                "update supplier set supplierName=?, supplyItem=?, supplierAddress=?, contactNo=? where supplierId=?",
                supplierDTO.getSupplierId(),
                supplierDTO.getSupplierName(),
                supplierDTO.getSupplyItem(),
                supplierDTO.getSupplierAddress(),
                supplierDTO.getContactNo()
        );
    }

    public boolean deleteSupplier(String supplierId) throws SQLException {
        return CrudUtil.execute("delete from supplier where supplierId=?", supplierId);
    }

    public ArrayList<String> getAllSupplierIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select supplierId from supplier");

        ArrayList<String> supplierIds = new ArrayList<>();

        while (rst.next()) {
            supplierIds.add(rst.getString(1));
        }

        return supplierIds;
    }

    public SupplierDTO findById(String selectedSupplierId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from supplier where supplierId=?", selectedSupplierId);

        if (rst.next()) {
            return new SupplierDTO(
                    rst.getString(1),  // supplier ID
                    rst.getString(2),  // supplier Name
                    rst.getString(3),  // supply Item
                    rst.getString(4),  // supplier Address
                    rst.getString(5)   // contact No
            );
        }
        return null;
    }
}
