package dao;

import java.sql.*;
import Classes.Inventory;
import Classes.Product;

public class View {
     public  void loadProductsFromDatabase(Connection connection, Inventory inventory) throws SQLException {
        String query = "SELECT * FROM products";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");
                inventory.addProduct(new Product(id, name, quantity, price));
            }
        }
    }
}
