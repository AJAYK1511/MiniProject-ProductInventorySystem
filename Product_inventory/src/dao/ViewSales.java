package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewSales {
    public void viewAllSales(Connection connection) throws SQLException {
    String query = "SELECT * FROM sales";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        ResultSet resultSet = statement.executeQuery();
        
        System.out.println("All Sales Records:");
        while (resultSet.next()) {
            int productId = resultSet.getInt("product_id");
            String productName = resultSet.getString("product_name");
            int quantitySold = resultSet.getInt("quantity_sold");
            double totalAmount = resultSet.getDouble("total_amount");

            System.out.println("Product ID: " + productId);
            System.out.println("Product Name: " + productName);
            System.out.println("Quantity Sold: " + quantitySold);
            System.out.println("Total Amount: " + totalAmount);
            System.out.println("==============================================");
        }
    }
}

}
