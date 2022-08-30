package springstore.Service;

import org.springframework.stereotype.Service;
import springstore.Model.Cart;
import springstore.Model.Product;
import springstore.Model.User;
import springstore.Repository.CartRepository;
import springstore.Repository.ProductRepository;
import springstore.Repository.UserRepository;
import springstore.Service.ServiceInterface.CartService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CartImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartImpl(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(int id, String email) {
        User user = userRepository.getUserByEmailAddress(email);
        Product product = productRepository.getProductsByProdId(id);
        int userId = user.getUserId();
        Cart cart = cartRepository.getCartByProIdAndUserId(product, user);
        Cart tmpCart = new Cart();
        int cartQuantity = 1;
        if (cart == null) {
            tmpCart.setProId(product);
            tmpCart.setUserId(user);
            tmpCart.setProdQuantity(cartQuantity);
            cartRepository.save(tmpCart);
        } else {
            cart.setProdQuantity(cart.getProdQuantity() + cartQuantity);
            cartRepository.save(cart);
        }
    }

    @Override
    public List<Product> getAllCart(String email) {
        User user = userRepository.getUserByEmailAddress(email);
        List<Cart> carts = cartRepository.findCartByUserId(user);
        List<Product> products = new ArrayList<>();
        if (!carts.isEmpty()) {
            carts.forEach((d) -> {
                Product product = productRepository.getProductsByProdId(d.getProId().getProdId());
                products.add(product);
            });
            products.forEach((b) -> {
                carts.forEach(c ->
                {
                    if (b.getProdId() == c.getProId().getProdId()) {
                        b.setProductQuantity(c.getProdQuantity());
                    }
                })
                ;
            });
        }
        return products;
    }

    @Override
    public void deleteProduct(int id, String email) {
        User user = userRepository.getUserByEmailAddress(email);
        Product product = productRepository.getProductsByProdId(id);
        cartRepository.deleteByUserIdAndProId(user,product);
    }


}


