package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import Classes.Product;


public class Update{
      public void updateProduct(Connection connection, Product product) throws SQLException {
        String updateQuery = "UPDATE products SET name=?, quantity=?, price=? WHERE id=?";
        PreparedStatement statement = connection.prepareStatement(updateQuery);
        statement.setString(1, product.getName());
        statement.setInt(2, product.getQuantity());
        statement.setDouble(3, product.getPrice());
        statement.setInt(4, product.getId());
        statement.executeUpdate();
        statement.close();
    }
}
