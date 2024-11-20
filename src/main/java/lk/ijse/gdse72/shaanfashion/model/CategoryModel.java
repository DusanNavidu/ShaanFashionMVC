package lk.ijse.gdse72.shaanfashion.model;

import lk.ijse.gdse72.shaanfashion.dto.CategoryDTO;
import lk.ijse.gdse72.shaanfashion.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryModel {

    public String getNextCategoryId() throws SQLException {
        ResultSet rst = CrudUtil.execute("select categoryId from category order by categoryId desc limit 1");

        if (rst.next()) {
            String lastId = rst.getString(1); // Last customer ID
            String substring = lastId.substring(1); // Extract the numeric part
            int i = Integer.parseInt(substring); // Convert the numeric part to integer
            int newIdIndex = i + 1; // Increment the number by 1
            return String.format("CT%03d", newIdIndex); // Return the new customer ID in format Cnnn
        }
        return "CT001"; // Return the default customer ID if no data is found
    }

    public ArrayList<CategoryDTO> getAllCategoryS() throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from category");

        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();

        while (rst.next()) {
            CategoryDTO customerDTO = new CategoryDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
            categoryDTOS.add(customerDTO);
        }
        return categoryDTOS;
    }

    public String getCategoryNameById(String categoryId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select categoryName from category where categoryId=?", categoryId);

        if (rst.next()) {
            return rst.getString("categoryName");
        }
        return null;
    }

    public boolean saveCategory(CategoryDTO categoryDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into category values (?,?,?)",
                categoryDTO.getCategoryId(),
                categoryDTO.getCategoryName(),
                categoryDTO.getDescription()
        );
    }

    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException {
        return CrudUtil.execute(
                "update category set categoryName=?, description=? where categoryId=?",
                categoryDTO.getCategoryName(),
                categoryDTO.getDescription(),
                categoryDTO.getCategoryId()
        );
    }

    public boolean deleteCategory(String categoryId) throws SQLException {
        return CrudUtil.execute("delete from category where categoryId=?", categoryId);
    }

    public ArrayList<String> getAllCategoryIds() throws SQLException {
        ResultSet rst = CrudUtil.execute("select categoryId from category");

        ArrayList<String> categoryIds = new ArrayList<>();

        while (rst.next()) {
            categoryIds.add(rst.getString(1));
        }
        return categoryIds;
    }

    public CategoryDTO findById(String selectedCatId) throws SQLException {
        ResultSet rst = CrudUtil.execute("select * from category where categoryId=?", selectedCatId);

        if (rst.next()) {
            return new CategoryDTO(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3)
            );
        }
        return null;
    }
}
