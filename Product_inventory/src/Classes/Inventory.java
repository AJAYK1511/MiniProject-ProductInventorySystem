package Classes;
import java.util.ArrayList;
import java.util.List;

interface newOne
{
    int id=0;
    String name="";
    Product findProductByName(int id);
    int present(int id);
    Product findProductByName(String name);
}
public class Inventory implements newOne{
    private List<Product> products;

    public Inventory() {
        products = new ArrayList<>();
    }
    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public Product findProductByName(int id) {
        for (Product product : products) {
            if (product.getId()==id) {
                return product;
            }
        }
        return null;
    }
    public Product findProductByName(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
    public int present(int id)
    {
         for (Product product : products) {
            if (product.getId()==id) {
                return 1;
            }
        }
        return 0;
    }
    public List<Product> getAllProducts() {
        return products;
    }
}
