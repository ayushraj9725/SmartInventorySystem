package SmartInventory.DataAccessObject;


import SmartInventory.CustomExceptions.ProductNotFoundException;
import SmartInventory.Entities.Product;
import SmartInventory.UtilityClasses.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // Add a product
    public void addProduct(Product product) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO product (name, quantity, price,reorder_level) VALUES (?, ?, ? ,?)";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getReorderThreshold());
            statement.executeUpdate();
        }
    }

    // Get all products
    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getInt("quantity"),
                        resultSet.getDouble("price"),
                        resultSet.getInt("reorder_level")
                );
                products.add(product);
            }
        }
        return products;
    }

    // Update a product
    public void updateProduct(Product product) throws SQLException, ClassNotFoundException , ProductNotFoundException {
        String sql = "UPDATE product SET name = ?, quantity = ?, price = ? WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setInt(2, product.getQuantity());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated == 0) {
                throw new ProductNotFoundException("Product with ID " + product.getId() + " not found.");
            }
        }
    }

    // Delete a product
    public void deleteProduct(int id) throws SQLException, ClassNotFoundException , ProductNotFoundException{
        String sql = "DELETE FROM product WHERE id = ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted == 0) {
                throw new ProductNotFoundException("Product with ID " + id + " not found.");
            }
        }
    }

    // Method to get products with low stock
    public List<Product> getLowStockProducts(int threshold) throws SQLException, ClassNotFoundException {
        List<Product> lowStockProducts = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE quantity <= ?";
        try (Connection connection = DatabaseUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, threshold);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Product product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getInt("quantity"),
                            resultSet.getDouble("price"),
                            resultSet.getInt("reorder_level")
                    );
                    lowStockProducts.add(product);
                }
            }
        }
        return lowStockProducts;
    }

}