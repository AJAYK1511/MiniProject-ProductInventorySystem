package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Classes.Product;;

public class Sales {

    public void addSaleToDatabase(Connection connection, Product product) throws SQLException {


        String checkQuantityQuery = "SELECT quantity FROM products WHERE id = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuantityQuery)) {
            checkStatement.setInt(1, product.getId());
            ResultSet resultSet = checkStatement.executeQuery();



            if (resultSet.next()) {
                int availableQuantity = resultSet.getInt("quantity");



                if (product.getQuantity() <= availableQuantity) {
                    String selectExistingSaleQuery = "SELECT * FROM sales WHERE product_id = ?";
                    try (PreparedStatement selectExistingSaleStatement = connection.prepareStatement(selectExistingSaleQuery)) {
                        selectExistingSaleStatement.setInt(1, product.getId());
                        ResultSet existingSaleResultSet = selectExistingSaleStatement.executeQuery();

                        if (existingSaleResultSet.next()) {
                            int existingSaleQuantity = existingSaleResultSet.getInt("quantity_sold");
                            int totalQuantitySold = existingSaleQuantity + product.getQuantity();

                            String updateSaleQuery = "UPDATE sales SET quantity_sold = ?, total_amount = ? WHERE product_id = ?";
                            try (PreparedStatement updateSaleStatement = connection.prepareStatement(updateSaleQuery)) {
                                updateSaleStatement.setInt(1, totalQuantitySold);
                                updateSaleStatement.setDouble(2, product.getPrice() * totalQuantitySold);
                                updateSaleStatement.setInt(3, product.getId());
                                updateSaleStatement.executeUpdate();
                            }

                        }
                        
                        else {
                            String insertQuery = "INSERT INTO sales (product_id, product_name, quantity_sold, total_amount) VALUES (?, ?, ?, ?)";
                            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                                insertStatement.setInt(1, product.getId());
                                insertStatement.setString(2, product.getName());
                                insertStatement.setInt(3, product.getQuantity());
                                insertStatement.setDouble(4, product.getPrice() * product.getQuantity());
                                insertStatement.executeUpdate();
                            }
                        }

                        String updateQuantityQuery = "UPDATE products SET quantity = ? WHERE id = ?";
                        try (PreparedStatement updateStatement = connection.prepareStatement(updateQuantityQuery)) {
                            updateStatement.setInt(1, availableQuantity - product.getQuantity());
                            updateStatement.setInt(2, product.getId());
                            updateStatement.executeUpdate();
                        }
                    }
                } 
                
                else {
                    System.out.println("Not enough quantity available for sale.");
                }
            }
            
            
            else {
                System.out.println("Product not found in the inventory.");
            }
        }
    }
}
