package springstore.Service.ServiceInterface;

import springstore.Model.Product;

import java.util.List;

public interface CartService {
    void addProductToCart(int id, String email);
     List<Product> getAllCart(String email);

    void deleteProduct(int id, String email);
}
