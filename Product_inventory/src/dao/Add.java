package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Classes.Product;

public class Add {
    
    public void addProductToDatabase(Connection connection, Product product) throws SQLException {
        String insertQuery = "INSERT INTO products (id, name, quantity, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
             preparedStatement.setInt(1, product.getId());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.executeUpdate();
        }
    }
}
