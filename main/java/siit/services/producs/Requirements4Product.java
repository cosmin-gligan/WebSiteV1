package siit.services.producs;


import siit.model.Product;

import java.util.List;

public interface Requirements4Product {
    List<Product> getAllProducts();
    void printProductsList();
    Product getProductByID(int id);

}
