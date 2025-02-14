package SmartInventory.Service;

import SmartInventory.DataAccessObject.ProductDAO;
import SmartInventory.Entities.Product;
import SmartInventory.CustomExceptions.ProductNotFoundException;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private final ProductDAO productDAO;
    private Product product ;

    public ProductService(ProductDAO productDAO) {
        this.productDAO = productDAO;

    }

    public void addProduct(Product product) throws SQLException, ClassNotFoundException {
        productDAO.addProduct(product);
    }

    public List<Product> getAllProducts() throws SQLException, ClassNotFoundException {
        return productDAO.getAllProducts();
    }

    public void updateProduct(Product product) throws SQLException, ClassNotFoundException , ProductNotFoundException{
        productDAO.updateProduct(product);
    }

    public void deleteProduct(int id) throws SQLException, ClassNotFoundException , ProductNotFoundException{
        productDAO.deleteProduct(id);
    }

    public void checkAndReorderProducts() throws SQLException, ClassNotFoundException, ProductNotFoundException {
        List<Product> lowStockProducts = productDAO.getLowStockProducts(product.getReorderThreshold());  // Threshold is 10 units

        if (!lowStockProducts.isEmpty()) {
            for (Product product : lowStockProducts) {
                // Reorder logic, for example, you can add stock manually:
                product.setQuantity(product.getQuantity() + 50);  // Restock by 50 units

                // Update product in DB with reordered quantity
                productDAO.updateProduct(product);

                // Notify user or log reorder
                System.out.println("Reordered product: " + product.getName());
            }
        }
    }

}
