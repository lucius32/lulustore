package springstore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springstore.Model.Cart;
import springstore.Model.Likes;
import springstore.Model.Product;
import springstore.Model.User;
import springstore.Repository.LikesRepository;
import springstore.Repository.ProductRepository;
import springstore.Repository.UserRepository;
import springstore.Service.ServiceInterface.LikesService;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class LikesServiceImp implements LikesService {
    @Autowired
   private LikesRepository likesRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void likeProduct(String email, int proId) {

        User user= userRepository.getUserByEmailAddress(email);
        Product product = productRepository.findById(proId).orElseThrow();
        Likes likes =  likesRepository.getLikesByUserIdAndProId(user,product);
        Likes likes1 = new Likes();
      if(likes == null){
          likes1.setUserId(user);
          likes1.setProId(product);
          likesRepository.save(likes1);
      }
      else {
          likesRepository.deleteByUserIdAndProId(user, product);
      }
    }

    @Override
    public List<Product> getAlllikes(String email) {
        User user= userRepository.getUserByEmailAddress(email);
        List<Likes> likes = likesRepository.getLikesByUserId(user);
        List<Product> products = new ArrayList<>();
        if (!likes.isEmpty()) {
            likes.forEach((d) -> {
                Product product = productRepository.getProductsByProdId(d.getProId().getProdId());
                products.add(product);
            });}

        return products;
    }

    @Override
    public void deleteProduct(int id, String email) {
        User user = userRepository.getUserByEmailAddress(email);
        Product product = productRepository.getProductsByProdId(id);
        likesRepository.deleteByUserIdAndProId(user,product);
    }


}

