package Classes;

public class Product extends ProductOne {
    public Product(int id, String name, int quantity, double price) {
        super(id, name, quantity, price);
    }

    @Override
    public double calculateTotalValue() {
        return getQuantity() * getPrice();
    }
}
