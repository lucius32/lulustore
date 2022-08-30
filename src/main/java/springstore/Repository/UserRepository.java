package springstore.Repository;

import springstore.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long> {
    User getUserByEmailAddressAndPassWord(String emailAddress, String password);
    User getUserByEmailAddress(String emailAddress);
}
