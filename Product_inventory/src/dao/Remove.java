package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Product;

public class Remove {
     public  void removeProductFromDatabase(Connection connection, Product product) throws SQLException {
        String deleteQuery = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, product.getId());
            preparedStatement.executeUpdate();
        }
    }
}
