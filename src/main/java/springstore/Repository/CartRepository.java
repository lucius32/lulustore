package springstore.Repository;

import springstore.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import springstore.Model.Product;
import springstore.Model.User;

import java.util.List;
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart getCartByProIdAndUserId(Product proId,User userId);
    List<Cart> findCartByUserId(User userId);
    void deleteByUserIdAndProId(User user, Product product);
}
