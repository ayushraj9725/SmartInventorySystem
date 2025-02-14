package SmartInventory.Entities;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;
    private int reorderThreshold;

    // Constructor, Getters, and Setters
    public Product(int id, String name, int quantity, double price , int reorderThreshold) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.reorderThreshold = reorderThreshold ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Add getter and setter for reorderThreshold
    public int getReorderThreshold() {
        return reorderThreshold;
    }

    public void setReorderThreshold(int reorderThreshold) {
        this.reorderThreshold = reorderThreshold;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
