package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.BrandDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BrandModel {
    public String getNextBrandId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select brandId from brand order by brandId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last brand ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("B%03d", newIdIndex); // Return the new brand ID in format Cnnn
        }
        return "B001"; // Return the default brand ID if no data is found
    }

    public String getBrandNameById(String categoryId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select brandName from brand where brandId=?", categoryId);

        if (rst.next()) {
            return rst.getString("brandName");
        }
        return null;
    }

    public ArrayList<BrandDTO> getAllBrands() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from customer");

        ArrayList<BrandDTO> brandDTOS = new ArrayList<>();

        while (rst.next()) {
            BrandDTO brandDTO = new BrandDTO(
                    rst.getString(1), // Brand ID
                    rst.getString(2),  // Brand Name
                    rst.getString(3)    // Description
            );
            brandDTOS.add(brandDTO);
        }
        return brandDTOS;
    }

    public BrandDTO findById(String selectedBrandId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from brand where brandId=?", selectedBrandId);

        if (rst.next()) {
            return new BrandDTO(
                    rst.getString(1),  // Brand ID
                    rst.getString(2),  // Brand Name
                    rst.getString(3)  // Description
            );
        }
        return null;
    }

    public ArrayList<String> getAllBrandIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select brandId from brand");

        ArrayList<String> brandIds = new ArrayList<>();

        while (rst.next()) {
            brandIds.add(rst.getString(1));
        }
        return brandIds;
    }

    public boolean saveBrand(BrandDTO brandDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into brand values (?,?,?)",
                brandDTO.getBrandId(),  // Brand ID
                brandDTO.getBrandName(),  // Brand Name
                brandDTO.getDescription()   // Description
        );
    }

    public boolean updateBrand(BrandDTO brandDTO) throws SQLException {
        return CrudUtil.execute(
                "update brand set brandName=?, description=? where brandId=?",
                brandDTO.getDescription(),  // Description
                brandDTO.getBrandId(),  // Brand ID
                brandDTO.getBrandName()   // Brand Name
        );
    }

    public boolean deleteBrand(String brandId) throws SQLException {
        return CrudUtil.execute("delete from brand where brandId=?", brandId);
    }
}
