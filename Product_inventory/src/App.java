import java.sql.*;
import java.util.Scanner;
import Classes.Product;
import Classes.Inventory;
import dao.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Inventory inventory = new Inventory();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hello", "root", "Ajay@2003");
            View v = new View();
            v.loadProductsFromDatabase(connection, inventory);

            boolean running = true;
            while (running) {
                System.out.println("==============================================");
                System.out.println("Choose a table:");
                System.out.println("1. Products");
                System.out.println("2. Sales");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int tableChoice = scanner.nextInt();

                switch (tableChoice) {
                    case 1:
                        performProductOperations(connection, scanner, inventory);
                        break;
                    case 2:
                        performSalesOperations(connection, scanner, inventory);
                        break;
                    case 0:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } 
        
        
        catch (SQLException e) {
            System.out.println("Exception occurred: " + e.getMessage());
        }
        scanner.close();
    }

    private static void performProductOperations(Connection connection, Scanner scanner, Inventory inventory)
            throws SQLException {
        boolean productOperationsRunning = true;
        while (productOperationsRunning) {
            System.out.println("==============================================");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Search Product");
            System.out.println("4. View All Products");
            System.out.println("5. Edit product");
            System.out.println("0. Back to table selection");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product id: ");
                    int id=scanner.nextInt();
                    int findme = inventory.present(id);
                    if(findme==0)
                    {                    
                     System.out.print("Enter product name: ");
                        scanner.nextLine();
                    String name = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Enter price: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); 
                    Product newProduct = new Product(id ,name, quantity, price);
                    inventory.addProduct(newProduct);
                    System.out.println("Product added successfully!");
                    System.out.println("                                      ");
                    System.out.println("==============================================");
                    Add a=new Add();
                    a.addProductToDatabase(connection, newProduct);
                    }
                    else
                    {
                        System.out.println("                                      ");
                        System.out.println("Id already exists");
                        System.out.println("==============================================");
                    }
                       break;

                case 2:
                                           System.out.print("Enter the id of the product to remove: ");
                    int productNameToRemove = scanner.nextInt();
                    Product productToRemove = inventory.findProductByName(productNameToRemove);
                    if (productToRemove != null) {
                            inventory.removeProduct(productToRemove);
                            System.out.println("                                      ");
                        System.out.println("Product removed successfully!");
                        System.out.println("==============================================");
                    } else {
                        System.out.println("                                      ");
                            System.out.println("Product not found in the inventory.");
                            System.out.println("==============================================");
                        }
                        Remove r=new Remove();
                        r.removeProductFromDatabase(connection, productToRemove);
                        break;


                case 3:
                        System.out.print("Type 'a' to search by id (or) type 'b' to search by name ");
                     String cd=scanner.next();
                          if(cd.equals("a")){
                           System.out.print("Enter the id of the product to search: ");
                           int productId = scanner.nextInt();
                           Product productToSearch = inventory.findProductByName(productId);
                           if (productToSearch != null) {
                                   System.out.println("Product found:");
                                   System.out.println("                                      ");
                                   // System.out.println("Id: "+productToSearch.getId());
                                   System.out.println("Name: " + productToSearch.getName());
                                   System.out.println("Quantity: " + productToSearch.getQuantity());
                                   System.out.println("Price: " + productToSearch.getPrice());
                                   System.out.println("==============================================");
                                   break;
                               }
                                else {
                                   System.out.println("                                      ");
                                       System.out.println("Product not found in the inventory with the given id.");
                                       System.out.println("==============================================");
                                       break;
                                   }
                             }

                           else if(cd.equals("b"))
                           {
                             System.out.print("Enter the name of the product to search: ");
                             String productName = scanner.next();
                             Product productToSearch = inventory.findProductByName(productName);
                             if (productToSearch != null) {
                                   System.out.println("Product found:");
                                   System.out.println("                                      ");
                                   System.out.println("Id: "+productToSearch.getId());
                                //    System.out.println("Name: " + productToSearch.getName());
                                   System.out.println("Quantity: " + productToSearch.getQuantity());
                                   System.out.println("Price: " + productToSearch.getPrice());
                                  System.out.println("==============================================");
                                   break;
                               } 
                               else {
                                   System.out.println("                                      ");
                                       System.out.println("Product not found in the inventory with the given name.");
                                       System.out.println("==============================================");
                                       break;
                                   }
                             }
                          
                            else
                            {
                                System.out.println("No such representation");
                                System.out.println("==============================================");
                                 break;
                            }

                case 4:
                       
                                System.out.println("All Products in Inventory:");
                                for (Product product : inventory.getAllProducts()) {
                                        System.out.println("| Id: "+product.getId()+
                                            " | Name: " + product.getName() +
                                                " | Quantity: " + product.getQuantity() +
                                                " | Price: " + product.getPrice()+" |");
                                            }
                            System.out.println("--------------------------------------------------------------");
                                    break;


                case 5:
                                     System.out.print("Enter the id of the product to edit: ");
                                    int productNameToEdit = scanner.nextInt();
                                    Product productToEdit = inventory.findProductByName(productNameToEdit);
                                    if (productToEdit != null) {
                                        System.out.println("Current Product Details:");
                                        System.out.println("Id: " + productToEdit.getId());
                                        System.out.println("Name: " + productToEdit.getName());
                                        System.out.println("Quantity: " + productToEdit.getQuantity());
                                        System.out.println("Price: " + productToEdit.getPrice());
                                        scanner.nextLine();
                                        System.out.print("Enter new name : ");
                                        String newName = scanner.nextLine();
                                        System.out.print("Enter new quantity : ");
                                        int newQuantity = scanner.nextInt();
                                        System.out.print("Enter new price : ");
                                        double newPrice = scanner.nextDouble();
                                        scanner.nextLine(); 

                                        if (!newName.isEmpty()) {
                                            productToEdit.setName(newName);
                                        }
                                        if (newQuantity > 0) {
                                            productToEdit.setQuantity(newQuantity);
                                        }
                                        if (newPrice > 0.0) {
                                            productToEdit.setPrice(newPrice);
                                        }
                                        Update u=new Update();
                                        u.updateProduct(connection, productToEdit);
                                        System.out.println("Product updated successfully!");
                                    } else {
                                        System.out.println("Product not found in the inventory.");
                                    }
                                    break;


                case 0:
                    productOperationsRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }   

    private static void performSalesOperations(Connection connection, Scanner scanner, Inventory inventory)
            throws SQLException {
        Sales sales = new Sales();
        boolean salesOperationsRunning = true;
        while (salesOperationsRunning) {
            System.out.println("1. Add Sale");
            System.out.println("2. View Sale");
            System.out.println("0. Back to table selection");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter product id: ");
                    int id = scanner.nextInt();
                    Product product = inventory.findProductByName(id);
                    if (product != null) {
                        System.out.print("Enter quantity sold: ");
                        int quantitySold = scanner.nextInt();
                        product.setQuantity(quantitySold); 
                        sales.addSaleToDatabase(connection, product);
                        System.out.println("Sale added successfully!");
                         System.out.println("==============================================");
                    } else {
                        System.out.println("Product not found in the inventory.");
                         System.out.println("==============================================");
                    }
                    break;

                case 2:
                   System.out.print("Sales table view");
                   ViewSales ve=new ViewSales();
                   ve.viewAllSales(connection);
                   break;

                case 0:
                    salesOperationsRunning = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                     System.out.println("==============================================");
            }
        }
    }
}
