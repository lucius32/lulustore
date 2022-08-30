package springstore.Service.ServiceInterface;

import springstore.Model.Likes;
import springstore.Model.Product;

import java.util.List;

public interface LikesService {
    void likeProduct(String email,int proId);

    List<Product> getAlllikes(String email);

    void deleteProduct(int id, String email);
}
