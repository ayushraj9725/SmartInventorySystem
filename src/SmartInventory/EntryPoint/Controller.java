package SmartInventory.EntryPoint;


import SmartInventory.Entities.Product;
import SmartInventory.Service.ProductService;
import SmartInventory.DataAccessObject.ProductDAO;
import SmartInventory.CustomExceptions.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        ProductService productService = new ProductService(productDAO);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter product name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter product quantity: ");
                        int quantity = scanner.nextInt();
                        System.out.print("Enter product price: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter threshold : ");
                        int threshold = scanner.nextInt();
                        Product product = new Product(0, name, quantity, price,threshold);
                        productService.addProduct(product);
                        System.out.println("Product added successfully!");
                        break;

                    case 2:
                        List<Product> products = productService.getAllProducts();
                        for (Product p : products) {
                            System.out.println(p);
                        }
                        break;

                    case 3:
                        System.out.print("Enter product ID to update: ");
                        int id = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter new product name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter new product quantity: ");
                        int newQuantity = scanner.nextInt();
                        System.out.print("Enter new product price: ");
                        double newPrice = scanner.nextDouble();
                        System.out.print("Enter threshold : ");
                        int newThreshold = scanner.nextInt();
                        Product updatedProduct = new Product(id, newName, newQuantity, newPrice,newThreshold);
                        productService.updateProduct(updatedProduct);
                        System.out.println("Product updated successfully!");
                        break;

                    case 4:
                        System.out.print("Enter product ID to delete: ");
                        int deleteId = scanner.nextInt();
                        productService.deleteProduct(deleteId);
                        System.out.println("Product deleted successfully!");
                        break;

                    case 5:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid choice!");
                }
            } catch (SQLException | ClassNotFoundException | ProductNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
