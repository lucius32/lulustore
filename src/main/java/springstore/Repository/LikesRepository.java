package springstore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springstore.Model.Likes;
import springstore.Model.Product;
import springstore.Model.User;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes,Integer> {
    Likes getLikesByUserIdAndProId(User userId, Product proId);
    void deleteByUserIdAndProId(User userId,Product proId);
    List<Likes> getLikesByUserId(User user);
}
