package lk.ijse.gdse72.shaanfashion.util;

import lk.ijse.gdse72.shaanfashion.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CrudUtil {

    // This class contains utility methods for executing CRUD operations (Create, Read, Update, Delete) with the database.
    public static <T>T execute(String sql,Object... obj) throws SQLException {
        // A generic method to execute SQL queries, with a flexible return type `T`.
        // It accepts an SQL statement and a variable number of parameters (obj) to insert into the SQL query.
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement(sql);

        // Iterates through the variable arguments (obj) and sets them in the prepared statement in place of the placeholders (?) in the SQL query.
        // The loop starts from 0, but `setObject` expects positions starting from 1, hence (i + 1).
        for (int i=0;i<obj.length;i++){
            pst.setObject((i+1),obj[i]);
        }

        if (sql.startsWith("select") || sql.startsWith("SELECT")){
            // If the SQL query is a SELECT statement, the prepared statement is executed as a query.
            // A ResultSet is returned, which contains the data retrieved from the database.
            ResultSet resultSet = pst.executeQuery();
            return (T) resultSet;
            // The result set is returned, cast to the generic type `T`.
        }else {
            int i = pst.executeUpdate();
            // For non-SELECT queries (INSERT, UPDATE, DELETE), executeUpdate() is called.
            boolean isSaved = i >0;

            return (T) ((Boolean) isSaved);
        }
    }
}
