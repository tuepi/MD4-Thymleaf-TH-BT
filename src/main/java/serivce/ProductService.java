package serivce;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    private static final List<Product> products;
    static {
        products = new ArrayList<>();
        products.add(new Product(1, "R15", 3500, "New", "Yamaha"));
        products.add(new Product(2, "R6", 11000, "New", "Yamaha"));
        products.add(new Product(3, "R1", 50000, "99%", "Yamaha"));
        products.add(new Product(4, "CBR150", 4000, "New", "Honda"));
        products.add(new Product(5, "CBR600", 7000, "New", "Honda"));
        products.add(new Product(6, "ZX-10R", 400000, "New", "Kawasaki"));
    }
    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void update(int id, Product product) {
        products.set(id, product);
    }

    @Override
    public void remove(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> list = new ArrayList<>();
        for (Product p : products) {
            if (p.getName().contains(name)) {
                list.add(p);
            }
        }
        return list;
    }
}
